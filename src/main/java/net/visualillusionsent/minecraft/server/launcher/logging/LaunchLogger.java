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
package net.visualillusionsent.minecraft.server.launcher.logging;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @author Jason (darkdiplomat) */
public final class LaunchLogger extends Logger {
    private FileHandler fhand;

    public LaunchLogger() {
        super("VI-MCSL", null);
        initialize();
    }

    private final void initialize() {
        this.setLevel(Level.ALL);
        try {
            LoggerFormat lForm = new LoggerFormat();

            fhand = new FileHandler(ControlRoom.getAppData("vimcsl.%g.log"), 1966080, 5, true);
            fhand.setLevel(Level.ALL);
            fhand.setFormatter(lForm);
            this.addHandler(fhand);

            ConsoleHandler chand = new ConsoleHandler();
            chand.setLevel(Level.ALL);
            chand.setFormatter(lForm);
            this.addHandler(chand);
        }
        catch (IOException e) {
        }
    }

    public final void close() {
        fhand.close();
        this.removeHandler(fhand);
        this.setLevel(Level.OFF);
    }
}
