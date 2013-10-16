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
package net.visualillusionsent.minecraft.server.launcher.display;

/** @author Jason (darkdiplomat) */
public final class OutputThread extends Thread {
    private final OutputQueue queue;
    private final LogTextPanel log_text_panel;

    public OutputThread(OutputQueue queue, LogTextPanel log_text_panel) {
        super("MCSL-Output-Thread");
        this.setDaemon(true);
        this.queue = queue;
        this.log_text_panel = log_text_panel;
    }

    public final void run() {
        while (true) {
            try {
                String line = queue.next();
                log_text_panel.insertString(line);
                sleep(2L);
            }
            catch (Exception ex) {

            }
        }
    }
}
