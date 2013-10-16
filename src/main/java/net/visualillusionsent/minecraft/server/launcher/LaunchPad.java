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
 * NOT affilated with, endorsed by, or sponsored by Mojang AB or Notch Development AB.
 */
package net.visualillusionsent.minecraft.server.launcher;

import net.visualillusionsent.minecraft.server.launcher.controller.jvm.JVMOptions;

import java.util.logging.Level;

/** @author Jason (darkdiplomat) */
public final class LaunchPad {
    private Rocket rocket;

    public LaunchPad(ControlRoom ctrl) {
    }

    public final void startServer() {
        if (ControlRoom.getServerInfo() == null) {
            ControlRoom.output(Level.WARNING, "No server path selected...");
            return;
        }
        else if (ControlRoom.isServerRunning()) {
            ControlRoom.output(Level.WARNING, "Server already running...");
            ControlRoom.button_menu_state();
            return;
        }
        try {
            ControlRoom.output(Level.INFO, "Starting Server...");
            ControlRoom.output(Level.INFO, "Type: " + ControlRoom.getServerInfo().getServerType() + " JarPath: " + ControlRoom.getServerInfo().getFullPath());

            rocket = new Rocket(this);
            rocket.start();
            ControlRoom.button_menu_state();
        }
        catch (Exception ex) {
            ControlRoom.output(Level.SEVERE, "Server Start failure...", ex);
            ControlRoom.button_menu_state();
        }
    }

    public final boolean serverRunning() {
        return rocket != null && rocket.isAlive();
    }

    public final void stopServer(boolean restart) {
        try {
            if (serverRunning()) {
                rocket.signalServer("stop");
                new StopServer(restart).start();
            }
        }
        catch (Exception ex) {
            ControlRoom.output(Level.SEVERE, "Server Stop failure...", ex);
        }
    }

    public final void restartServer() {
        stopServer(true);
        ControlRoom.button_menu_state();
    }

    public final void killServer(boolean buttonCall) {
        rocket.kill();
        if (buttonCall) {
            ControlRoom.button_menu_state();
        }
    }

    public final String getVMArguments() {
        JVMOptions options = ControlRoom.getJVMOptions();
        String vmArgs = "";
        if (options.getConcMarkSweepGC()) {
            vmArgs = vmArgs.concat("-XX:+UseConcMarkSweepGC ");
        }
        if (options.getCMSIncrementalPacing()) {
            vmArgs = vmArgs.concat("-XX:+CMSIncrementalPacing ");
        }
        if (options.getAggressiveOpts()) {
            vmArgs = vmArgs.concat("-XX:+AggressiveOpts ");
        }
        if (options.getGCThreads() > 1) {
            vmArgs = vmArgs.concat(String.format("-XX:ParallelGCThreads=%d ", options.getGCThreads()));
        }
        vmArgs = vmArgs.concat(options.getExtra());
        return vmArgs;
    }

    private final class StopServer extends Thread {
        private final boolean restart;

        public StopServer(boolean restart) {
            this.restart = restart;
        }

        public final void run() {
            int retry = 0;

            while (serverRunning() && retry < 15) {
                try {
                    sleep(1000);
                    retry++;
                }
                catch (InterruptedException e) {
                }
            }
            if (retry >= 15 && serverRunning()) {
                ControlRoom.output(Level.WARNING, "Server appears to not be responding... Force quiting server...");
                killServer(false);
            }
            if (restart) {
                startServer();
            }
        }
    }

    public void sendCommand(String action) {
        rocket.signalServer(action);
    }

    final void nullRocket() {
        rocket = null;
    }
}
