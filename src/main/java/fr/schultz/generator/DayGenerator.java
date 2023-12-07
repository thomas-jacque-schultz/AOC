package fr.schultz.generator;

import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.time.Instant;

@NoArgsConstructor
public class DayGenerator {

    public void resolve(int year, int day, int part, String input) throws Exception {
        String className = String.format("Resolve%d_%d", year, day);

        Class<?> cls = Class.forName("fr.schultz.aoc.year2023." + className);
        Method method = cls.getMethod("resolvePart" + part, String.class, Timer.class);

        Object obj = cls.getDeclaredConstructor().newInstance();

        Timer timer = new Timer();
        String result = (String) method.invoke(obj, input, timer);
        System.out.println("Day : \t"+day +" \t Part : \t" + part+" \t Time taken: \t" + Timer.formatTime(timer.getDurationInMicro()) +  "  Result : " + result);
    }

}
