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
package net.visualillusionsent.minecraft.server.launcher.controller.actions;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;
import net.visualillusionsent.minecraft.server.launcher.controller.ServerButtonsAndRam;

import javax.swing.*;
import java.awt.*;

/** @author Jason (darkdiplomat) */
public final class ServerActionButtons extends JComponent {

    private static final long serialVersionUID = 1785578261083028370L;
    private final JButton stop;
    private final JButton start;
    private final JButton restart;
    private final JButton kill;

    public ServerActionButtons(ServerButtonsAndRam buttram) {
        setBackground(Color.DARK_GRAY);
        Dimension size = new Dimension(60, 100);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(start = new ServerStartButton(this), this);
        add(stop = new ServerStopButton(this), this);
        add(restart = new ServerRestartButton(this), this);
        add(kill = new ServerKillButton(this), this);
        setVisible(true);
    }

    public final void setState() {
        boolean running = ControlRoom.isServerRunning();

        start.setEnabled(!running);
        stop.setEnabled(running);
        restart.setEnabled(running);
        kill.setEnabled(running);
    }
}
