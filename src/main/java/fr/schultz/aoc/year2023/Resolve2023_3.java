package fr.schultz.aoc.year2023;


import fr.schultz.generator.AocTemplate;
import fr.schultz.generator.Timer;

import java.util.*;

public class Resolve2023_3 extends AocTemplate {

    public class SumShift {
        public int sum;
        public int shift;

        public Map<String, List<Integer>> map = new HashMap<>();

        public SumShift(int sum, int shift) {
            this.sum = sum;
            this.shift = shift;
        }
    }

    public Resolve2023_3() {
        super("""
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..""");
    }

    public String resolvePart1(String input, Timer timer) {
        timer.start();
        List<String> res = getData(input);
        String[][] map = new String[res.size()+2][res.get(0).length()+2];
        Arrays.fill(map[0], ".");
        Arrays.fill(map[res.size()+1], ".");
        for (int i = 1; i < res.size()+1; i++) {
            map[i] = ("."+res.get(i-1)+".").split("");
        }
        SumShift sum = new SumShift(0,0);
        for(int i = 1; i < map.length-1; i++) {
            for(int j = 1; j < map[i].length-1;j++) {
                if(map[i][j].matches("[0-9]")) {
                    numberDetector(map, i, j,sum);
                    j = j + sum.shift;
                }
            }
        }
        timer.stop();
        return sum.sum+"";
    }

    private SumShift numberDetector(String[][] map, int i, int j, SumShift sumShift) {
        sumShift.shift = 0;
        StringBuilder number = new StringBuilder();
        boolean match = false;
        while (map[i][j].matches("[0-9]")) {
            number.append(map[i][j]);
            // check all around and diagonaly for a symbol
            if(checkAroundIfPossible(map, i, j,"[^0-9.]")) {
                match = true;
            }
            j++;
            sumShift.shift++;
        }
        if(match){
            sumShift.sum += Integer.parseInt(number.toString());
        }
        return sumShift;
    }

    public boolean checkAroundIfPossible(String[][] map, int i, int j,String match) {
        return map[i-1][j-1].matches(match) ||
                map[i-1][j].matches(match) ||
                map[i-1][j+1].matches(match) ||
                map[i][j-1].matches(match) ||
                map[i][j+1].matches(match) ||
                map[i+1][j-1].matches(match) ||
                map[i+1][j].matches(match) ||
                map[i+1][j+1].matches(match);
    }

    //check if the char * is around and return the coord
    public String checkAroundIfPossible2(String[][] map, int i, int j, String match) {
        boolean val1 =     map[i-1][j-1].matches(match);
        if(val1) {
            return (i-1)+","+(j-1);
        }
        boolean val2 =     map[i-1][j].matches(match);
        if(val2) {
            return (i-1)+","+j;
        }
        boolean val3 =     map[i-1][j+1].matches(match);
        if(val3) {
            return (i-1)+","+(j+1);
        }
        boolean val4 =     map[i][j-1].matches(match);
        if(val4) {
            return i+","+(j-1);
        }
        boolean val5 =     map[i][j+1].matches(match);
        if(val5) {
            return i+","+(j+1);
        }
        boolean val6 =     map[i+1][j-1].matches(match);
        if(val6) {
            return (i+1)+","+(j-1);
        }
        boolean val7 =     map[i+1][j].matches(match);
        if(val7) {
            return (i+1)+","+j;
        }
        boolean val8 =     map[i+1][j+1].matches(match) ;
        if(val8) {
            return (i+1)+","+(j+1);
        }
        return "" ;
    }


    public String resolvePart2(String input, Timer timer) {
        timer.start();
        List<String> res = getData(input);
        String[][] map = new String[res.size()+2][res.get(0).length()+2];
        Arrays.fill(map[0], ".");
        Arrays.fill(map[res.size()+1], ".");
        for (int i = 1; i < res.size()+1; i++) {
            map[i] = ("."+res.get(i-1)+".").split("");
        }
        SumShift sum = new SumShift(0,0);
        for(int i = 1; i < map.length-1; i++) {
            for(int j = 1; j < map[i].length-1;j++) {
                if(map[i][j].matches("[0-9]")) {
                    numberDetector2(map, i, j,sum);
                    j = j + sum.shift;
                }
            }
        }

        sum.map.forEach((k,v) -> {
            if(v.size() == 2) {
                sum.sum += v.get(0) * v.get(1);
            }
        });
        timer.stop();
        return sum.sum+"";
    }

    private SumShift numberDetector2(String[][] map, int i, int j, SumShift sumShift) {
        sumShift.shift = 0;
        StringBuilder number = new StringBuilder();
        boolean match = false;
        String coord = "";
        while (map[i][j].matches("[0-9]")) {
            number.append(map[i][j]);
            // check all around and diagonaly for a symbol
            String checkAroundIfPossible = checkAroundIfPossible2(map, i, j,"\\*");
            if(!checkAroundIfPossible.isEmpty()) {
                match = true;
                coord = checkAroundIfPossible;
            }
            j++;
            sumShift.shift++;
        }
        if(match){
            if(sumShift.map.containsKey(coord)) {
                sumShift.map.get(coord).add(Integer.parseInt(number.toString()));
            } else {
                sumShift.map.put(coord, new ArrayList<>(List.of(Integer.parseInt(number.toString()))));
            }
        }
        return sumShift;
    }

}