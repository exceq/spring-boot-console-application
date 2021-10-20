package com.example.springbootconsoleapplication.modules.directory;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

@Component
public class ListFilesModule extends AbstractDirectoryModule {
    @Override
    public String getFunctionDescription() {
        return "Returns list of files in directory.";
    }

    @Override
    public void function(File file) {
        Arrays.stream(file.listFiles())
                .sorted(getFilesComparator())
                .map(f -> (f.isDirectory() ? "[DIR]" : "[FILE]") + "\t" + f.getName())
                .forEach(System.out::println);
    }

    private Comparator<File> getFilesComparator() {
        return (a, b) -> a.isDirectory() && !b.isDirectory() ? -1 :
                !a.isDirectory() && b.isDirectory() ? 1 : 0;
    }
}
