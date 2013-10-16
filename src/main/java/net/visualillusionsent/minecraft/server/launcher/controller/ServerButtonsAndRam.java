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
package net.visualillusionsent.minecraft.server.launcher.controller;

import net.visualillusionsent.minecraft.server.launcher.controller.actions.ServerActionButtons;

import javax.swing.*;
import java.awt.*;

/** @author Jason (darkdiplomat) */
public final class ServerButtonsAndRam extends JPanel {
    private static final long serialVersionUID = 2213474889691135543L;
    private final ServerActionButtons sab;
    private final RAMSetPanel ram_panel;

    public ServerButtonsAndRam(ServerControlPanel scp) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);
        this.setSize(100, 50);
        this.sab = new ServerActionButtons(this);
        this.add(sab, BorderLayout.CENTER);
        this.ram_panel = new RAMSetPanel(this);
        this.add(ram_panel, BorderLayout.SOUTH);
    }

    public final ServerActionButtons getActionButtons() {
        return sab;
    }

    public final RAMSetPanel getRAMBox() {
        return ram_panel;
    }
}
