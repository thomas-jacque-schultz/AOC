package fr.schultz.aoc.year2023;


import fr.schultz.generator.AocTemplate;
import fr.schultz.generator.Timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Resolve2023_5 extends AocTemplate {

    public Resolve2023_5() {
        super("""
                seeds: 14 0
                                
                seed-to-soil map:
                50 98 2
                52 50 48
                                
                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15
                                
                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4
                                
                water-to-light map:
                88 18 7
                18 25 70
                                
                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13
                                
                temperature-to-humidity map:
                0 69 1
                1 0 69
                                
                humidity-to-location map:
                60 56 37
                56 93 4""");
    }

    public String resolvePart1(String input, Timer timer) {
        timer.start();
        // get the input
        List<String> lines = getData(input);

        //parse the input


        List<List<String>> catagories = new ArrayList<>();

        lines = lines.stream().map(element -> {
            return element.replaceAll("[a-z:-]", "");
        }).toList();

        List<Double> seeds = new ArrayList<>(Stream.of(
                lines.getFirst()
                        .replaceFirst(" ", "")
                        .split(" ")).map(Double::parseDouble).toList()

        );

        for (String line : lines.subList(1, lines.size())) {
            if (line.isEmpty()) {
                catagories.add(new ArrayList<>());
            } else {
                if (!line.isBlank())
                    catagories.getLast().addAll(List.of(line.split(",")));
            }
        }

        // resolve the input

        seeds = seeds.parallelStream().map(seed -> {
            seed = getNewValue(seed, catagories.get(0));
            seed = getNewValue(seed, catagories.get(1));
            seed = getNewValue(seed, catagories.get(2));
            seed = getNewValue(seed, catagories.get(3));
            seed = getNewValue(seed, catagories.get(4));
            seed = getNewValue(seed, catagories.get(5));
            seed = getNewValue(seed, catagories.get(6));
            return seed;
        }).toList();
        //return min value of the list of seeds
        String res =String.format("%.0f", seeds.stream().min(Double::compareTo).get());
        timer.stop();
        return res;

    }

    private Double getNewValue(Double seed, List<String> rules) {
        Double newValue = seed;
        for (String rule : rules) {
            Double transform = Transform(newValue, rule);
            if (!transform.equals(newValue)) {
                newValue = transform;
                break;
            }
        }
        return newValue;
    }

    private Double Transform(Double seed, String rule) {
        Double newValue = seed;
        List<Double> values = Stream.of(rule.split(" ")).map(Double::parseDouble).toList();
        if (values.get(1) <= seed && seed <= values.get(1) + values.get(2)) { // if we are in the start range
            newValue = seed - values.get(1) + values.get(0);
        }
        return newValue;
    }


    public String resolvePart2(String input, Timer timer) {
        timer.start();
        // get the input
        List<String> lines = new ArrayList<>( getData(input));

        // parse the input

        long answer = Long.MAX_VALUE;
        List<RangeMap> rangeMaps = new ArrayList<>();
        List<Range> tmp = new ArrayList<>();
        String[] stringSeeds = lines.removeFirst().split(" ");
        long[] seeds = new long[stringSeeds.length - 1];
        for (int i = 1; i < stringSeeds.length; i++) {
            seeds[i - 1] = Long.parseLong(stringSeeds[i]);
        }
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.equals("")) {
                continue;
            }
            if (line.contains("map")) {
                if (tmp.size() > 0) {
                    rangeMaps.add(new RangeMap(tmp));
                }
                tmp = new ArrayList<>();
            } else {
                tmp.add(new Range(line));
            }
        }
        rangeMaps.add(new RangeMap(tmp));

        // resolve the input

        for (int i = 0; i < seeds.length; i += 2) {     // create a range for each seed

            for (long j = seeds[i]; j < seeds[i] + seeds[i + 1]; j++) { // for each value in the range

                long[] ret = returnValAndBound(j, rangeMaps);
                if (ret[0] < answer) {
                    answer = ret[0];
                }

                j += ret[1];
            }
        }
        timer.stop();
        return answer + "";
    }

    private long[] returnValAndBound(long val, List<RangeMap> rangeMaps) {
        long bound = Long.MAX_VALUE;
        for (RangeMap rangeMap : rangeMaps) {
            bound = Math.min(bound, rangeMap.convert(val)[1]);
            val = rangeMap.convert(val)[0];
        }
        return new long[]{val, bound};
    }

    class Range {
        long destination;
        long source;
        long range;

        public Range(long des, long src, long r) {
            destination = des;
            source = src;
            range = r;
        }

        public Range(String line) {
            String[] pieces = line.split(" ");
            destination = Long.parseLong(pieces[0]);
            source = Long.parseLong(pieces[1]);
            range = Long.parseLong(pieces[2]);
        }
    }

    class RangeMap {
        List<Long> starts;
        List<Long> ends;
        List<Long> betweens;

        public RangeMap(List<Range> ranges) {
            starts = new ArrayList<>();
            ends = new ArrayList<>();
            betweens = new ArrayList<>();
            for (Range range : ranges) {
                starts.add(range.source);
                ends.add(range.destination);
                betweens.add(range.range);
            }
        }
        public long[] convert(long val) {
            long nextStart = Long.MAX_VALUE;
            for (int i = 0; i < starts.size(); i++) {
                if (starts.get(i) > val) {
                    nextStart = Math.min(nextStart, starts.get(i) - val - 1);
                }
                if (starts.get(i) <= val && starts.get(i) + betweens.get(i) > val) {
                    return new long[]{ends.get(i) + (val - starts.get(i)), betweens.get(i) - (val - starts.get(i)) - 1};
                }
            }
            return new long[]{val, nextStart == Long.MAX_VALUE ? 0 : nextStart};
        }
    }

}