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
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

/** @author Jason (darkdiplomat) */
public final class WebLinkWarning extends JPanel {
    private static final long serialVersionUID = 1981671238465892255L;
    private static final String unsafe = "The link %s is potentially unsafe.";
    private static final String sure = "Are you sure you want to open the link?";
    private final URI link;
    private final JWindow popup;

    public WebLinkWarning(URI link) {
        this.link = link;
        popup = new JWindow();
        setPreferredSize(new Dimension(link.toString().length() * 12, 60));
        setVisible(true);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton open = new JButton("Open Link");
        open.setFont(new Font("Verdana", 0, 8));
        open.setSize(75, 35);
        open.setLocation(5, 50);
        open.addMouseListener(new CloseWarning(true));
        open.setVisible(true);

        JButton kill = new JButton("Close");
        kill.setFont(new Font("Verdana", 0, 8));
        kill.setSize(60, 35);
        kill.setLocation(80, 50);
        kill.addMouseListener(new CloseWarning(false));
        kill.setVisible(true);

        buttonPanel.add(open);
        buttonPanel.add(kill);
        add(buttonPanel, "South");
        popup.getContentPane().add(this);
        popup.pack();
        popup.setLocationRelativeTo((Component) null);
        popup.setVisible(true);
        popup.setAlwaysOnTop(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        int fontSize = 12;
        g.setFont(new Font("Verdana", 1, fontSize));
        g.setColor(Color.RED);
        String unsafeLink = String.format("The link %s is potentially unsafe.", new Object[]{ link.toString() });
        g.drawString(unsafeLink, 10, 10);
        g.drawString("Are you sure you want to open the link?", (getWidth() - "Are you sure you want to open the link?".length() * (fontSize / 2)) / 2, 22);
    }

    private final class CloseWarning extends MouseAdapter {
        private final boolean continueToLink;

        CloseWarning(boolean continueToLink) {
            this.continueToLink = continueToLink;
        }

        public void mouseClicked(MouseEvent e) {
            popup.dispose();
            if (continueToLink)
                if (Desktop.isDesktopSupported())
                    try {
                        Desktop.getDesktop().browse(link);
                    }
                    catch (IOException ioex) {
                    }
                else
                    try {
                        Runtime.getRuntime().exec("xdg-open ".concat(link.toString()));
                    }
                    catch (IOException ioex) {
                    }
        }
    }
}