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
public final class BugReportMenuItem extends JMenuItem implements ActionListener {

    private static final long serialVersionUID = 5043688797233877669L;
    private URI report;

    public BugReportMenuItem(HelpMenu helpMenu) {
        super("Report a Bug");
        this.addActionListener(this);
        try {
            report = new URI("https://github.com/Visual-Illusions/MinecraftServerLauncher/issues");
        }
        catch (URISyntaxException e) {
        }
    }

    public final void actionPerformed(ActionEvent e) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(report);
                return; // if supported, don't move on
            }
            catch (IOException ioex) {
            }
        }
        try { // For non-Windows/Gnome users
            Runtime.getRuntime().exec("xdg-open ".concat(report.toString()));
        }
        catch (IOException ioex) {
            System.out.println("Failed to browse to: '" + report + "'. Possible unsupported Desktop...");
        }
    }
}
