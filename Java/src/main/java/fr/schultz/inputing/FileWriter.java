package fr.schultz.inputing;

import lombok.NoArgsConstructor;

import java.beans.JavaBean;
import java.io.File;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@NoArgsConstructor
public class FileWriter {

        public boolean fileExists(String fileName) {
            File f = new File(fileName);
            return f.exists();
        }

        public void write(String content, String fileName) throws IOException {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));
            writer.write(content);
            writer.close();
        }

        public String read(String fileName) throws IOException {
            return Files.readString(Path.of(fileName));
        }


}
