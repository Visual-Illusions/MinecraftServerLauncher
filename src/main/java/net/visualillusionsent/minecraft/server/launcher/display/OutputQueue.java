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

import java.util.LinkedList;
import java.util.NoSuchElementException;

/** @author Jason (darkdiplomat) */
public class OutputQueue {
    private LinkedList<String> queue;

    public OutputQueue() {
        queue = new LinkedList<String>();
    }

    public final void add(String line) {
        synchronized (queue) {
            queue.add(line);
            queue.notify();
        }
    }

    public final String next() {
        String line = null;
        synchronized (queue) {
            if (!hasNext()) {
                try {
                    queue.wait();
                }
                catch (InterruptedException ex) {
                    return "";
                }
            }
        }
        try {
            line = queue.getFirst();
            queue.removeFirst();
        }
        catch (NoSuchElementException nsee) {
            return "";
        }
        return line;
    }

    public final void clear() {
        synchronized (queue) {
            queue.clear();
        }
    }

    public final boolean hasNext() {
        return queue.size() > 0;
    }
}
