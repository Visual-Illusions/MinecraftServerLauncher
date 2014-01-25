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
package net.visualillusionsent.minecraft.server.launcher;

import javax.swing.*;
import java.awt.*;

/** @author Jason (darkdiplomat) */
public final class CopyrightPanel extends JPanel {

    private static final long serialVersionUID = -5031046066912599520L;

    public CopyrightPanel(CentralPanel central) {
        Dimension size = new Dimension(680, 20);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
        this.add(copyrightLabel());
    }

    private final JLabel copyrightLabel() {
        JLabel copyright_lb = new JLabel();
        copyright_lb.setForeground(Color.LIGHT_GRAY);
        copyright_lb.setFont(new Font(Font.SERIF, Font.BOLD, 10));
        copyright_lb.setText("<html><p style=\"text-align:center\">Copyright &copy; 2013 Visual Illusions Entertainment -- NOT affilated with, endorsed by, or sponsored by Mojang AB</p></html>");
        copyright_lb.setLocation(0, 0);
        return copyright_lb;
    }
}
