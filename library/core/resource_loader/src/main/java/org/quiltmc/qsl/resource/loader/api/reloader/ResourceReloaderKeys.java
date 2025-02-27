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

package org.quiltmc.qsl.resource.loader.api.reloader;

import net.minecraft.util.Identifier;

/**
 * This class contains default keys for various Minecraft resource reloaders.
 *
 * @see IdentifiableResourceReloader
 */
public final class ResourceReloaderKeys {
	/**
	 * Keys for various client resource reloaders.
	 */
	public static final class Client {
		public static final Identifier SOUNDS = new Identifier("minecraft:sounds");
		public static final Identifier FONTS = new Identifier("minecraft:fonts");
		public static final Identifier MODELS = new Identifier("minecraft:models");
		public static final Identifier LANGUAGES = new Identifier("minecraft:languages");
		public static final Identifier TEXTURES = new Identifier("minecraft:textures");

		private Client() {
		}
	}

	/**
	 * Keys for various server resource reloaders.
	 */
	public static final class Server {
		public static final Identifier TAGS = new Identifier("minecraft:tags");
		public static final Identifier RECIPES = new Identifier("minecraft:recipes");
		public static final Identifier ADVANCEMENTS = new Identifier("minecraft:advancements");
		public static final Identifier FUNCTIONS = new Identifier("minecraft:functions");
		public static final Identifier LOOT_TABLES = new Identifier("minecraft:loot_tables");

		private Server() {
		}
	}

	private ResourceReloaderKeys() {
	}
}
