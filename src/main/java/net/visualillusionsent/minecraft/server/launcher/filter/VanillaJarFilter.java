/*
 * This file is part of VisualIllusionsMinecraftServerLauncher.
 *
 * Copyright © 2013 Visual Illusions Entertainment
 *
 * VisualIllusionsMinecraftServerLauncher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * VisualIllusionsMinecraftServerLauncher is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with VisualIllusionsMinecraftServerLauncher.
 * If not, see http://www.gnu.org/licenses/gpl.html.
 */
package net.visualillusionsent.minecraft.server.launcher.filter;

import net.visualillusionsent.minecraft.server.launcher.info.ServerType;

import java.io.File;

/** @author Jason (darkdiplomat) */
public final class VanillaJarFilter extends MinecraftJarFilter {

    @Override
    public final String getDescription() {
        return "Vanilla Minecraft Server";
    }

    @Override
    protected final boolean verify(File jar) {
        return getServerType(jar) == ServerType.VANILLA;
    }
}
