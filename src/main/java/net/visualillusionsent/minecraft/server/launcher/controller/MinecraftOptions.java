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
