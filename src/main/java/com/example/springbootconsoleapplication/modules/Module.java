package com.example.springbootconsoleapplication.modules;

import org.springframework.stereotype.Component;

import java.io.File;

public interface Module {
    boolean isSupportedFormat(String extension);

    String getFunctionDescription();

    void function(File file);
}
