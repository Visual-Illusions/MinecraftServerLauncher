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
 * NOT affilated with, endorsed, or sponsored by with Mojang AB or Notch Development AB.
 */
package net.visualillusionsent.minecraft.server.launcher.controller;

import net.visualillusionsent.minecraft.server.launcher.LaunchSettings;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.lang.management.ManagementFactory;

/** @author Jason (darkdiplomat) */
public final class RAMSetPanel extends JComponent {

    private static final long serialVersionUID = -8248761030457166279L;
    private final JComboBox ramlist;

    @SuppressWarnings("restriction")
    public RAMSetPanel(ServerButtonsAndRam sbar) {
        TitledBorder title_border = new TitledBorder(new EtchedBorder(), "RAM Setting");
        title_border.setTitleColor(Color.LIGHT_GRAY);
        this.setBorder(title_border);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setPreferredSize(new Dimension(90, 45));
        this.setMinimumSize(new Dimension(90, 45));
        this.setMaximumSize(new Dimension(90, 45));

        ramlist = new JComboBox();
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize() / 1024 / 1024;
        boolean x86 = System.getProperty("sun.arch.data.model").equals("32");
        for (int index = 512; index < memorySize; index += 256) {
            if (x86 && index > 1536) { // 32Bit VM max ram is 1.5GB
                break;
            }
            ramlist.addItem((index / 1024.0F) + "GB");
        }
        ramlist.setMaximumRowCount(10);
        int ramindex = LaunchSettings.getRAMSetting();
        if ((ramlist.getItemCount() - 1) < ramindex) {
            ramlist.setSelectedIndex(0);
        }
        else {
            ramlist.setSelectedIndex(ramindex);
        }
        ramlist.setLightWeightPopupEnabled(false);
        this.add(ramlist, BorderLayout.CENTER);
    }

    public final int getSelectedRAM() {
        String ram = (String) ramlist.getSelectedItem();
        ram = ram.replace("GB", "");
        return (int) (Float.parseFloat(ram) * 1024);
    }

    public int getSelectedRAMIndex() {
        return ramlist.getSelectedIndex();
    }
}
