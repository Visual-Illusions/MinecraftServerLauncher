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
package net.visualillusionsent.minecraft.server.launcher.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** @author Jason (darkdiplomat) */
public final class ClearPanelButton extends JButton implements MouseListener {

    private static final long serialVersionUID = 4228721679109169330L;
    private final LogTextPanel log_text;

    public ClearPanelButton(InputOutputPanel iopanel) {
        super("Clear Log");
        Dimension size = new Dimension(20, 25);
        this.setSize(size);
        this.addMouseListener(this);
        this.setEnabled(true);
        this.setVisible(true);
        this.log_text = iopanel.getLogPanel().getLogTextPanel();
    }

    public final void mouseClicked(MouseEvent event) {
        log_text.clear();
    }

    public final void mouseEntered(MouseEvent event) {
    }

    public final void mouseExited(MouseEvent event) {
    }

    public final void mousePressed(MouseEvent event) {
    }

    public final void mouseReleased(MouseEvent event) {
    }

}
