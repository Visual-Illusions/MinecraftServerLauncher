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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/** @author Jason (darkdiplomat) */
final class ExitMenuItem extends JMenuItem implements ActionListener {

    private static final long serialVersionUID = 777653718428986023L;
    private final FileMenu fmenu;

    ExitMenuItem(FileMenu fmenu) {
        super("Exit");
        this.fmenu = fmenu;
        this.addActionListener(this);
    }

    public final void actionPerformed(ActionEvent e) {
        WindowEvent windowClosing = new WindowEvent(fmenu.getMain().getCentral(), WindowEvent.WINDOW_CLOSING);
        fmenu.getMain().getCentral().dispatchEvent(windowClosing);
    }

}
