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
package net.visualillusionsent.minecraft.server.launcher.menu.file;

import net.visualillusionsent.minecraft.server.launcher.menu.MainMenuBar;

import javax.swing.*;

public final class FileMenu extends JMenu {
    private static final long serialVersionUID = 237368492526804154L;
    private final MainMenuBar main;

    public FileMenu(MainMenuBar mmb) {
        super("File");
        this.main = mmb;

        this.add(new FindMinecraftServerJarMenuItem(this));
        this.add(new ExitMenuItem(this));
    }

    public final MainMenuBar getMain() {
        return main;
    }
}
