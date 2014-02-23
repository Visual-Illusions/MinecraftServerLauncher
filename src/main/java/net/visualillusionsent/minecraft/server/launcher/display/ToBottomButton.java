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

import net.visualillusionsent.minecraft.server.launcher.ButtonFontConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Jason (darkdiplomat)
 */
public class ToBottomButton extends JButton implements MouseListener {
    private final LogPanel logPanel;

    public ToBottomButton(InputOutputPanel iopanel) {
        super("Return to Bottom");
        this.setSelected(true);
        this.setFont(ButtonFontConstants.BUTTON_MONO_PLAIN_8.getFont());
        this.setAlignmentX(LEFT_ALIGNMENT);
        Dimension size = new Dimension(20, 12);
        this.setSize(size);
        this.setEnabled(true);
        this.setVisible(true);
        this.addMouseListener(this);
        this.logPanel = iopanel.getLogPanel();
    }

    @Override
    public final void mouseClicked(MouseEvent e) {
        BoundedRangeModel listModel = logPanel.getVerticalScrollBar().getModel();
        logPanel.getVerticalScrollBar().setValue(listModel.getMaximum());
    }

    @Override
    public final void mousePressed(MouseEvent e) {
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
    }

    @Override
    public final void mouseExited(MouseEvent e) {
    }
}
