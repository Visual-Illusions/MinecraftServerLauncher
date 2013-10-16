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
package net.visualillusionsent.minecraft.server.launcher.popups;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.MouseEvent;

/** @author Jason (darkdiplomat) */
public final class EditPopupMenu extends JPopupMenu {

    private static final long serialVersionUID = 4433630399359802443L;

    public EditPopupMenu(MouseEvent event, boolean editable) {
        if (event.getButton() == MouseEvent.BUTTON3) {

            JMenuItem menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
            menuItem.setText("Copy");
            this.add(menuItem);

            if (editable) {
                menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
                menuItem.setText("Cut");
                this.add(menuItem);

                menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
                menuItem.setText("Paste");
                this.add(menuItem);
            }

            this.show(event.getComponent(), event.getX(), event.getY());
        }
    }
}
