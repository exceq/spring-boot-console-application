package com.example.springbootconsoleapplication.modules.textfile;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class SumOfCharacters extends AbstractTextModule {
    @Override
    public String getFunctionDescription() {
        return "Returns sum of Characters in file.";
    }

    @Override
    public void function(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("Error with reading file " + file.getAbsolutePath());
            return;
        }

        int sum = lines.stream()
                .flatMapToInt(String::chars)
                .sum();
        System.out.println("Sum of the file Characters: " + sum);
    }
}
