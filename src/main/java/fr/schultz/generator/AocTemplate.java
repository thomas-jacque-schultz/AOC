package fr.schultz.generator;

import fr.schultz.inputing.Parser;

import java.util.List;

public abstract class AocTemplate {

    private final String exampleData;

    public AocTemplate(String exampleData) {
        this.exampleData = exampleData;
    }

    public abstract String resolvePart1(String input);

    public abstract String resolvePart2(String input);

    /**
     * @return example data parsed
     */
    protected List<String> getData() {
        return List.of(exampleData.split("\n"));
    }

    /**
     * @param input : données en entrée telles que téléchargées du site
     * @return input data parsed
     */
    protected List<String> getData(String input) {
        return Parser.parse(input, System.lineSeparator());
    }

}