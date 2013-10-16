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
 *
 * Minecraft is the property of Mojang AB/Notch Development AB
 * Copyright &copy; 2009-2013 Mojang AB
 * "Minecraft" is a trademark of Notch Development AB
 *
 * Visual Illusions Minecraft Server Launcher and Visual Illusions Entertainment are
 * NOT affilated with, endorsed by, or sponsored by Mojang AB or Notch Development AB.
 */
package net.visualillusionsent.minecraft.server.launcher.filter;

import net.visualillusionsent.minecraft.server.launcher.info.ServerType;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/** @author Jason (darkdiplomat) */
public abstract class MinecraftJarFilter extends FileFilter {

    @Override
    public final boolean accept(File jar) {
        if (jar.isDirectory()) {
            return true;
        }
        int per = jar.getName().lastIndexOf(".");
        if (per > -1) {
            String extension = jar.getName().substring(per, jar.getName().length());
            if (!extension.isEmpty()) {
                if (extension.equals(".jar")) {
                    return verify(jar);
                }
            }
        }
        return false;
    }

    protected abstract boolean verify(File jar);

    protected final ServerType getServerType(File jar) {
        ServerType toRet = null;
        try {
            JarFile jarFile = new JarFile(jar);
            Manifest mf = jarFile.getManifest();
            Attributes attr = mf.getMainAttributes();
            String att = attr.getValue("Main-Class");
            if (att != null && att.equals("net.minecraft.server.MinecraftServer") && !jar.getName().equals("minecraft_servero.jar")) {
                toRet = ServerType.VANILLA;
            }
            att = attr.getValue("Specification-Title");
            if (att != null && att.equals("Bukkit")) {
                toRet = ServerType.BUKKIT;
            }
            else if (att != null && att.equals("CanaryLib")) {
                toRet = ServerType.CANARY;
            }
            att = attr.getValue("Class-Path");
            if (att != null) {
                if ((att.contains("minecraft_servero.jar") && att.contains("jarjar.jar"))) {
                    toRet = ServerType.HMOD;
                }
            }
            jarFile.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return toRet;
    }
}
