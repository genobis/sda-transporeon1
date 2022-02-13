package pl.sda.java.adv.school;

import pl.sda.java.adv.school.model.Grade;
import pl.sda.java.adv.school.model.GradeWeight;
import pl.sda.java.adv.school.util.AbstractCsvLoader;

import java.util.Optional;

public class CsvGradesLoader extends AbstractCsvLoader<Grade> {
    protected Optional<Grade> parseLine(String value) {
        String[] values = value.replaceAll("\"", "").split(",");

        if (values.length != 4) {
            return Optional.empty();
        } else {
            try {
                Grade grade = new Grade();
                grade.setStudentId(values[0]);
                grade.setSubjectCode(values[1]);
                if (values[2].equals("EGZ"))
                    grade.setGradeWeight(GradeWeight.EGZ);
                else if (values[2].equals("PYT"))
                    grade.setGradeWeight(GradeWeight.PYT);
                else
                    grade.setGradeWeight(GradeWeight.AKT);
                grade.setValue(Double.parseDouble(values[3]));
                return Optional.of(grade);
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

    }

}
