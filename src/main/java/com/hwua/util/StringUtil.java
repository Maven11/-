package com.hwua.util;

public class StringUtil {
    public static String replaceStr(String str) {
        str = str.replaceAll("\r\n", "<br>");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll(" ", "&nbsp;");
        str = str.replaceAll("\"", "&quot");
        return str;
    }
}
