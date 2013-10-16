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
package net.visualillusionsent.minecraft.server.launcher.info;

import java.io.File;

/** @author Jason (darkdiplomat) */
public abstract class ServerInfo {

    private final String directory;
    private final String jarName;
    private final String args;

    public ServerInfo(String directory, String jarName, String args) {
        this.directory = directory;
        this.jarName = jarName;
        this.args = args;
    }

    public abstract ServerType getServerType();

    public final String getDirectory() {
        return directory;
    }

    public final String getJarName() {
        return jarName;
    }

    public final String getFullPath() {
        return directory + File.separator + jarName;
    }

    public final String getArguments() {
        return args;
    }

    public final String toString() {
        return String.format("Server Type: %s | Server Path: %s", getServerType(), getFullPath());
    }
}
