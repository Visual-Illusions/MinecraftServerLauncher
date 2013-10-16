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
 * NOT affilated with, endorsed, or sponsored by with Mojang AB or Notch Development AB.
 */
package net.visualillusionsent.minecraft.server.launcher.menu.help;

import net.visualillusionsent.minecraft.server.launcher.ControlRoom;

import javax.imageio.ImageIO;
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
public final class About extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1981671238465892255L;
    private final JWindow window;

    About() {
        window = new JWindow();
        this.setPreferredSize(new Dimension(450, 350));
        this.setVisible(true);
        this.setLayout(null);

        JTextPane text = new JTextPane();
        text.setBounds(5, 150, 425, 300);
        text.setVisible(true);
        text.setEditable(false);
        text.setOpaque(false);
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument doc = new HTMLDocument();
        text.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                            return;
                        }
                        catch (IOException e1) {
                        }
                        catch (URISyntaxException e1) {
                        }
                        try {
                            Runtime.getRuntime().exec("xdg-open ".concat(e.getURL().toString()));
                        }
                        catch (IOException ioex) {
                            System.out.println("Failed to show file: '" + e.getURL().toString() + "'. Possible unsupported File Manager...");
                        }
                    }
                }
            }
        });
        text.setEditorKit(kit);
        text.setDocument(doc);
        try {
            kit.insertHTML(doc, doc.getLength(), "<h1 style=\"font-size:12px;text-align:center\">v. " + ControlRoom.instance().getVersion() + " " + ControlRoom.instance().getCleanStatus() + "</h1><br>", 0, 0, null);
            kit.insertHTML(doc, doc.getLength(), "<p style=\"font-size:10px;text-align:center\">Copyright &copy; 2013 <a href=\"http://visualillusionsent.net\"><u>Visual Illusions Entertainment</u></a></p>", 0, 0, null);
            kit.insertHTML(doc, doc.getLength(), "<p style=\"font-size:10px;text-align:center\">All Rights Reserved</p>", 0, 0, null);
            kit.insertHTML(doc, doc.getLength(), "<p style=\"font-size:10px;text-align:center\"><a href=\"http://minecraft.net\"><u>Minecraft</u></a> is the property of Mojang AB/Notch Development AB</p>", 0, 0, null);
            kit.insertHTML(doc, doc.getLength(), "<p style=\"font-size:10px;text-align:center\">Copyright &copy; 2009-2013 <a href=\"http://mojang.com\"><u>Mojang AB</u></a></p>", 0, 0, null);
            kit.insertHTML(doc, doc.getLength(), "<p style=\"font-size:10px;text-align:center\">\"Minecraft\" is a trademark of Notch Development AB</p>", 0, 0, null);
            kit.insertHTML(doc, doc.getLength(), "<p style=\"font-size:10px;text-align:center\">Visual Illusions Minecraft Server Launcher and Visual Illusions Entertainment are NOT affilated with, endorsed by, or sponsored by Mojang AB or Notch Development AB. </p>", 0, 0, null);
        }
        catch (BadLocationException e) {
        }
        catch (IOException e) {
        }
        window.getContentPane().add(text);
        window.getContentPane().add(this);
        window.addMouseListener(this);
        window.pack();
        ControlRoom.setWindowRelativeToCentral(window);
        window.setVisible(true);
        window.setAlwaysOnTop(true);

    }

    @Override
    protected final void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);
        try {
            gfx.drawImage(ImageIO.read(this.getClass().getResource("/resources/img/vimcsl_logo.png")), 50, 5, null);
        }
        catch (IOException e) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        window.dispose();
    }

    @Override
    public final void mousePressed(MouseEvent e) {
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
    }

    @Override
    public final void mouseExited(MouseEvent e) {
    }
}
