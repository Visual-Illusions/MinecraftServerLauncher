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
package net.visualillusionsent.minecraft.server.launcher.menu.server;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;
import net.visualillusionsent.minecraft.server.launcher.menu.MainMenuBar;

import javax.swing.*;

/** @author Jason (darkdiplomat) */
public final class ServerMenu extends JMenu {
    private static final long serialVersionUID = -5611170037228493548L;
    private final JMenuItem start, stop, restart, kill;

    public ServerMenu(MainMenuBar mmb) {
        super("Server");
        this.setSize(25, 20);
        this.start = new ServerStartMenuItem(this);
        this.stop = new ServerStopMenuItem(this);
        this.restart = new ServerRestartMenuItem(this);
        this.kill = new ServerKillMenuItem(this);
        this.add(start);
        this.add(stop);
        this.add(restart);
        this.add(kill);
        this.add(new ServerOpenPathMenuItem(this));
    }

    public final void setRunningState() {
        boolean running = ControlRoom.isServerRunning();
        this.start.setEnabled(!running);
        this.stop.setEnabled(running);
        this.restart.setEnabled(running);
        this.kill.setEnabled(running);
    }
}
