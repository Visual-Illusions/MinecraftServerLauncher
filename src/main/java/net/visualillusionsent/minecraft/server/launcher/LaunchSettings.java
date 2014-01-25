/*
 * This file is part of VisualIllusionsMinecraftServerLauncher.
 *
 * Copyright © 2013-2014 Visual Illusions Entertainment
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
 * Copyright &copy; 2009-2014 Mojang AB
 * "Minecraft" is a trademark of Notch Development AB
 *
 * Visual Illusions Minecraft Server Launcher and Visual Illusions Entertainment are
 * NOT affilated with, endorsed by, or sponsored by Mojang AB or Notch Development AB.
 */
package net.visualillusionsent.minecraft.server.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/** @author Jason (darkdiplomat) */
public enum LaunchSettings {
    $;

    private final Properties props;
    private final File prefFile;

    private LaunchSettings() {
        props = new Properties();

        prefFile = new File(ControlRoom.getAppData("settings.cfg"));

        if (!prefFile.exists()) {
            try {
                prefFile.createNewFile();
            }
            catch (IOException e) {
            }
        }

        try {
            props.load(new FileInputStream(prefFile));
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }

    }

    public final void saveProps() {
        props.setProperty("ram.index", String.valueOf(ControlRoom.getRAMIndex()));
        props.setProperty("jvm.custom.args", ControlRoom.getJVMOptions().getExtra());
        props.setProperty("conc.mark.sweep.gc", String.valueOf(ControlRoom.getJVMOptions().getConcMarkSweepGC()));
        props.setProperty("cms.incremental.pacing", String.valueOf(ControlRoom.getJVMOptions().getCMSIncrementalPacing()));
        props.setProperty("aggressive.opts", String.valueOf(ControlRoom.getJVMOptions().getAggressiveOpts()));
        props.setProperty("minecraft.args", ControlRoom.getMinecraftOptions());
        try {
            props.store(new FileOutputStream(prefFile), null);
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
    }

    public static final int getRAMSetting() {
        if ($.props.containsKey("ram.index")) {
            return Integer.parseInt($.props.getProperty("ram.index"));
        }
        return 2;
    }

    public static final String getJVMCustomArgs() {
        if ($.props.containsKey("jvm.custom.args")) {
            return $.props.getProperty("jvm.custom.args");
        }
        return "";
    }

    public static final boolean getUseConcMarkSweepGC() {
        if ($.props.containsKey("conc.mark.sweep.gc")) {
            return Boolean.parseBoolean($.props.getProperty("conc.mark.sweep.gc"));
        }
        return true;
    }

    public static final boolean getCMSIncrementalPacing() {
        if ($.props.containsKey("cms.incremental.pacing")) {
            return Boolean.parseBoolean($.props.getProperty("cms.incremental.pacing"));
        }
        return true;
    }

    public static final boolean getAggressiveOpts() {
        if ($.props.containsKey("aggressive.opts")) {
            return Boolean.parseBoolean($.props.getProperty("aggressive.opts"));
        }
        return true;
    }

    public static final String getCustomMCArgs() {
        if ($.props.containsKey("minecraft.args")) {
            return $.props.getProperty("minecraft.args");
        }
        return "";
    }

    public static final String getPreviousDirectory() {
        if ($.props.containsKey("previous.directory")) {
            return $.props.getProperty("previous.directory");
        }
        return ControlRoom.getAppData("");
    }

    public static final void setPreviousDirectory(String previous_directory) {
        $.props.setProperty("previous.directory", previous_directory);
    }
}
