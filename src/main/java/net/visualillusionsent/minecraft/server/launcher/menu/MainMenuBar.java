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
package net.visualillusionsent.minecraft.server.launcher.menu;

import net.visualillusionsent.minecraft.server.launcher.CentralPanel;
import net.visualillusionsent.minecraft.server.launcher.menu.file.FileMenu;
import net.visualillusionsent.minecraft.server.launcher.menu.help.HelpMenu;
import net.visualillusionsent.minecraft.server.launcher.menu.server.ServerMenu;

import javax.swing.*;

/** @author Jason (darkdiplomat) */
public final class MainMenuBar extends JMenuBar {
    private static final long serialVersionUID = 7685767075928403317L;
    private final FileMenu file_menu;
    private final ServerMenu server_menu;
    private final CentralPanel central;

    public MainMenuBar(CentralPanel central) {
        this.central = central;
        file_menu = new FileMenu(this);
        this.add(file_menu);
        server_menu = new ServerMenu(this);
        this.add(server_menu);
        this.add(new HelpMenu(this));
    }

    public final void setRunningState() {
        server_menu.setRunningState();
    }

    public final FileMenu getFileMenu() {
        return file_menu;
    }

    public final CentralPanel getCentral() {
        return central;
    }

    public final ServerMenu getServerMenu() {
        return server_menu;
    }
}
