package fr.schultz.aoc.year2023;


import fr.schultz.generator.AocTemplate;
import fr.schultz.generator.Timer;

import java.time.Instant;
import java.util.List;

public class Resolve2023_6 extends AocTemplate {

    public Resolve2023_6() {
        super("""
                Time:      7  15   30
                Distance:  9  40  200""");
    }

    public String resolvePart1(String input, Timer timer) {
        timer.start();
        // get the input
        List<String> linesList = getData(input);


        // parse the input
        List<String> times = List.of(linesList
                .get(0)
                .replaceAll("[a-zA-Z:]", "")
                .replaceAll("\\s+", " ")
                .trim()
                .split(" "));
        List<String> distances = List.of(linesList
                .get(1)
                .replaceAll("[a-zA-Z:]", "")
                .replaceAll("\\s+", " ")
                .trim()
                .split(" "));


        // calculate the answer
        long res = 1L;
        for (int index = 0; index < times.size(); index++) {
            long matches = getMatches(Long.parseLong(times.get(index)), Long.parseLong( distances.get(index)));
            res =  res * matches;

        }
        timer.stop();
        return res + "";
    }


    public String resolvePart2(String input, Timer timer) {
        timer.start();
        // get the input
        List<String> linesList = getData(input);

        // parse the input
        List<Long> times = List.of(linesList
                .get(0)
                .replaceAll("[a-zA-Z: ]", "")
                .trim()
                .replaceAll("\\s+", " ")
                .split(" ")).stream().map(Long::parseLong).toList();
        List<Long> distances = List.of(linesList
                .get(1)
                .replaceAll("[a-zA-Z: ]", "")
                .trim()
                .replaceAll("\\s+", " ")
                .split(" ")).stream().map(Long::parseLong).toList();

        // calculate the answer
        long res = 1L;
        for (int index = 0; index < times.size(); index++) {
            long matches = getMatches(times.get(index), distances.get(index));
            res =  res * matches;
        }
        timer.stop();
        return res + "";
    }
    private static long getMatches(Long time,Long distance) {
        double discriminant = time * time - 4 * distance; // 100
        double root1 = (-time + Math.sqrt(discriminant)) / (-2);
        double root2 = (-time - Math.sqrt(discriminant)) / (-2);

        Double root1Round = Math.ceil(root1);
        double root2Round = Math.ceil(root2);

        root1Round = root1Round.equals(root1) ? root1Round+1 : root1Round;

        long matches = (long) (Math.max(root1Round, root2Round) - Math.min(root1Round, root2Round));
        return matches;
    }
}