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
package net.visualillusionsent.minecraft.server.launcher.controller.jvm;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;
import net.visualillusionsent.minecraft.server.launcher.LaunchSettings;
import net.visualillusionsent.minecraft.server.launcher.controller.ServerOptionsPanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/** @author Jason (darkdiplomat) */
public final class JVMOptions extends JPanel {

    private static final long serialVersionUID = -6811554756298520837L;
    private final JCheckBox sweepGC, cmsIncro, aggressiveOpts;
    private final JComboBox gcthreads;
    private final JVMExtraPanel extra;
    private final int maxProcess = Runtime.getRuntime().availableProcessors();

    public JVMOptions(ServerOptionsPanel sop) {
        TitledBorder title_border = new TitledBorder(new EtchedBorder(), "JVM Options");
        title_border.setTitleColor(Color.LIGHT_GRAY);
        setBorder(title_border);
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        setVisible(true);
        Dimension size = new Dimension(220, 200);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        sweepGC = getCheckBox("UseConcMarkSweepGC", 20, LaunchSettings.getUseConcMarkSweepGC());
        cmsIncro = getCheckBox("CMSIncrementalPacing", 40, LaunchSettings.getCMSIncrementalPacing());
        aggressiveOpts = getCheckBox("AggressiveOpts", 60, LaunchSettings.getAggressiveOpts());
        extra = new JVMExtraPanel(this);
        gcthreads = getGCThreadBox();

        add(sweepGC);
        add(cmsIncro);
        add(aggressiveOpts);
        add(extra);
        add(getGCThreadList());
        ControlRoom.setJVMOptions(this);
    }

    private JCheckBox getCheckBox(String name, int yOffset, boolean preSelect) {
        JCheckBox temp = new JCheckBox(name);
        temp.setBounds(5, yOffset, 200, 15);
        temp.setSelected(preSelect);
        temp.setBackground(Color.DARK_GRAY);
        temp.setForeground(Color.LIGHT_GRAY);
        return temp;
    }

    private JPanel getGCThreadList() {
        JPanel gcthreadpanel = new JPanel();
        TitledBorder title_border = new TitledBorder(new EtchedBorder(), "Parallel GC Threads");
        title_border.setTitleColor(Color.LIGHT_GRAY);
        gcthreadpanel.setBorder(title_border);
        gcthreadpanel.setBackground(Color.DARK_GRAY);
        gcthreadpanel.setLayout(null);
        gcthreadpanel.setVisible(true);
        gcthreadpanel.setBounds(35, 80, 160, 45);
        gcthreadpanel.add(gcthreads);

        return gcthreadpanel;
    }

    private JComboBox getGCThreadBox() {
        JComboBox gcThreadList = new JComboBox();
        gcThreadList.addItem("Default (1)");
        gcThreadList.addItem("Max (" + maxProcess + ")");
        int processors = Runtime.getRuntime().availableProcessors();
        for (int index = 2; index < processors; index++) {
            gcThreadList.addItem(String.valueOf(index));
        }
        gcThreadList.setMaximumRowCount(10);
        gcThreadList.setBounds(30, 15, 100, 20);
        gcThreadList.setSelectedIndex(1);
        gcThreadList.setLightWeightPopupEnabled(false);
        return gcThreadList;
    }

    public final boolean getConcMarkSweepGC() {
        return sweepGC.isSelected();
    }

    public final boolean getCMSIncrementalPacing() {
        return cmsIncro.isSelected();
    }

    public final boolean getAggressiveOpts() {
        return aggressiveOpts.isSelected();
    }

    public final int getGCThreads() {
        switch (gcthreads.getSelectedIndex()) {
            case 0:
                return 1;
            case 1:
                return maxProcess;
            default:
                return Integer.valueOf((String) gcthreads.getSelectedItem());
        }
    }

    public final String getExtra() {
        return extra.getJVMExtra();
    }
}
