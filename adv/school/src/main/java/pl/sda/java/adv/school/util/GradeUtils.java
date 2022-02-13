package pl.sda.java.adv.school.util;

import pl.sda.java.adv.school.model.Grade;
import pl.sda.java.adv.school.model.GradeWeight;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class GradeUtils {
    public static double gradesAverage(Collection<Grade> grades) {
        List<Double> egzValues = grades.stream().
                filter(grade -> grade.getGradeWeight() == GradeWeight.EGZ)
                .map(Grade::getValue)
                .collect(Collectors.toList());

        List<Double> pytValues = grades.stream().
                filter(grade -> grade.getGradeWeight() == GradeWeight.PYT)
                .map(Grade::getValue)
                .collect(Collectors.toList());

        List<Double> aktValues = grades.stream().
                filter(grade -> grade.getGradeWeight() == GradeWeight.AKT)
                .map(Grade::getValue)
                .collect(Collectors.toList());

        double egzSum = egzValues.stream().reduce(0.0, Double::sum);
        double pytSum = pytValues.stream().reduce(0.0, Double::sum);
        double aktSum = aktValues.stream().reduce(0.0, Double::sum);

        double result = (egzSum * 2 + pytSum * 1.5 + aktSum) / (egzValues.size() * 2 + pytValues.size() * 1.5 + aktValues.size());

        try {
            return (new BigDecimal(result).setScale(2, RoundingMode.HALF_UP)).doubleValue();
        } catch (Exception e) {
            //e.printStackTrace();
            return 0.0;
        }


        //return bd.doubleValue();

    }
}
