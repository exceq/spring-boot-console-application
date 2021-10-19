package com.example.springbootconsoleapplication.modules;

import java.io.File;

public interface Module {
    boolean isSupportedFormat(File file);

    String getFunctionDescription();

    void function(File file);
}
