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
package net.visualillusionsent.minecraft.server.launcher.controller.jvm;

import net.visualillusionsent.minecraft.server.launcher.LaunchSettings;
import net.visualillusionsent.minecraft.server.launcher.popups.EditPopupMenu;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** @author Jason (darkdiplomat) */
public final class JVMExtraPanel extends JPanel implements MouseListener {

    private static final long serialVersionUID = 797327066478741873L;
    private final TextField textField;

    public JVMExtraPanel(JVMOptions opt) {
        this.setLayout(null);
        TitledBorder title_border = new TitledBorder(new EtchedBorder(), "Custom JVM Arguments");
        title_border.setTitleColor(Color.LIGHT_GRAY);
        this.setBorder(title_border);
        this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
        this.setBounds(5, 130, 210, 50);
        textField = new TextField();
        textField.setBounds(10, 20, 190, 20);
        textField.addMouseListener(this);
        textField.setText(LaunchSettings.getJVMCustomArgs());
        textField.setCaretPosition(textField.getText().length());
        this.setToolTipText("Custom Command Line JVM Arguments.");
        this.add(textField);
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

    final String getJVMExtra() {
        return textField.getText();
    }
}
