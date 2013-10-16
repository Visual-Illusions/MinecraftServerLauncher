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
package net.visualillusionsent.minecraft.server.launcher.menu.server;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** @author Jason (darkdiplomat) */
public class ServerKillMenuItem extends JMenuItem implements ActionListener {
    private static final long serialVersionUID = 11184005272013L;

    public ServerKillMenuItem(ServerMenu sm) {
        super("Force Kill");
        setEnabled(false);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        ControlRoom.killServer();
    }
}
