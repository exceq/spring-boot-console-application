package com.example.springbootconsoleapplication.modules.textfile;

import com.example.springbootconsoleapplication.modules.Module;
import com.example.springbootconsoleapplication.utils.FileUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class CountRowsModule implements Module {

    List<String> formats = Arrays.asList("txt", "doc", "docx", "rtf", "html", "pdf",
                                            "odt", "fb2", "epub", "mobi", "djvu");

    @Override
    public boolean isSupportedFormat(File file) {
        return formats.contains(FileUtils.getFileExtension(file.getName()).toLowerCase(Locale.ROOT));
    }

    @Override
    public String getFunctionDescription() {
        return "Returns count of rows in file.";
    }

    @Override
    public void function(File file) {
        try {
            int count = Files.readAllLines(file.toPath()).size();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
