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
package net.visualillusionsent.minecraft.server.launcher;

import net.visualillusionsent.minecraft.server.launcher.controller.ServerControlPanel;
import net.visualillusionsent.minecraft.server.launcher.display.InputOutputPanel;
import net.visualillusionsent.minecraft.server.launcher.menu.MainMenuBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;

/** @author Jason (darkdiplomat) */
public final class CentralPanel extends JFrame {
    private static final long serialVersionUID = 1L;
    private final ControlRoom crtl;
    private final InputOutputPanel iopanel;
    private final ServerControlPanel controlPanel;
    private final MainMenuBar main_menu;
    private final InfoPanel info;

    public CentralPanel(ControlRoom crtl) {
        super("Visual Illusions Minecraft Server Launcher v" + crtl.getVersion() + " " + crtl.getCleanStatus());
        this.crtl = crtl;
        this.setLayout(new BorderLayout());
        this.main_menu = new MainMenuBar(this);
        this.setJMenuBar(main_menu);
        this.info = new InfoPanel(this);
        this.add(info, BorderLayout.NORTH);
        this.iopanel = new InputOutputPanel(this);
        this.add(iopanel, BorderLayout.CENTER);
        this.controlPanel = new ServerControlPanel(this);
        this.add(controlPanel, BorderLayout.WEST);
        this.add(new CopyrightPanel(this), BorderLayout.SOUTH);
        Image favicon = null;
        try {
            favicon = ImageIO.read(CentralPanel.class.getResourceAsStream("/resources/img/favicon.png"));
        }
        catch (IOException ex) {
            ControlRoom.log(Level.SEVERE, "Failed to get resource: 'favicon.png'", ex);
        }

        if (favicon != null) {
            this.setIconImage(favicon);
        }

        this.pack();
        this.setVisible(true);
        this.addWindowListener(new LauncherWindowAdapter(this));
        this.setResizable(true);
        this.setLocationRelativeTo((Component) null);
        this.setMinimumSize(new Dimension(700, 500));
    }

    public final ControlRoom hailControlRoom() {
        return crtl;
    }

    public final InputOutputPanel getIOPanel() {
        return iopanel;
    }

    public final ServerControlPanel getControlPanel() {
        return controlPanel;
    }

    public final MainMenuBar getMainMenu() {
        return main_menu;
    }

    public final void repaintInfo() {
        this.info.setLabelText();
    }
}
