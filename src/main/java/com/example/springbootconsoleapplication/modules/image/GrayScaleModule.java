package com.example.springbootconsoleapplication.modules.image;

import com.example.springbootconsoleapplication.utils.FileUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Component
public class GrayScaleModule extends AbstractImageModule {

    @Override
    public String getFunctionDescription() {
        return "Makes the image in grayscale.";
    }

    @Override
    public void function(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            File newFile = new File("gray_" + file.getName());
            Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(newFile.getAbsolutePath()),
                    StandardCopyOption.REPLACE_EXISTING);
            paintImageToGrayscale(image);
            ImageIO.write(image, FileUtils.getFileExtension(newFile.getName()), newFile);

            System.out.println("New gray image: " + newFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could not open the file " + file.getAbsolutePath());
        }
    }

    private void paintImageToGrayscale(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color c = new Color(image.getRGB(x, y));
                int av = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                image.setRGB(x, y, new Color(av, av, av).getRGB());
            }
        }
    }

}
