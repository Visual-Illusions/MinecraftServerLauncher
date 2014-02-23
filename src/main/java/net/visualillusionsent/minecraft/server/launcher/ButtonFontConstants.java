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

import java.awt.*;

import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;

/**
 * @author Jason (darkdiplomat)
 */
public enum ButtonFontConstants {

    BUTTON_MONO_PLAIN_8(MONOSPACED, PLAIN, 8),
    BUTTON_MONO_PLAIN_12(MONOSPACED, PLAIN, 12),;

    private Font font;

    private ButtonFontConstants(String name, int style, int size) {
        this.font = new Font(name, style, size);
    }

    public Font getFont() {
        return font;
    }
}
