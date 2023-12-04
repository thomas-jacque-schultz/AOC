package fr.schultz.aoc.year2023;


import fr.schultz.generator.AocTemplate;

import java.util.*;

public class Resolve2023_2 extends AocTemplate {

    public Resolve2023_2() {
        super("""
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                """);
    }

    public String resolvePart1(String input) {
        List<String> res = getData(input);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("blue", 14);
        map.put("red", 12);
        map.put("green", 13);

        int sol = res.stream().mapToInt(elem -> {
            List<String> games = new ArrayList<>(Arrays.asList(elem.split(";|:")));
            Integer es = Integer.valueOf(games.remove(0).replaceAll("Game ", ""));

            List<Boolean> suces= games.stream().map(game -> {
                List<String> colors = new ArrayList<>(Arrays.asList(game.split(",")));
                for ( String color : colors ) {
                    for (Map.Entry<String, Integer> entry : map.entrySet()){
                        if (color.contains(entry.getKey())) {
                            Integer val = Integer.valueOf(color.replaceAll("[a-z]", "").trim());
                            if (val > entry.getValue()) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }).toList();
            if(suces.contains(false)) {
                return 0;
            }
            return es;
        }).sum();
        return sol + "";
    }


    public String resolvePart2(String input) {
        List<String> res = getData(input);


        int sol = res.stream().mapToInt(elem -> {
            List<String> games = new ArrayList<>(Arrays.asList(elem.split(";|:")));
            HashMap<String, Integer> map = new HashMap<>();
            map.put("blue", 0);
            map.put("red", 0);
            map.put("green", 0);


            List<Boolean> suces= games.stream().map(game -> {
                //here

                List<String> colors = new ArrayList<>(Arrays.asList(game.split(",")));
                for ( String color : colors ) {
                    for (Map.Entry<String, Integer> entry : map.entrySet()){
                        if (color.contains(entry.getKey())) {
                            Integer val = Integer.valueOf(color.replaceAll("[a-z]", "").trim());
                            if (val > entry.getValue()) {
                                map.put(entry.getKey(), val);
                            }
                        }
                    }
                }
                return true;
            }).toList();


            //todo
            return map.get("blue") * map.get("red") * map.get("green");
        }).sum();
        return sol + "";
    }

}