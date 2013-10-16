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
package net.visualillusionsent.minecraft.server.launcher.menu.help;

import net.visualillusionsent.minecraft.server.launcher.menu.MainMenuBar;

import javax.swing.*;

/** @author Jason (darkdiplomat) */
public final class HelpMenu extends JMenu {
    private static final long serialVersionUID = 237368492526804154L;
    private final MainMenuBar main;

    public HelpMenu(MainMenuBar mmb) {
        super("Help");
        this.main = mmb;

        this.add(new WikiMenuItem(this));
        this.add(new AboutMenuItem(this));
        this.add(new BugReportMenuItem(this));
    }

    public final MainMenuBar getMain() {
        return main;
    }
}
