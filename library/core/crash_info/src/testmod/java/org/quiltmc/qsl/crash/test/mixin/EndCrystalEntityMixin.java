/*
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

package org.quiltmc.qsl.crash.test.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.world.World;

@Mixin(EndCrystalEntity.class)
public abstract class EndCrystalEntityMixin extends Entity {
	public EndCrystalEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "tick", at = @At("HEAD"))
	void crashOnTick(CallbackInfo ci) {
		if (world.getBlockState(getBlockPos().down()).getBlock() == Blocks.DIAMOND_BLOCK) {
			this.kill();
			throw new RuntimeException("Crash Test!");
		}
	}
}
