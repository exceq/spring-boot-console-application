package com.example.springbootconsoleapplication.modules.audio;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class AudioMetadataModule extends AbstractAudioModule {
    @Override
    public String getFunctionDescription() {
        return "Returns metadata of the song.";
    }

    @Override
    public void function(File file) {
        try (InputStream input = new FileInputStream(file)) {
            Parser parser = new Mp3Parser();

            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            ParseContext parseCtx = new ParseContext();
            parser.getSupportedTypes(parseCtx);

            parser.parse(input, handler, metadata, parseCtx);

            printFullInformation(metadata);

        } catch (IOException | SAXException | TikaException e) {
            System.out.println(e.getMessage());
        }
    }
    private void printFullInformation(Metadata metadata) {
        String[] metadataNames = metadata.names();
        System.out.println("Metadata of the song:");
        System.out.println("----------------------------");
        for (String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
}
