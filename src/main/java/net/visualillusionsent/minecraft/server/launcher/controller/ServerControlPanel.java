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

import net.visualillusionsent.minecraft.server.launcher.CentralPanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/** @author Jason (darkdiplomat) */
public final class ServerControlPanel extends JPanel {

    private static final long serialVersionUID = 5228591715112978024L;
    public final ServerButtonsAndRam sbar;

    public ServerControlPanel(CentralPanel central) {
        this.setLayout(new BorderLayout());
        TitledBorder title_border = new TitledBorder(new EtchedBorder(), "Server Setting and Options");
        title_border.setTitleColor(Color.LIGHT_GRAY);
        this.setBorder(title_border);
        this.setBackground(Color.DARK_GRAY);
        this.sbar = new ServerButtonsAndRam(this);
        this.add(sbar, BorderLayout.NORTH);
        this.add(new ServerOptionsPanel(this), BorderLayout.CENTER);
    }

    public final ServerButtonsAndRam getButtonsAndRam() {
        return sbar;
    }

}
