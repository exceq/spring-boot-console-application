package com.example.springbootconsoleapplication.modules.directory;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ZipFolder extends AbstractDirectoryModule {

    private String localRoot;

    @Override
    public String getFunctionDescription() {
        return "Makes zip-archive from folder.";
    }

    @Override
    public void function(File file) {
        localRoot = file.getAbsolutePath();

        String zipName = file.getName() + ".zip";
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName))) {
            addDirectory(zout, file);
            System.out.printf("Zip-archive %s is created.\n", zipName);
        } catch (FileNotFoundException e) {
            System.out.printf("Error: File %s not found.\n", file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("IOException: "+e.getMessage());
        }
    }

    private void addDirectory(ZipOutputStream zout, File fileSource) {
        File[] files = fileSource.listFiles();
        if (files == null)
            return;

        for (File file : files) {
            if (file.isDirectory()) {
                addDirectory(zout, file);
                continue;
            }
            try (FileInputStream fis = new FileInputStream(file)) {
                String entryName = file.getAbsolutePath().replace(localRoot + File.separator, "");
                zout.putNextEntry(new ZipEntry(entryName));
                System.out.printf("Copying %s...\n",entryName);
                writeToZipOutputStream(zout, fis);
                zout.closeEntry();
            } catch (IOException e) {
                System.out.println("IOException: "+e.getMessage());
            }
        }
    }

    private void writeToZipOutputStream(ZipOutputStream zout, FileInputStream fis) throws IOException {
        byte[] buffer = new byte[4096];
        int length;
        while ((length = fis.read(buffer)) > 0)
            zout.write(buffer, 0, length);
    }
}
