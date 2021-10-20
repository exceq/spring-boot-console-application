package com.example.springbootconsoleapplication.modules.textfile;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

@Component
public class FrequencyOfSymbols extends AbstractTextModule {

    @Override
    public String getFunctionDescription() {
        return "Returns the frequency of occurrence of each symbol.";
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

        HashMap<Character, Integer> symbols = lines.stream()
                .flatMapToInt(String::chars)
                .mapToObj(x -> (char) x)
                .collect(HashMap::new,
                        (m, c) -> m.put(c, m.getOrDefault(c, 0) + 1),
                        HashMap::putAll);
        if (symbols.keySet().size() == 0)
            System.out.println("This file is empty.");
        else
            symbols.entrySet()
                    .stream()
                    .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
                    .map(entry -> entry.getKey() + " -> " + entry.getValue())
                    .forEach(System.out::println);
    }
}
