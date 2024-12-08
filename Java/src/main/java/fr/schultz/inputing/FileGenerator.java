package fr.schultz.inputing;

import fr.schultz.inputing.exception.CookieMissingException;
import lombok.NoArgsConstructor;

import java.beans.JavaBean;
import java.io.IOException;

@NoArgsConstructor
public class FileGenerator {
        private final FileWriter fileWriter = new FileWriter();
        private final HttpConnector httpConnector = new HttpConnector();

        public String getData(int year, int day, String sessionCookie) throws IOException, InterruptedException, CookieMissingException {
            String fileName = String.format("src/main/resources/%d/data-%d-%d.txt", year,year, day);
            if (fileWriter.fileExists(fileName)) {
                return fileWriter.read(fileName);
            }
            else {
                String content = httpConnector.get(year, day, sessionCookie);
                if (content.contains("Puzzle inputs differ by user.  Please log in to get your puzzle input.")) {
                    throw new CookieMissingException();
                }
                fileWriter.write(content, fileName);
                return content;
            }
        }
}
