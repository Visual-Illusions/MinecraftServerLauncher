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
package net.visualillusionsent.minecraft.server.launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/** @author Jason (darkdiplomat) */
public final class StandardInReader extends Thread {

    private final BufferedReader reader;
    private final boolean sysout;

    public StandardInReader(InputStream is, boolean sysout) {
        this.reader = new BufferedReader(new InputStreamReader(is));
        this.sysout = sysout;
    }

    public final void run() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(">")) { // Stupid CraftBukkit thing...
                    continue;
                }
                else if (sysout) {
                    ControlRoom.addLineToQueue(escapeHTML(line), null);

                }
                else {
                    ControlRoom.addLineToQueue(escapeHTML(line), null);
                }
            }
        }
        catch (IOException e) {
        }
    }

    private final String escapeHTML(String input) {
        String output = input.replaceAll("(?i)&([0-9A-FK-OR])", "\u00A7$1");
        output = output.replace("<", "&lt;");
        output = output.replace(">", "&gt;");
        output = output.replace(" ", "&nbsp;");

        return output;
    }
}
