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
package net.visualillusionsent.minecraft.server.launcher.controller.actions;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** @author Jason (darkdiplomat) */
public class ServerKillButton extends JButton
        implements MouseListener {
    public ServerKillButton(ServerActionButtons sab) {
        super("Force Kill");
        Dimension size = new Dimension(150, 25);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setAlignmentX(0.5F);
        addMouseListener(this);
        setEnabled(false);
        setVisible(true);
    }

    public final void mouseClicked(MouseEvent event) {
        if (isEnabled())
            ControlRoom.killServer();
    }

    public final void mouseEntered(MouseEvent event) {
    }

    public final void mouseExited(MouseEvent event) {
    }

    public final void mousePressed(MouseEvent event) {
    }

    public final void mouseReleased(MouseEvent event) {
    }
}