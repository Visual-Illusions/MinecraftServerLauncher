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
package net.visualillusionsent.minecraft.server.launcher.display;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;
import net.visualillusionsent.minecraft.server.launcher.popups.EditPopupMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/** @author Jason (darkdiplomat) */
public final class InputPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    private static final long serialVersionUID = 8152481354122705521L;
    private final TextField textField;
    private final LinkedList<String> previous = new LinkedList<String>();
    private int prevIndex = -1;

    public InputPanel(InputOutputPanel iopanel) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 20));
        this.setMinimumSize(new Dimension(400, 20));
        this.setBackground(Color.DARK_GRAY);
        textField = new TextField();
        textField.addActionListener(this);
        textField.addMouseListener(this);
        textField.addKeyListener(this);
        textField.setVisible(true);
        this.add(textField, BorderLayout.CENTER);
    }

    public final void mousePressed(MouseEvent event) {
    }

    public final void mouseReleased(MouseEvent event) {
    }

    public final void mouseClicked(MouseEvent event) {
        new EditPopupMenu(event, true);
    }

    public final void mouseEntered(MouseEvent event) {
    }

    public final void mouseExited(MouseEvent event) {
    }

    public final void actionPerformed(ActionEvent event) {
        String action = this.textField.getText().trim();

        if (action.length() > 0) {
            ControlRoom.sendCommand(action);
            previous.addFirst(action);
        }
        this.textField.setText("");
        this.prevIndex = -1;
    }

    public final void keyTyped(KeyEvent e) {
    }

    public final void keyPressed(KeyEvent e) {
        if (!previous.isEmpty()) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if ((prevIndex + 1) < previous.size()) {
                    prevIndex++;
                    this.textField.setText(previous.get(prevIndex));
                    this.textField.setCaretPosition(textField.getText().length());
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if ((prevIndex - 1) > -1) {
                    prevIndex--;
                    this.textField.setText(previous.get(prevIndex));
                    this.textField.setCaretPosition(textField.getText().length());
                }
                else {
                    this.textField.setText("");
                    prevIndex = -1;
                }
            }
        }
    }

    public final void keyReleased(KeyEvent e) {
    }

}
