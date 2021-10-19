package com.example.springbootconsoleapplication.utils;

public class FileUtils {
    public static String getFileExtension(String filename) {
        if (filename.contains("."))
            return "";
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
