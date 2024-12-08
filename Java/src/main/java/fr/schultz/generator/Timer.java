package fr.schultz.generator;

public class Timer {
    Long start=0L;
    Long end=0L;

    public Timer() {

    }

    public void start() {
        start = System.nanoTime();
    }

    public void stop() {
        end = System.nanoTime();
    }

    public Long getDurationInNano() {
        return end - start;
    }
    public Long getDurationInMicro() {
        return (end - start) / 1000;
    }
    public Long getDurationInMilli() {
        return (end - start) / 1000000;
    }

    public static String formatTime(long micros) {
        long seconds = micros / 1_000_000;
        long remainingMicros = micros % 1_000_000;
        long millis = remainingMicros / 1_000;
        long remainingMillis = remainingMicros % 1_000;

        return seconds + "\ts\t" + millis + "\tms\t" + remainingMillis + "\tµs\t";
    }


}
