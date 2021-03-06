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
package net.visualillusionsent.minecraft.server.launcher.controller;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;
import net.visualillusionsent.minecraft.server.launcher.LaunchSettings;
import net.visualillusionsent.minecraft.server.launcher.popups.EditPopupMenu;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** @author Jason (darkdiplomat) */
public final class MinecraftOptions extends JPanel implements MouseListener {

    private static final long serialVersionUID = -2607250895158087707L;
    private final TextField textField;

    public MinecraftOptions(ServerOptionsPanel sop) {
        this.setLayout(new BorderLayout());
        TitledBorder title_border = new TitledBorder(new EtchedBorder(), "Minecraft Arguments");
        title_border.setTitleColor(Color.LIGHT_GRAY);
        this.setBorder(title_border);
        this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(220, 45));
        this.setMinimumSize(new Dimension(220, 45));
        textField = new TextField();
        textField.addMouseListener(this);
        textField.setSize(new Dimension(210, 35));
        textField.setText(LaunchSettings.getCustomMCArgs());
        textField.setCaretPosition(textField.getText().length());
        this.add(textField, BorderLayout.NORTH);
        ControlRoom.setMCOptions(this);
    }

    public final String getMinecraftArgs() {
        return textField.getText();
    }

    public final void mouseClicked(MouseEvent event) {
        new EditPopupMenu(event, true);
    }

    public final void mousePressed(MouseEvent event) {
    }

    public final void mouseReleased(MouseEvent event) {
    }

    public final void mouseEntered(MouseEvent event) {
    }

    public final void mouseExited(MouseEvent event) {
    }
}
