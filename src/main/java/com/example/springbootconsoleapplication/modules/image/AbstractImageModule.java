package com.example.springbootconsoleapplication.modules.image;

import com.example.springbootconsoleapplication.modules.Module;
import com.example.springbootconsoleapplication.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public abstract class AbstractImageModule implements Module {
    static final List<String> formats = Arrays.asList("jpg","jpeg","png","bmp", "webp");

    @Override
    public boolean isSupportedFormat(File file) {
        return formats.contains(FileUtils.getFileExtension(file.getName()).toLowerCase(Locale.ROOT));
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);
}
