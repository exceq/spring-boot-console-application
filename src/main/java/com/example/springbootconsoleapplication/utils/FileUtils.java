package com.example.springbootconsoleapplication.utils;

import java.util.Locale;

public class FileUtils {
    public static String getFileExtension(String filename) {
        if (!filename.contains("."))
            return "";
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase(Locale.ROOT);
    }
}
