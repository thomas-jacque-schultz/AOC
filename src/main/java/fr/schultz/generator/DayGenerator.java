package fr.schultz.generator;

import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.time.Instant;

@NoArgsConstructor
public class DayGenerator {

    public String resolve(int year, int day, int part, String input) throws Exception {
        long start = Instant.now().toEpochMilli();
        String className = String.format("Resolve%d_%d", year, day);

        Class<?> cls = Class.forName("fr.schultz.aoc.year2023." + className);
        Method method = cls.getMethod("resolvePart" + part, String.class);

        Object obj = cls.getDeclaredConstructor().newInstance();


        String result = (String) method.invoke(obj, input);
        long end = Instant.now().toEpochMilli();
        System.out.println("Time taken: " + (end - start) + "ms");
        return result;
    }

}
