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
package net.visualillusionsent.minecraft.server.launcher.menu.help;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/** @author Jason (darkdiplomat) */
public final class WikiMenuItem extends JMenuItem implements ActionListener {

    private static final long serialVersionUID = 5043688797233877669L;
    private URI wiki;

    public WikiMenuItem(HelpMenu helpMenu) {
        super("Wiki Page");
        this.addActionListener(this);
        try {
            wiki = new URI("http://wiki.visualillusionsent.net/Minecraft_Server_Launcher");
        }
        catch (URISyntaxException e) {
        }
    }

    public final void actionPerformed(ActionEvent e) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(wiki);
                return; // if supported, don't move on
            }
            catch (IOException ioex) {
            }
        }
        try {
            Runtime.getRuntime().exec("xdg-open ".concat(wiki.toString()));
        }
        catch (IOException ioex) {
            System.out.println("Failed to show file: '" + wiki + "'. Possible unsupported Desktop...");
        }
    }
}
