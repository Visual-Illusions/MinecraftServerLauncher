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
package net.visualillusionsent.minecraft.server.launcher.display;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** @author Jason (darkdiplomat) */
public final class LogPanelFormatter {
    private final String BLACK = "<span style=\"color:black;background-color:white;\">%s</span>";
    private final String DARK_BLUE = "<span style=\"color:#0000AA;\">%s</span>";
    private final String GREEN = "<span style=\"color:#00AA00;\">%s</span>";
    private final String TURQUOISE = "<span style=\"color:#00AAAA;\">%s</span>";
    private final String RED = "<span style=\"color:#AA0000;\">%s</span>";
    private final String PURPLE = "<span style=\"color:#AA00AA;\">%s</span>";
    private final String ORANGE = "<span style=\"color:#FFAA00;\">%s</span>";
    private final String LIGHT_GRAY = "<span style=\"color:#AAAAAA;\">%s</span>";
    private final String GRAY = "<span style=\"color:#555555;\">%s</span>";
    private final String BLUE = "<span style=\"color:#5555FF;\">%s</span>";
    private final String LIGHT_GREEN = "<span style=\"color:#55FF55;\">%s</span>";
    private final String CYAN = "<span style=\"color:#55FFFF;\">%s</span>";
    private final String LIGHT_RED = "<span style=\"color:#FF5555;\">%s</span>";
    private final String PINK = "<span style=\"color:#FF55FF;\">%s</span>";
    private final String YELLOW = "<span style=\"color:#FFFF55;\">%s</span>";
    private final String WHITE = "<span style=\"color:white;\">%s</span>";
    private final String BOLD = "<b>%s</b>";
    private final String ITALIC = "<i>%s</i>";
    private final String UNDERLINE = "<u>%s</u>";
    private final String STRIKED = "<strike>%s</strike>";
    private final String SCRAMBLE = "SCRAMBLE";
    private final String cReset = "%s";

    private String colorCurrent = WHITE;
    private String pendingForm = "";

    public LogPanelFormatter() {
    }

    private final String append(String c, String s) {
        if (c.isEmpty()) {
            return String.format(WHITE, s);
        }
        if (!s.isEmpty()) {
            String ret = "";
            if (c.equals(SCRAMBLE)) {
                s = scramble(s);
                if (!pendingForm.isEmpty()) {
                    ret = String.format(pendingForm, String.format(c, s));
                }
                else {
                    ret = String.format(WHITE, s);
                }
            }
            else if (pendingForm.equals(SCRAMBLE)) {
                s = scramble(s);
                ret = String.format(c, s);
            }
            else if (!pendingForm.isEmpty()) {
                ret = String.format(pendingForm, String.format(c, s));
            }
            else {
                ret = String.format(c, s);
            }
            pendingForm = "";
            return ret;
        }
        else {
            pendingForm = c;
            return "";
        }
    }

    private final String scramble(String s) {
        String[] scram = s.split("");
        List<String> letters = Arrays.asList(scram);
        Collections.shuffle(letters);
        StringBuilder sb = new StringBuilder(s.length());
        for (String c : letters) {
            sb.append(c);
        }
        return sb.toString();
    }

    public final String convertToHTML(String line) {
        String convert = convertANSI(convertSectionSymbol(line));
        convert = convert.replaceAll("((https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])", String.format("<a href=%s>%s</a>", "$1", "$1"));
        return convert;
    }

    private final String convertANSI(String s) {
        int aPos = 0;
        int aIndex = 0;
        int mIndex = 0;
        String tmpString = "";
        boolean stillSearching = true;
        String addString = s;
        String remaining = "";
        String toRet = "";

        if (addString.length() > 0) {
            aIndex = addString.indexOf("\u001B");
            if (aIndex == -1) {
                return append(WHITE, addString);
            }

            if (aIndex > 0) {
                tmpString = addString.substring(0, aIndex);
                toRet = toRet.concat(append(colorCurrent, tmpString));
                aPos = aIndex;
            }
            stillSearching = true;
            while (stillSearching) {
                mIndex = addString.indexOf("m", aPos);
                if (mIndex < 0) {
                    remaining = addString.substring(aPos, addString.length());
                    stillSearching = false;
                    continue;
                }
                else {
                    tmpString = addString.substring(aPos, mIndex + 1);
                    colorCurrent = convertANSICode(tmpString);
                }
                aPos = mIndex + 1;
                aIndex = addString.indexOf("\u001B", aPos);

                if (aIndex == -1) {
                    tmpString = addString.substring(aPos, addString.length());
                    toRet = toRet.concat(append(colorCurrent, tmpString));
                    stillSearching = false;
                    continue;
                }

                tmpString = addString.substring(aPos, aIndex);
                aPos = aIndex;
                toRet = toRet.concat(append(colorCurrent, tmpString));

            }
        }
        colorCurrent = WHITE;
        return toRet.isEmpty() ? String.format(WHITE, s) : toRet.concat(remaining);
    }

