/*
 * This file is part of VisualIllusionsMinecraftServerLauncher.
 *
 * Copyright © 2013-2014 Visual Illusions Entertainment
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
 * Copyright &copy; 2009-2014 Mojang AB
 * "Minecraft" is a trademark of Notch Development AB
 *
 * Visual Illusions Minecraft Server Launcher and Visual Illusions Entertainment are
 * NOT affilated with, endorsed by, or sponsored by Mojang AB or Notch Development AB.
 */
package net.visualillusionsent.minecraft.server.launcher.menu.file;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;
import net.visualillusionsent.minecraft.server.launcher.LaunchSettings;
import net.visualillusionsent.minecraft.server.launcher.filter.AllServersFilter;
import net.visualillusionsent.minecraft.server.launcher.filter.BukkitJarFilter;
import net.visualillusionsent.minecraft.server.launcher.filter.CanaryJarFilter;
import net.visualillusionsent.minecraft.server.launcher.filter.HModJarFilter;
import net.visualillusionsent.minecraft.server.launcher.filter.VanillaJarFilter;
import net.visualillusionsent.minecraft.server.launcher.info.BukkitServerInfo;
import net.visualillusionsent.minecraft.server.launcher.info.CanaryServerInfo;
import net.visualillusionsent.minecraft.server.launcher.info.HModServerInfo;
import net.visualillusionsent.minecraft.server.launcher.info.ServerInfo;
import net.visualillusionsent.minecraft.server.launcher.info.VanillaServerInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/** @author Jason (darkdiplomat) */
final class FindMinecraftServerJarMenuItem extends JMenuItem implements ActionListener {
    private final JFileChooser fc;
    private final FileMenu fmenu;
    private static final long serialVersionUID = -7387615072696675860L;

    public FindMinecraftServerJarMenuItem(FileMenu fmenu) {
        super("Find Minecraft Server...");
        fc = new JFileChooser();
        createFileChoose();
        this.fmenu = fmenu;
        this.addActionListener(this);
    }

    public final void actionPerformed(ActionEvent e) {
        fc.setCurrentDirectory(fc.getFileSystemView().createFileObject(LaunchSettings.getPreviousDirectory()));
        int returnVal = fc.showOpenDialog(fmenu.getParent().getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            ControlRoom.setServerInfo(createInfo(file));
            LaunchSettings.setPreviousDirectory(fc.getCurrentDirectory().getAbsolutePath());
        }
    }

    private final void createFileChoose() {
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(new VanillaJarFilter());
        fc.addChoosableFileFilter(new HModJarFilter());
        fc.addChoosableFileFilter(new CanaryJarFilter());
        fc.addChoosableFileFilter(new BukkitJarFilter());
        fc.addChoosableFileFilter(new AllServersFilter());
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
