package com.example.springbootconsoleapplication.modules.audio;

import com.example.springbootconsoleapplication.utils.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.audio.AudioParser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;

@Component
public class NameFromTagsModule extends AbstractAudioModule {
    @Override
    public String getFunctionDescription() {
        return "Returns name of song from tags.";
    }

    @Override
    public void function(File file) {
        try (InputStream input = new FileInputStream(file)) {
            Parser parser;
            if (FileUtils.getFileExtension(file.getName()).equals("mp3"))
                parser = new Mp3Parser();
            else
                parser = new AudioParser();

            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            ParseContext parseCtx = new ParseContext();
            parser.getSupportedTypes(parseCtx);

            parser.parse(input, handler, metadata, parseCtx);

            printSongTitle(metadata);

        } catch (IOException | SAXException | TikaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printSongTitle(Metadata metadata) {
        String title = metadata.get("dc:title");
        if (title != null)
            System.out.println("Title: " + title);
        else
            System.out.println("Title not found in metadata.");
    }



}
