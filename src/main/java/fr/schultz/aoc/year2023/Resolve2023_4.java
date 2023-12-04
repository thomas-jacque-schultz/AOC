package fr.schultz.aoc.year2023;


import fr.schultz.generator.AocTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Resolve2023_4 extends AocTemplate {

    public Resolve2023_4() {
        super("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""");
    }

    public String resolvePart1(String input) {
        List<String> res = getData(input);
        int sol = res.stream().mapToInt(elem -> {
            String trim = elem.split(":")[1].replaceAll("  ", " ").replaceAll(" ", ",").trim();
            String[] split = trim.split("\\|");
            String[] winning = split[0].substring(1).split(",");
            String[] play = split[1].substring(1).split(",");
            int match  = Arrays.stream(play).mapToInt(card -> {
                if(Arrays.stream(winning).anyMatch(w -> w.equals(card))){
                    return 1;
                }
                return 0;
            }).sum();
            if(match == 0) {
                return 0;
            }
            return (int) Math.pow(2, match - 1);
        }).sum();
        return sol+"";
    }


    public String resolvePart2(String input) {
        List<String> res = getData(input);
        List<Integer> sol = res.stream().map(elem -> {
            String trim = elem.split(":")[1].replaceAll("  ", " ").replaceAll(" ", ",").trim();
            String[] split = trim.split("\\|");
            String[] winning = split[0].substring(1).split(",");
            String[] play = split[1].substring(1).split(",");
            int match = Arrays.stream(play).mapToInt(card -> {
                if (Arrays.stream(winning).anyMatch(w -> w.equals(card))) {
                    return 1;
                }
                return 0;
            }).sum();

            return match;
        }).toList();
        List<Integer> count = new ArrayList<>(sol.size());
        int sum = 0;
        for(int i = 0; i < sol.size(); i++) {
            sum += addUp(i, sol);

        }
        return sum+"";

    }

    public int addUp(int index, List<Integer> sol) {
        int res = 1;
        for (int i = index+1; i <= index +sol.get(index); i++) {
            res += addUp(i, sol);
        }
        return res;
    }

}