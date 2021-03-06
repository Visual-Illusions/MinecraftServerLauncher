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
package net.visualillusionsent.minecraft.server.launcher.menu.file.history;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Provide a file history mechanism for the File menu of a parent frame).
 *
 * @author Klaus Berg (original author)
 * @author Jason (darkdiplomat)
 * @since JDK 1.2
 */
public final class FileHistory {

    private static final int MAX_ITEM_LEN = 60;
    private static String historyPath;
    private static int max_itemnames;
    private static ArrayList<String> itemnameHistory = new ArrayList<String>(max_itemnames);
    private static ArrayList<String> pathnameHistory = new ArrayList<String>(max_itemnames);

    private IFileHistory caller;
    private JMenu fileMenu;

    // CONSTRUCTOR: caller is the parent frame that hosts the file menu
    public FileHistory(IFileHistory caller, String historyPath) {
        this.caller = caller;
        this.historyPath = historyPath;
        String max_itemnames_str = System.getProperty("itemnames.max", "9");
        try {
            max_itemnames = Integer.parseInt(max_itemnames_str);
        }
        catch (NumberFormatException e) {
            System.err.println(e);
            e.printStackTrace();
            System.exit(1);
        }
        if (max_itemnames < 1) {
            max_itemnames = 9;
        }
        fileMenu = caller.getFileMenu();
    }

