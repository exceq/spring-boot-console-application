package com.example.springbootconsoleapplication;

import com.example.springbootconsoleapplication.modules.Module;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@Service
public class FilesService {
    public void executeFunction(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.example.springbootconsoleapplication.modules");
        Module[] modules = getAvailableModules(file, ctx);
        if (modules.length == 0) {
            System.out.println("No modules available for this file.");
            return;
        }
        printAvailableModules(file, modules);
        int number = askUserForNumberOfFunction(modules.length);
        modules[number - 1].function(file);
    }

    private int askUserForNumberOfFunction(int max) {
        while (true) {
            System.out.printf("Enter number of function (%s-%s):%n", 1, max);
            String input = System.console().readLine();
            int number;
            try {
                number = Integer.parseInt(input);
                if (number < 1 || number > max) {
                    System.out.println("Incorrect number");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number");
            }
        }
    }

    private void printAvailableModules(File file, Module[] modules) {
        System.out.println("Available functions for file " + file.getPath() + " :");
        for (int i = 0; i < modules.length; i++) {
            System.out.println((i + 1) + ". " + modules[i].getFunctionDescription());
        }
    }


    private Module[] getAvailableModules(File file, ApplicationContext ctx) {
        String ext = getExtension(file.getName());
        Map<String, Module> moduleMap = ctx.getBeansOfType(Module.class);
        return moduleMap.values()
                .stream()
                .filter(module -> module.isSupportedFormat(ext))
                .toArray(Module[]::new);
    }


    private String getExtension(String filename) {
        if (!filename.contains("."))
            return "";
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
