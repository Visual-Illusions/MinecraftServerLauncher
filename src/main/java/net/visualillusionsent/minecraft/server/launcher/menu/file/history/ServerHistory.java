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
 * Copyright &copy; 2009-2013 [Mojang AB](http://mojang.com)
 * "Minecraft" is a trademark of Notch Development AB
 *
 * Visual Illusions Minecraft Server Launcher and Visual Illusions Entertainment are
 * NOT affilated with, endorsed by, or sponsored by Mojang AB or Notch Development AB.
 */
package net.visualillusionsent.minecraft.server.launcher.menu.file.history;

import net.visualillusionsent.minecraft.server.launcher.CentralPanel;
import net.visualillusionsent.minecraft.server.launcher.ControlRoom;
import net.visualillusionsent.minecraft.server.launcher.info.BukkitServerInfo;
import net.visualillusionsent.minecraft.server.launcher.info.CanaryServerInfo;
import net.visualillusionsent.minecraft.server.launcher.info.HModServerInfo;
import net.visualillusionsent.minecraft.server.launcher.info.ServerInfo;
import net.visualillusionsent.minecraft.server.launcher.info.VanillaServerInfo;
import net.visualillusionsent.minecraft.server.launcher.menu.MainMenuBar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/** @author Jason (darkdiplomat) */
public class ServerHistory implements IFileHistory {
    private final CentralPanel central;
    private final MainMenuBar main;

    public ServerHistory(CentralPanel central, MainMenuBar main) {
        this.central = central;
        this.main = main;
    }

    public String getApplicationName() {
        return "visualillusions".concat(File.separator).concat("MinecraftServerLauncher");
    }

    public JMenu getFileMenu() {
        return main.getMenu(0);
    }

    public Dimension getSize() {
        return central.getSize();
    }

    public JFrame getFrame() {
        return central;
    }

    public void loadFile(String pathname) {
        ControlRoom.setServerInfo(createInfo(new File(pathname)));
    }

    private final ServerInfo createInfo(File file) {
        ServerInfo toSet = null;
        try {
            String absolute_path = file.getAbsolutePath();
            JarFile jarFile = new JarFile(file);
            Manifest mf = jarFile.getManifest();
            Attributes attr = mf.getMainAttributes();
            String att = attr.getValue("Main-Class");
            if (att != null && att.equals("net.minecraft.server.MinecraftServer")) {

                toSet = new VanillaServerInfo(absolute_path.substring(0, absolute_path.lastIndexOf(File.separator)), file.getName());
            }
            if (toSet == null) {
                att = attr.getValue("Specification-Title");
                if (att != null && att.equals("Bukkit")) {
                    toSet = new BukkitServerInfo(absolute_path.substring(0, absolute_path.lastIndexOf(File.separator)), file.getName());
                }
                else if (att != null && att.equals("CanaryLib")) {
                    toSet = new CanaryServerInfo(absolute_path.substring(0, absolute_path.lastIndexOf(File.separator)), file.getName());
                }
            }
            if (toSet == null) {
                att = attr.getValue("Class-Path");
                if (att != null) {
                    if ((att.contains("minecraft_servero.jar") && att.contains("jarjar.jar"))) {
                        toSet = new HModServerInfo(absolute_path.substring(0, absolute_path.lastIndexOf(File.separator)), file.getName());
                    }
                }
            }
            jarFile.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return toSet;
    }
}
