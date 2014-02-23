/*
 * This file is part of Visual Illusions Minecraft Server Launcher.
 *
 * Copyright © 2013-2014 Visual Illusions Entertainment
 *
 * Visual Illusions Minecraft Server Launcher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see http://www.gnu.org/licenses/gpl.html.
 *
 * Minecraft is the property of Mojang AB/Notch Development AB
 * Copyright &copy; 2009-2014 Mojang AB
 * "Minecraft" is a trademark of Notch Development AB
 *
 * Visual Illusions Minecraft Server Launcher and Visual Illusions Entertainment are
 * NOT affilated with, endorsed by, or sponsored by Mojang AB or Notch Development AB.
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
