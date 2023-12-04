package fr.schultz;

import fr.schultz.generator.Cookie;
import fr.schultz.generator.DayGenerator;
import fr.schultz.inputing.FileGenerator;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@NoArgsConstructor
public class DayPreparator {


    final FileGenerator fileGenerator = new FileGenerator();
    private final DayGenerator dayGenerator = new DayGenerator();


    public static void main(String[] args) throws Exception {
        //mainNow();
        mainParam(2023,1);
        mainParam(2023,2);
        mainParam(2023,3);
        mainParam(2023,4);
    }

    public static void mainParam(int y, int d) throws Exception {
        DayPreparator dayPreparator = new DayPreparator();
        String input = dayPreparator.fileGenerator.getData(y, d, Cookie.cookie);
        System.out.println(dayPreparator.dayGenerator.resolve(y, d, 1, input));
        System.out.println(dayPreparator.dayGenerator.resolve(y, d, 2, input));
    }



    public static void mainNow() throws Exception {
        DayPreparator dayPreparator = new DayPreparator();
        int year = LocalDate.now().getYear();
        int day = LocalDate.now().getDayOfMonth();
        String input = dayPreparator.fileGenerator.getData(year, day, Cookie.cookie);
        System.out.println(dayPreparator.dayGenerator.resolve(year, day, 1, input));
        System.out.println(dayPreparator.dayGenerator.resolve(year, day, 2, input));
    }

}
