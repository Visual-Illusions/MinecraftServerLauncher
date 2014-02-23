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
package net.visualillusionsent.minecraft.server.launcher.display;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;
import net.visualillusionsent.minecraft.server.launcher.popups.EditPopupMenu;
import net.visualillusionsent.minecraft.server.launcher.popups.WebLinkWarning;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;

/** @author Jason (darkdiplomat) */
public final class LogTextPanel extends JTextPane implements MouseListener {

    private static final long serialVersionUID = -2241728919153645949L;
    private final HTMLEditorKit kit;
    private final HTMLDocument doc;

    public LogTextPanel(LogPanel log_panel) {
        kit = new HTMLEditorKit();
        doc = new HTMLDocument();
        Dimension size = new Dimension(600, 440);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setEditable(false);
        this.setEditorKit(kit);
        this.setDocument(doc);
        this.addMouseListener(this);
        addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent hle) {
                if (hle.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                    try {
                        new WebLinkWarning(hle.getURL().toURI());
                    }
                    catch (URISyntaxException urisex) {
                    }
            }
        });
        this.clear();
    }

    public final void insertString(String log) {
        ensureSize();
        try {
            ControlRoom.logFine(log);
            kit.insertHTML(doc, doc.getLength(), log, 0, 0, null);
        }
        catch (BadLocationException e) {
        }
        catch (IOException e) {
        }
    }

    private final void ensureSize() {
        if (doc.getLength() > 150000) {
            try {
                this.doc.remove(0, doc.getLength() - 150000);
            }
            catch (BadLocationException e) {
            }
        }
    }

    public final void mousePressed(MouseEvent event) {
    }

    public final void mouseReleased(MouseEvent event) {
    }

    public final void mouseClicked(MouseEvent event) {
        new EditPopupMenu(event, false);
    }

    public final void mouseEntered(MouseEvent event) {
    }

    public final void mouseExited(MouseEvent event) {
    }

    public final void clear() {
        try {
            this.doc.remove(0, doc.getLength());
        }
        catch (BadLocationException e) {
        }
    }
}
