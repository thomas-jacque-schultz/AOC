package fr.schultz.aoc.year2023;


import fr.schultz.generator.AocTemplate;
import fr.schultz.generator.Timer;

import java.util.*;

public class Resolve2023_7 extends AocTemplate {

    private final static Map<Character, Integer> valuesPoint1 = Map.ofEntries(
            Map.entry('2', 2),
            Map.entry('3', 3),
            Map.entry('4', 4),
            Map.entry('5', 5),
            Map.entry('6', 6),
            Map.entry('7', 7),
            Map.entry('8', 8),
            Map.entry('9', 9),
            Map.entry('T', 10),
            Map.entry('J', 11),
            Map.entry('Q', 12),
            Map.entry('K', 13),
            Map.entry('A', 14)
    );
    private final static Map<Character, Integer> valuesPoint2 = Map.ofEntries(
            Map.entry('2', 2),
            Map.entry('3', 3),
            Map.entry('4', 4),
            Map.entry('5', 5),
            Map.entry('6', 6),
            Map.entry('7', 7),
            Map.entry('8', 8),
            Map.entry('9', 9),
            Map.entry('T', 10),
            Map.entry('J', 0),
            Map.entry('Q', 12),
            Map.entry('K', 13),
            Map.entry('A', 14)
    );

    public Resolve2023_7() {
        super("""
                32T3K 765
                T55J5 684
                KK677 28
                KTJJT 220
                QQQJA 483""");
    }

    public String resolvePart1(String input, Timer timer) {
        timer.start();
        List<Hand> data = new ArrayList<>(this.getData(input)).parallelStream().map(line -> new Hand(line, false)).sorted().toList();
        long sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += (long) data.get(i).points * (i + 1);
        }
        timer.stop();
        return sum + "";
    }

    public String resolvePart2(String input, Timer timer) {
        timer.start();
        List<Hand> data = new ArrayList<>(this.getData(input)).parallelStream().map(line -> new Hand(line, true)).sorted().toList();
        long sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i).points * (i + 1);
        }
        timer.stop();
        return sum + "";
    }

    private class Hand implements Comparable {
        private String cards;
        private int points;

        boolean type;

        private List<Integer> occurences = null;

        public Hand(String cards, boolean type) {
            this.cards = cards.split(" ")[0];
            this.points = Integer.parseInt(cards.split(" ")[1]);
            Map<Character, Integer> occ = new HashMap<>();
            for (Character card : this.cards.toCharArray()) {
                if (occ.containsKey(card)) {
                    occ.put(card, occ.get(card) + 1);
                } else {
                    occ.put(card, 1);
                }
            }
            if (type) {
                if (occ.containsKey('J') && !this.cards.contains("JJJJJ")) {
                    int Jquantity = occ.remove('J');
                    this.occurences = new ArrayList<>(occ.values().stream().sorted(Collections.reverseOrder()).toList());
                    this.occurences.set(0, this.occurences.get(0) + Jquantity);
                } else {
                    this.occurences = new ArrayList<>(occ.values().stream().sorted(Collections.reverseOrder()).toList());
                }
            } else {
                this.occurences = new ArrayList<>(occ.values().stream().sorted(Collections.reverseOrder()).toList());
            }
            this.type = type;
        }

        @Override
        public int compareTo(Object o) {
            Hand other = (Hand) o;
            if (other.occurences.size() != this.occurences.size()) {
                return other.occurences.size() - this.occurences.size();
            } else {
                if (!this.occurences.get(0).equals(other.occurences.get(0))) {
                    return this.occurences.get(0) - other.occurences.get(0);
                }
                return this.getPoints(other.cards);
            }

        }

        private int getPoints(String s) {
            for (int i = 0; i < this.cards.length(); i++) {
                if (this.cards.charAt(i) != s.charAt(i)) {
                    if (this.type) {
                        return valuesPoint2.get(this.cards.charAt(i)) - valuesPoint2.get(s.charAt(i));
                    } else {
                        return valuesPoint1.get(this.cards.charAt(i)) - valuesPoint1.get(s.charAt(i));
                    }
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return cards + " " + points + " " + occurences;
        }
    }

    // works but bad idea to use this

    /*public class HandBetter implements Comparable {
        private long cardVal;
        private long points;


        public HandBetter(String cards) {
            this.cardVal = 0;
            this.points = Long.parseLong(cards.split(" ")[1]);
            Map<Character, Integer> occ = new HashMap<>();
            occ.put('0', 0);
            String val = "";

            for (Character card : cards.split(" ")[0].toCharArray()) {
                if (occ.containsKey(card)) {
                    occ.put(card, occ.get(card) + 1);
                } else {
                    occ.put(card, 1);
                }
                val += valuesPointBetter.get(card);
            }

            List<Integer> res = occ.values().stream().sorted(Collections.reverseOrder()).toList();
            val = res.get(0) + "" + res.get(1) + val;
            this.cardVal = Long.parseLong(val);

        }

        @Override
        public int compareTo(Object o) {
            HandBetter other = (HandBetter) o;
            return this.cardVal > other.cardVal ? 1 : -1;
        }

        @Override
        public String toString() {
            return "HandBetter{" +
                    "res=" + cardVal +
                    ", bet=" + points +
                    '}';
        }
    }*/

    /*    public String resolvePartBetter(String input, Timer timer) {
        timer.start();
        List<HandBetter> data = new ArrayList<>(this.getData(input))
                .parallelStream()
                .map(HandBetter::new)
                .sorted()
                .toList();
        long sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i).points * (i + 1);
        }

        timer.stop();
        return sum + "";
    }*/

    /*    private final static Map<Character, String> valuesPointBetter = Map.ofEntries(
            Map.entry('2', "02"),
            Map.entry('3', "03"),
            Map.entry('4', "04"),
            Map.entry('5', "05"),
            Map.entry('6', "06"),
            Map.entry('7', "07"),
            Map.entry('8', "08"),
            Map.entry('9', "09"),
            Map.entry('T', "10"),
            Map.entry('J', "11"),
            Map.entry('Q', "12"),
            Map.entry('K', "13"),
            Map.entry('A', "14")
    );*/
}