/*
 * Copyright 2016, 2017, 2018, 2019 FabricMC
 * Copyright 2021 QuiltMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.quiltmc.qsl.game_rule.impl.rule;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.GameRules;

import org.quiltmc.qsl.game_rule.api.GameRuleRegistry;
import org.quiltmc.qsl.game_rule.mixin.IntRuleAccessor;

public final class BoundedIntRule extends GameRules.IntRule {
	private static final Logger LOGGER = LogManager.getLogger(GameRuleRegistry.class);

	private int minimumValue;
	private int maximumValue;

	public BoundedIntRule(GameRules.Type<GameRules.IntRule> type, int initialValue, int minimumValue, int maximumValue) {
		super(type, initialValue);

		if (areBoundsInvalid(minimumValue, maximumValue)) {
			throw new IllegalArgumentException(String.format("Invalid bounds %s - %s, minimum must be less than maximum", minimumValue, maximumValue));
		}

		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
	}

	@Override
	protected void deserialize(String value) {
		final int i = BoundedIntRule.parseInt(value);

		if (this.minimumValue > i || this.maximumValue < i) {
			LOGGER.warn("Failed to parse integer {}. Was out of bounds {} - {}", value, this.minimumValue, this.maximumValue);
			return;
		}

		((IntRuleAccessor) (Object) this).setValue(i);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean validate(String input) {
		try {
			int value = Integer.parseInt(input);

			if (this.minimumValue > value || this.maximumValue < value) {
				return false;
			}

			((IntRuleAccessor) (Object) this).setValue(value);
			return true;
		} catch (NumberFormatException var3) {
			return false;
		}
	}

	@Override
	protected GameRules.IntRule copy() {
		return new BoundedIntRule(this.type, ((IntRuleAccessor) (Object) this).getValue(), this.minimumValue, this.maximumValue);
	}

	public int getMinimumValue() {
		return this.minimumValue;
	}

	public int getMaximumValue() {
		return this.maximumValue;
	}

	public void setMinimumValue(int minimumValue) {
		if (areBoundsInvalid(minimumValue, this.maximumValue)) {
			throw new IllegalArgumentException(String.format("Invalid bounds %s - %s, minimum must be less than maximum", minimumValue, this.maximumValue));
		}

		if (this.get() < minimumValue) {
			throw new IllegalArgumentException(String.format("Could not set minimum value to %s. Current value is less than minimum", minimumValue));
		}

		this.minimumValue = minimumValue;
	}

	public void setMaximumValue(int maximumValue) {
		if (areBoundsInvalid(this.minimumValue, maximumValue)) {
			throw new IllegalArgumentException(String.format("Invalid bounds %s - %s, minimum must be less than maximum", this.minimumValue, maximumValue));
		}

		if (this.get() > maximumValue) {
			throw new IllegalStateException(String.format("Cannot set maximum value to %s. Current value is less than minimum", maximumValue));
		}

		this.maximumValue = maximumValue;
	}

	private static int parseInt(String input) {
		if (!input.isEmpty()) {
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException var2) {
				LOGGER.warn("Failed to parse integer {}", input);
			}
		}

		return 0;
	}

	private static boolean areBoundsInvalid(int minimumValue, int maximumValue) {
		return minimumValue >= maximumValue;
	}
}
