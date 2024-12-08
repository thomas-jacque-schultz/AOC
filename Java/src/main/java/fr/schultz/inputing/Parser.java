package fr.schultz.inputing;

import java.util.Arrays;
import java.util.List;

public class Parser {

    public  static List<String> parse(String content, String separator) {
        return Arrays.stream(content.split(separator)).toList();
    }

}