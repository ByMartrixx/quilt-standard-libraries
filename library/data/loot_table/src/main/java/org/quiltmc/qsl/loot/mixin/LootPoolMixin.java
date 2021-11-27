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

package org.quiltmc.qsl.loot.mixin;

import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.provider.number.LootNumberProvider;
import org.quiltmc.qsl.loot.api.QuiltLootPool;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Arrays;
import java.util.List;

@Mixin(LootPool.class)
public abstract class LootPoolMixin implements QuiltLootPool {
	@Shadow
	@Final
	private LootPoolEntry[] entries;

	@Shadow
	@Final
	private LootCondition[] conditions;

	@Shadow
	@Final
	private LootFunction[] functions;

	@Override
	public List<LootPoolEntry> getEntries() {
		return Arrays.asList(entries);
	}

	@Override
	public List<LootCondition> getConditions() {
		return Arrays.asList(conditions);
	}

	@Override
	public List<LootFunction> getFunctions() {
		return Arrays.asList(functions);
	}

	@Accessor
	@Override
	public abstract LootNumberProvider getRolls();
}
