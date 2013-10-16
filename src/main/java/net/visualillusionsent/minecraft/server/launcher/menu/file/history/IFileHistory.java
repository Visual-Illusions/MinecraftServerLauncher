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
package net.visualillusionsent.minecraft.server.launcher.menu.file.history;

import javax.swing.*;
import java.awt.*;

/**
 * Interface that must be implemented by a GUI applcation frame
 * that wants to use the FileHistory class.
 *
 * @author Klaus Berg
 * @since JDK 1.2
 */
public interface IFileHistory {

    /**
     * Get the application name to identify the configuration file in the
     * the USER_HOME directory. This name should be unique in this directory.
     *
     * @return the application name
     */
    public String getApplicationName();

    /**
     * Get a handle to the frame's file menu.
     *
     * @return the frame's file menu
     */
    public JMenu getFileMenu();

    /**
     * Return the size of the main application frame.
     * It is used to center the file history maintenance window.
     *
     * @return the main GUI frame's size
     */
    public Dimension getSize();

    /**
     * Return the main application frame.
     * It is used to center the file history maintenance window.
     *
     * @return the main GUI frame
     */
    public JFrame getFrame();

    /**
     * Perform the load file activity.
     *
     * @param path
     *         the pathname of the loaded file
     */
    public void loadFile(String pathname);
}
