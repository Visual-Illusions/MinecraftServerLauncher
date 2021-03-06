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
package net.visualillusionsent.minecraft.server.launcher.menu.file;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/** @author Jason (darkdiplomat) */
final class ExitMenuItem extends JMenuItem implements ActionListener {

    private static final long serialVersionUID = 777653718428986023L;
    private final FileMenu fmenu;

    ExitMenuItem(FileMenu fmenu) {
        super("Exit");
        this.fmenu = fmenu;
        this.setMnemonic(KeyEvent.VK_X);
        this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));
        this.addActionListener(this);
    }

    public final void actionPerformed(ActionEvent e) {
        WindowEvent windowClosing = new WindowEvent(fmenu.getMain().getCentral(), WindowEvent.WINDOW_CLOSING);
        fmenu.getMain().getCentral().dispatchEvent(windowClosing);
    }

}
