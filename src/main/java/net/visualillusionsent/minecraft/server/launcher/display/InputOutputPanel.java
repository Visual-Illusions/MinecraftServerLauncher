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
package net.visualillusionsent.minecraft.server.launcher.display;

import net.visualillusionsent.minecraft.server.launcher.CentralPanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/** @author Jason (darkdiplomat) */
public final class InputOutputPanel extends JPanel {

    private static final long serialVersionUID = 8L;
    private final LogPanel log;
    private final LogTextPanelControl ltpc;

    public InputOutputPanel(CentralPanel central) {
        TitledBorder title_border = new TitledBorder(new EtchedBorder(), "Server I/O");
        title_border.setTitleColor(Color.LIGHT_GRAY);
        this.setBorder(title_border);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 350));
        this.setMinimumSize(new Dimension(400, 350));
        this.log = new LogPanel(this);
        this.add(log, BorderLayout.CENTER);
        this.add(new InputPanel(this), BorderLayout.SOUTH);
        this.add(ltpc = new LogTextPanelControl(this), BorderLayout.NORTH);
    }

    public final LogPanel getLogPanel() {
        return this.log;
    }

    public final LogTextPanelControl getLTPControl() {
        return this.ltpc;
    }
}
