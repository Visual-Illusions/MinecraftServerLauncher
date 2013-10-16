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
package net.visualillusionsent.minecraft.server.launcher.display;

import javax.swing.*;

/** @author Jason (darkdiplomat) */
public final class LogPanel extends JScrollPane {

    private static final long serialVersionUID = -1982612246485683075L;
    private LogTextPanel logtext;

    public LogPanel(InputOutputPanel iopanel) {
        logtext = new LogTextPanel(this);
        this.setViewportView(logtext);
    }

    public LogTextPanel getLogTextPanel() {
        return logtext;
    }

}
