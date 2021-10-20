package com.example.springbootconsoleapplication.modules.textfile;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class CountRowsModule extends AbstractTextModule {

    @Override
    public String getFunctionDescription() {
        return "Returns count of rows in file.";
    }

    @Override
    public void function(File file) {
        try {
            int count = Files.readAllLines(file.toPath()).size();
            System.out.println("Count of lines in file: "+count);
        } catch (IOException e) {
            System.out.println("Error with reading file " + file.getAbsolutePath());
        }
    }
}
