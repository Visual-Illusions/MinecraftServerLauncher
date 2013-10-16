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
