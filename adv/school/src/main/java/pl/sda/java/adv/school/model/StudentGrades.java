package pl.sda.java.adv.school.model;

import pl.sda.java.adv.school.CsvGradesLoader;
import pl.sda.java.adv.school.util.GradeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentGrades {
    private String studentID;
    private List<Grade> grades;

    private List<Grade> loadData() {
        CsvGradesLoader csvGradesLoader = new CsvGradesLoader();

        try (InputStream inputStream = Files.newInputStream(Path.of("example/grades.csv"))) {
            return csvGradesLoader.loadData(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public double getAverage() {
        return GradeUtils.gradesAverage(grades);
    }

    public double getAverage(String subjectCode){
        return GradeUtils.gradesAverage(
                grades.stream()
                        .filter(grade -> grade.getSubjectCode().equals(subjectCode))
                        .collect(Collectors.toList())
        );
    }

    public StudentGrades(String studentID) {
        this.studentID = studentID;
        this.grades = loadData().stream()
                .filter(grade -> grade.getStudentId().equals(studentID))
                .collect(Collectors.toList());
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public String toString() {
        return "STUDENT_ID: " + studentID + '\'' +
                ", TOTAL_AVERAGE=" + getAverage() +
                ", MAT_AVERAGE=" + getAverage("MAT") +
                ", POL_AVERAGE=" +  getAverage("POL");
    }
}
