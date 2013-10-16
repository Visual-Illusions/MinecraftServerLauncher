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
package net.visualillusionsent.minecraft.server.launcher;

import net.visualillusionsent.minecraft.server.launcher.info.ServerInfo;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/** @author Jason (darkdiplomat) */
public final class InfoPanel extends JPanel {

    private static final long serialVersionUID = -5031046066912599520L;
    private final int hMod = 0xFFFF00, canary = 0xFFAA00, bukkit = 0x00FFFF, vanilla = 0xFFFFCC;
    private final String label = "<html><p style=\"text-align:center\">Server Type: <font color=%s\">%s</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Server Path: <font color=%s\">%s</font></p></html>";
    private final JLabel info_label;

    public InfoPanel(CentralPanel central) {
        Dimension size = new Dimension(680, 20);
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setBackground(Color.DARK_GRAY);
        this.add(info_label = createLabel());
        setLabelText();
        this.setVisible(true);
    }

    private final JLabel createLabel() {
        JLabel info_label = new JLabel();
        info_label.setForeground(Color.LIGHT_GRAY);
        info_label.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        info_label.setLocation(0, 0);
        return info_label;
    }

    final void setLabelText() {
        ServerInfo info = ControlRoom.getServerInfo();
        if (info == null) {
            info_label.setText(String.format(label, "#FF0000", "NONE", "#FF0000", "NONE"));
        }
        else {
            String typecolor = "#FFFFFF";
            switch (info.getServerType()) {
                case CANARY:
                    typecolor = Integer.toHexString(canary);
                    break;
                case HMOD:
                    typecolor = Integer.toHexString(hMod);
                    break;
                case BUKKIT:
                    typecolor = Integer.toHexString(bukkit);
                    break;
                case VANILLA:
                    typecolor = Integer.toHexString(vanilla);
                    break;
            }
            info_label.setText(String.format(label, typecolor, info.getServerType(), "#00FF00", shortenPathName(info.getFullPath())));
        }
    }

    private final String shortenPathName(String pathname) {
        final char FILE_SEPARATOR = File.separatorChar;
        final int pathnameLen = pathname.length();
        final int MAX_ITEM_LEN = 50;
        // if the pathame is short enough: return whole pathname
        if (pathnameLen <= MAX_ITEM_LEN) {
            return pathname;
        }
        // if we have only one directory: return whole pathname
        // we will not cut to MAX_ITEM_LEN here
        if (pathname.indexOf(File.separator) == pathname.lastIndexOf(File.separator)) {
            return pathname;
        }
        else {
            // abbreviate pathanme: Windows OS like solution
            final int ABBREVIATED_PREFIX_LEN = 6; // e.g.: C:\..\
            final int MAX_PATHNAME_LEN = MAX_ITEM_LEN - ABBREVIATED_PREFIX_LEN;
            int firstFileSeparatorIndex = 0;
            for (int i = pathnameLen - 1; i >= (pathnameLen - MAX_PATHNAME_LEN); i--) {
                if (pathname.charAt(i) == FILE_SEPARATOR) {
                    firstFileSeparatorIndex = i;
                }
            }
            if (firstFileSeparatorIndex > 0) {
                return pathname.substring(0, 3) + ".." + pathname.substring(firstFileSeparatorIndex, pathnameLen);
            }
            else {
                return pathname.substring(0, 3) + ".." + File.separator + pathname.substring(pathnameLen - MAX_PATHNAME_LEN, pathnameLen);
            }
        }
    }
}
