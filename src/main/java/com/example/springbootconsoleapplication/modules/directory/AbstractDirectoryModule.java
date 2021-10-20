package com.example.springbootconsoleapplication.modules.directory;

import com.example.springbootconsoleapplication.modules.Module;

import java.io.File;

public abstract class AbstractDirectoryModule implements Module {
    @Override
    public boolean isSupportedFormat(File file) {
        return file.isDirectory();
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);
}