    /**
     * ****************************************************************
     * Initialize itemname and pathname arraylists from historySerFile. *
     * build up the additional entries in the File menu. *
     * *****************************************************************
     */
    public final void initFileMenuHistory() {
        if (new File(historyPath).exists()) {
            try {
                FileInputStream fis = new FileInputStream(historyPath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                int itemnameCount = ois.readInt();
                // if the user has reduced filehistory.size in the past: cut last items
                if (itemnameCount > max_itemnames) {
                    itemnameCount = max_itemnames;
                }
                if (itemnameCount > 0) {
                    fileMenu.addSeparator();
                }
                for (int i = 0; i < itemnameCount; i++) {
                    itemnameHistory.add((String) ois.readObject());
                    pathnameHistory.add((String) ois.readObject());
                    MenuItemWithFixedTooltip item = new MenuItemWithFixedTooltip((i + 1) + ": " + (String) itemnameHistory.get(i));
                    item.setToolTipText((String) pathnameHistory.get(i));
                    item.addActionListener(new ItemListener(i));
                    item.setMnemonic(KeyEvent.VK_1 + i);
                    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1 + i, InputEvent.ALT_DOWN_MASK));
                    fileMenu.add(item);
                }
                ois.close();
                fis.close();
            }
            catch (Exception e) {
                System.err.println("Trouble reading file history entries: " + e);
                e.printStackTrace();
            }
        }
    }

    /**
     * ********************************************************
     * Save itemname and pathname arraylists to historySerFile. *
     * *********************************************************
     */
    public void saveHistoryEntries() {
        try {
            FileOutputStream fos = new FileOutputStream(historyPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            int itemnameCount = itemnameHistory.size();
            oos.writeInt(itemnameCount);
            for (int i = 0; i < itemnameCount; i++) {
                oos.writeObject((String) itemnameHistory.get(i));
                oos.writeObject((String) pathnameHistory.get(i));
            }
            oos.flush();
            oos.close();
            fos.close();
        }
        catch (Exception e) {
            System.err.println("Trouble saving file history entries: " + e);
            e.printStackTrace();
        }
    }

    /**
     * ****************************************************************
     * Insert the last loaded pathname into the File menu if it is not *
     * present yet. Only max pathnames are shown (the max number can be *
     * set in Jmon.ini, default is 9). Every item starts with *
     * "<i>: ", where <i> is in the range [1..max]. *
     * The loaded itemname will become item number 1 in the list. *
     * *****************************************************************
     */
    public final void insertPathname(String pathname) {
        for (int k = 0; k < pathnameHistory.size(); k++) {
            if (((String) pathnameHistory.get(k)).equals(pathname)) {
                int index = fileMenu.getItemCount() - itemnameHistory.size() + k;
                fileMenu.remove(index);
                pathnameHistory.remove(k);
                itemnameHistory.remove(k);
                if (itemnameHistory.isEmpty()) {
                    // JSeparator is the last menu item at (index-1)
                    fileMenu.remove(index - 1);
                }
                insertPathname(pathname);
                return;
            }
        }
        if (itemnameHistory.isEmpty()) {
            fileMenu.addSeparator();
        }
        else {
            // remove all itemname entries to prepare for re-arrangement
            for (int i = fileMenu.getItemCount() - 1, j = 0; j < itemnameHistory.size(); i--, j++) {
                fileMenu.remove(i);
            }
        }
        if (itemnameHistory.size() == max_itemnames) {
            // fileList is full: remove last entry to get space for the first item
            itemnameHistory.remove(max_itemnames - 1);
            pathnameHistory.remove(max_itemnames - 1);
        }
        itemnameHistory.add(0, getItemname(pathname));
        pathnameHistory.add(0, pathname);
        for (int i = 0; i < itemnameHistory.size(); i++) {
            MenuItemWithFixedTooltip item = new MenuItemWithFixedTooltip((i + 1) + ": " + (String) itemnameHistory.get(i));
            item.setToolTipText((String) pathnameHistory.get(i));
            item.addActionListener(new ItemListener(i));
            fileMenu.add(item);
        }
    }

    /**
     * ****************************************************************
     * Process the file history list that is appended to the file menu: *
     * display a dialog to delete itemname items. *
     * *****************************************************************
     */
    public void processList() {
        final JDialog dialog = new JDialog(caller.getFrame(), "File History", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        final JList itemList = createItemList();
        Container c = dialog.getContentPane();
        JScrollPane scroller = new JScrollPane(itemList);
        scroller.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        c.add(scroller, BorderLayout.CENTER);
        JButton deleteB = new JButton("Delete");
        deleteB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] indicesToDelete = itemList.getSelectedIndices();
                if (indicesToDelete.length > 0) {
                    int oldFileHistorySize = itemnameHistory.size();
                    ArrayList<String> itemnames = new ArrayList<String>(oldFileHistorySize - indicesToDelete.length);
                    ArrayList<String> pathnames = new ArrayList<String>(oldFileHistorySize - indicesToDelete.length);
                    for (int i = 0; i < oldFileHistorySize; i++) {
                        boolean copyItem = true;
                        for (int j = 0; j < indicesToDelete.length; j++) {
                            if (i == indicesToDelete[j]) {
                                copyItem = false;
                                break;
                            }
                        }
                        if (copyItem) {
                            itemnames.add(itemnameHistory.get(i));
                            pathnames.add(pathnameHistory.get(i));
                        }
                    }
                    itemnameHistory = itemnames;
                    pathnameHistory = pathnames;
                    itemList.revalidate();
                    itemList.repaint();
                    // re-arrange file menu history
                    for (int i = fileMenu.getItemCount() - 1, j = 0; j < oldFileHistorySize; i--, j++) {
                        fileMenu.remove(i);
                    }
                    int lastIndex = fileMenu.getItemCount() - 1;
                    for (int i = 0; i < itemnameHistory.size(); i++) {
                        MenuItemWithFixedTooltip item = new MenuItemWithFixedTooltip((i + 1) + ": " + (String) itemnameHistory.get(i));
                        item.setToolTipText((String) pathnameHistory.get(i));
                        item.addActionListener(new ItemListener(i));
                        fileMenu.add(item);
                    }
                    if (itemnameHistory.isEmpty()) {
                        fileMenu.remove(lastIndex); // no items were added: remove JSeparator too
                    }
                }
            }
        });
        JButton closeB = new JButton("Close");
        closeB.setMaximumSize(deleteB.getPreferredSize());
        closeB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // dialog.hide();
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        JPanel buttonBox = new JPanel();
        buttonBox.setLayout(new BoxLayout(buttonBox, BoxLayout.Y_AXIS));
        buttonBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        buttonBox.add(Box.createVerticalStrut(10));
        buttonBox.add(deleteB);
        buttonBox.add(Box.createVerticalStrut(10));
        buttonBox.add(closeB);
        buttonBox.add(Box.createVerticalGlue());
        c.add(buttonBox, BorderLayout.EAST);
        dialog.pack();
        dialog.setSize(350, 200);
        // center dialog in parent frame
        Dimension parentSize = caller.getSize();
        Dimension mySize = dialog.getSize();
        dialog.setLocation(parentSize.width / 2 - mySize.width / 2, parentSize.height / 2 - mySize.height / 2);
        dialog.setVisible(true);
    }

    /**
     * *******************************************************
     * Return the itemname (abbreviated itemname if necessary) *
     * to be shown in the file menu open item list. *
     * A maximum of MAX_ITEM_LEN characters is used for the *
     * itemname because we do not want to make the JMenuItem *
     * entry too wide. *
     * ********************************************************
     */
    protected String getItemname(String pathname) {
        final char FILE_SEPARATOR = File.separatorChar;
        final int pathnameLen = pathname.length();
        // if the pathame is short enough: return whole pathname
        if (pathnameLen <= MAX_ITEM_LEN) {
            return pathname;
        }
        // if we have only one directory: return whole pathname
        // we will not cut to MAX_ITEM_LEN here
        if (pathname.indexOf(File.separator) == pathname.lastIndexOf(File.separator)) {
            return pathname;
        }
        else {
            // abbreviate pathanme: Windows OS like solution
            final int ABBREVIATED_PREFIX_LEN = 6; // e.g.: C:\..\
            final int MAX_PATHNAME_LEN = MAX_ITEM_LEN - ABBREVIATED_PREFIX_LEN;
            int firstFileSeparatorIndex = 0;
            for (int i = pathnameLen - 1; i >= (pathnameLen - MAX_PATHNAME_LEN); i--) {
                if (pathname.charAt(i) == FILE_SEPARATOR) {
                    firstFileSeparatorIndex = i;
                }
            }
            if (firstFileSeparatorIndex > 0) {
                return pathname.substring(0, 3) + ".." + pathname.substring(firstFileSeparatorIndex, pathnameLen);
            }
            else {
                return pathname.substring(0, 3) + ".." + File.separator + ".." + pathname.substring(pathnameLen - MAX_PATHNAME_LEN, pathnameLen);
            }
        }
    }

    /**
     * ***********************************************************
     * Create a JList instance with itemnameHistory as its model. *
     * ************************************************************
     */
    private final JList createItemList() {
        ListModel model = new ListModel();
        JList list = new JList(model);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return list;
    }

    // --- Helper classes ------------------------------------------------------

    /**
     * ********************************************************
     * Create a tooltip location directly over the menu item, *
     * ie, left allign the tooltip text in "overlay" technique. *
     * *********************************************************
     */
    private final class MenuItemWithFixedTooltip extends JMenuItem {

        private static final long serialVersionUID = 8197529826966991446L;

        public MenuItemWithFixedTooltip(String text) {
            super(text);
        }

        public Point getToolTipLocation(MouseEvent e) {
            Graphics g = getGraphics();
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            String prefix = itemnameHistory.size() <= 9 ? "8: " : "88: ";
            int prefixWidth = metrics.stringWidth(prefix);
            int x = JButton.TRAILING + JButton.LEADING - 1 + prefixWidth;
            return new Point(x, 0);
        }
    }

    /**
     * ********************************
     * Listen to menu item selections. *
     * *********************************
     */
    private final class ItemListener implements ActionListener {
        int itemNbr;

        ItemListener(int itemNbr) {
            this.itemNbr = itemNbr;
        }

        public void actionPerformed(ActionEvent e) {
            caller.loadFile((String) pathnameHistory.get(itemNbr));
            JMenuItem item = (JMenuItem) e.getSource();
            FileHistory.this.insertPathname(item.getToolTipText());
        }
    }

    /**
     * *****************************************************
     * The list model for our File History dialog itemList. *
     * ******************************************************
     */
    private final class ListModel extends AbstractListModel {

        private static final long serialVersionUID = 5136584248563629516L;

        public String getElementAt(int i) {
            return itemnameHistory.get(i);
        }

        public int getSize() {
            return itemnameHistory.size();
        }

    }

} // end class FileHistory
