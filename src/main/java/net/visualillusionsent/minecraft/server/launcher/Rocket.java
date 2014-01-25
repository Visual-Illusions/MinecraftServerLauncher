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
package net.visualillusionsent.minecraft.server.launcher;

import net.visualillusionsent.minecraft.server.launcher.info.ServerInfo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;

/** @author Jason (darkdiplomat) */
public final class Rocket extends Thread {
    private final LaunchPad pad;
    private Process server;
    private Thread serverout;
    private Thread serverlog;
    private OutputStream servin;

    public Rocket(LaunchPad pad) {
        this.pad = pad;
    }

    public final void run() {
        try {
            ControlRoom.output(Level.INFO, "Starting Server with " + ControlRoom.getRAM() + "MB RAM & VM Args: " + pad.getVMArguments() + " & Minecraft Args: " + ControlRoom.getMinecraftOptions());
            ServerInfo info = ControlRoom.getServerInfo();
            server = Runtime.getRuntime().exec(String.format("java %s -Xms256M -Xmx%dM -jar %s %s %s", pad.getVMArguments(), ControlRoom.getRAM(), info.getJarName(), info.getArguments(), ControlRoom.getMinecraftOptions()), null, new File(info.getDirectory()));
            serverout = new StandardInReader(server.getInputStream(), true);
            serverlog = new StandardInReader(server.getErrorStream(), false);
            servin = server.getOutputStream();
            serverout.start();
            serverlog.start();
            int result = server.waitFor();
            if (result != 0) {
                ControlRoom.output(Level.WARNING, "Server Terminated with exit status: " + result);
            }
            else {
                ControlRoom.output(Level.INFO, "Server terminated normally.");
            }
            kill();
        }
        catch (IOException e) {
            ControlRoom.output(Level.SEVERE, "Failed to launch Server...", e);
        }
        catch (InterruptedException e) {
            ControlRoom.output(Level.WARNING, "Server process thread went down.");
        }
    }

    public final void signalServer(String cmd) {
        try {
            servin.write(cmd.concat("\n").getBytes());
            servin.flush();
        }
        catch (Exception ex) {
            ControlRoom.output(Level.SEVERE, "Server Output failure...", ex);
        }
    }

    public final void kill() {
        if (server != null) {
            server.destroy();
            server = null;
        }
        this.serverout.interrupt();
        this.serverlog.interrupt();
        this.interrupt();
        pad.nullRocket();
        ControlRoom.button_menu_state();
    }
}
