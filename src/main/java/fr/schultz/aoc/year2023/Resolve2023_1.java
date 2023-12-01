package fr.schultz.aoc.year2023;


import fr.schultz.generator.AocTemplate;

import java.util.List;
import java.util.stream.Stream;

public class Resolve2023_1 extends AocTemplate {

    public Resolve2023_1() {
        super("""
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen""");
    }

    public String resolvePart1(String input) {
        List<String> data = this.getData(input);
        Integer res = data.stream().map(line -> {
            String num = line.replaceAll("[a-z]", "");
            num = num.charAt(0) + "" + num.charAt(num.length() - 1);
            return Integer.parseInt(num);
        }).reduce(0, Integer::sum);
        return res.toString();
    }


    public String resolvePart2(String input) {

        List<String> data = this.getData(input);
        Integer res = data.stream().map(line -> {
            line = line.replaceAll("one", "o1e");
            line = line.replaceAll("two", "t2o");
            line = line.replaceAll("three", "t3e");
            line = line.replaceAll("four", "f4r");
            line = line.replaceAll("five", "f5i");
            line = line.replaceAll("six", "s6x");
            line = line.replaceAll("seven", "s7n");
            line = line.replaceAll("eight", "e8t");
            line = line.replaceAll("nine", "n9e");
            line = line.replaceAll("zero", "z0o");
            String num = line.replaceAll("[a-z]", "");
            num = num.charAt(0) + "" + num.charAt(num.length() - 1);
            return Integer.parseInt(num);
        }).reduce(0, Integer::sum);
        return res.toString();

    }

}