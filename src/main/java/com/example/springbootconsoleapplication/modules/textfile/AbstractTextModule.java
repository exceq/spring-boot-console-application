package com.example.springbootconsoleapplication.modules.textfile;

import com.example.springbootconsoleapplication.modules.Module;
import com.example.springbootconsoleapplication.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public abstract class AbstractTextModule implements Module {

    static final List<String> formats = Arrays.asList("txt", "doc", "docx", "rtf", "html", "pdf",
            "odt", "fb2", "epub", "mobi", "djvu");

    @Override
    public boolean isSupportedFormat(File file) {
        return formats.contains(FileUtils.getFileExtension(file.getName()).toLowerCase(Locale.ROOT));
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);

}
