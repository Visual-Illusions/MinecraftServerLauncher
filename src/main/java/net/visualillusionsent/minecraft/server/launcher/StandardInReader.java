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
                    ControlRoom.addLineToQueue("[SYS-OUT] ".concat(escapeHTML(line)), null);

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
