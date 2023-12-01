package fr.schultz.aoc.year2023;


import fr.schultz.generator.AocTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        int res = data.parallelStream().mapToInt(line -> {
                    String modifiedLine = line.replaceAll("one", "o1e")
                            .replaceAll("two", "t2o")
                            .replaceAll("three", "t3e")
                            .replaceAll("four", "f4r")
                            .replaceAll("five", "f5e")
                            .replaceAll("six", "s6x")
                            .replaceAll("seven", "s7n")
                            .replaceAll("eight", "e8t")
                            .replaceAll("nine", "n9e")
                            .replaceAll("[a-z]", "");
                    modifiedLine = modifiedLine.charAt(0) + "" + modifiedLine.charAt(modifiedLine.length() - 1);
                    return Integer.parseInt(modifiedLine);
                })
                .sum();
        return Integer.toString(res);

    }

}