    private final String convertSectionSymbol(String toOut) {
        if (toOut.indexOf('\u00A7') > -1) {
            String[] tmp = toOut.split("\u00A7");
            String toRet = "";
            boolean first = true;
            for (String tmpret : tmp) {
                if (!first) {
                    colorCurrent = convertSectionCode("\u00A7".concat(tmpret.substring(0, 1)));
                    toRet = toRet.concat(append(colorCurrent, (tmpret.length() > 1 ? tmpret.substring(1) : "")));
                }
                else {
                    toRet = toRet.concat(append(colorCurrent, tmpret));
                    first = false;
                }
            }
            colorCurrent = WHITE;
            return toRet;
        }
        return String.format(WHITE, toOut);
    }

    private final String convertANSICode(String ANSIColor) {
        if (ANSIColor.matches("\u001B(\\[30m|\\[0;30m|\\[30;22m)")) {
            return BLACK;
        }
        else if (ANSIColor.equals("\u001B[30;1m")) {
            return GRAY;
        }
        else if (ANSIColor.matches("\u001B(\\[31m|\\[0;31m|\\[31;22m)")) {
            return RED;
        }
        else if (ANSIColor.equals("\u001B[31;1m")) {
            return LIGHT_RED;
        }
        else if (ANSIColor.matches("\u001B(\\[32m|\\[0;32m|\\[32;22m)")) {
            return GREEN;
        }
        else if (ANSIColor.equals("\u001B[32;1m")) {
            return LIGHT_GREEN;
        }
        else if (ANSIColor.matches("\u001B(\\[33m|\\[0;33m|\\[33;22m)")) {
            return ORANGE;
        }
        else if (ANSIColor.equals("\u001B[33;1m")) {
            return YELLOW;
        }
        else if (ANSIColor.matches("\u001B(\\[34m|\\[0;34m|\\[34;22m)")) {
            return DARK_BLUE;
        }
        else if (ANSIColor.equals("\u001B[34;1m")) {
            return BLUE;
        }
        else if (ANSIColor.matches("\u001B(\\[35m|\\[0;35m|\\[35;22m)")) {
            return PURPLE;
        }
        else if (ANSIColor.equals("\u001B[35;1m")) {
            return PINK;
        }
        else if (ANSIColor.matches("\u001B(\\[36m|\\[0;36m|\\[36;22m)")) {
            return TURQUOISE;
        }
        else if (ANSIColor.equals("\u001B[36;1m")) {
            return CYAN;
        }
        else if (ANSIColor.matches("\u001B(\\[37m|\\[0;37m|\\[37;22m)")) {
            return LIGHT_GRAY;
        }
        else if (ANSIColor.equals("\u001B[37;1m")) {
            return WHITE;
        }
        else if (ANSIColor.equals("\u001B[21m")) {
            return BOLD;
        }
        else if (ANSIColor.equals("\u001B[3m")) {
            return ITALIC;
        }
        else if (ANSIColor.equals("\u001B[4m")) {
            return UNDERLINE;
        }
        else if (ANSIColor.equals("\u001B[9m")) {
            return STRIKED;
        }
        else if (ANSIColor.equals("\u001B[5m")) {
            return SCRAMBLE;
        }
        else if (ANSIColor.equals("\u001B[0m")) {
            return cReset;
        }
        else if (ANSIColor.equals("\u001B[m")) {
            return cReset;
        }
        return WHITE;
    }

    private final String convertSectionCode(String section) {
        if (section.equals("\u00A70")) {
            return BLACK;
        }
        else if (section.equals("\u00A71")) {
            return DARK_BLUE;
        }
        else if (section.equals("\u00A72")) {
            return GREEN;
        }
        else if (section.equals("\u00A73")) {
            return TURQUOISE;
        }
        else if (section.equals("\u00A74")) {
            return RED;
        }
        else if (section.equals("\u00A75")) {
            return PURPLE;
        }
        else if (section.matches("\u00A76")) {
            return ORANGE;
        }
        else if (section.equals("\u00A77")) {
            return LIGHT_GRAY;
        }
        else if (section.equals("\u00A78")) {
            return GRAY;
        }
        else if (section.equals("\u00A79")) {
            return BLUE;
        }
        else if (section.matches("\u00A7[Aa]")) {
            return LIGHT_GREEN;
        }
        else if (section.matches("\u00A7[Bb]")) {
            return CYAN;
        }
        else if (section.matches("\u00A7[Cc]")) {
            return LIGHT_RED;
        }
        else if (section.matches("\u00A7[Dd]")) {
            return PINK;
        }
        else if (section.matches("\u00A7[Ee]")) {
            return YELLOW;
        }
        else if (section.matches("\u00A7[Kk]")) {
            return SCRAMBLE;
        }
        else if (section.matches("\u00A7[Ll]")) {
            return BOLD;
        }
        else if (section.matches("\u00A7[Mm]")) {
            return STRIKED;
        }
        else if (section.matches("\u00A7[Nn]")) {
            return UNDERLINE;
        }
        else if (section.matches("\u00A7[Oo]")) {
            return ITALIC;
        }
        return WHITE; // FfRr
    }
}