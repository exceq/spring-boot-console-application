package com.example.springbootconsoleapplication.modules.directory;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class SizeFolderFiles extends AbstractDirectoryModule {
    @Override
    public String getFunctionDescription() {
        return "Returns size of all files in the folder.";
    }

    @Override
    public void function(File file) {
        try {
            long sum = Files.walk(file.toPath())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
            System.out.printf("Size of folder %s: %s\n",file.getAbsolutePath(),getFormattedSize(sum));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getFormattedSize(long l) {
        return  l < 1024 ? l + " B" :
                l < (1024 * 1024) ? l / 1024 + " KB" :
                l < (1024 * 1024 * 1024) ? l / (1024 * 1024) + " MB" :
                l / (1024 * 1024 * 1024) + " GB";
    }
}
