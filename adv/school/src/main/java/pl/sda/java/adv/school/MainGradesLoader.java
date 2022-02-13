package pl.sda.java.adv.school;

import pl.sda.java.adv.school.model.Grade;
import pl.sda.java.adv.school.model.GradeWeight;
import pl.sda.java.adv.school.model.StudentGrades;
import pl.sda.java.adv.school.util.GradeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class MainGradesLoader {
    public static void main(String[] args) {
        CsvGradesLoader csvGradesLoader = new CsvGradesLoader();
        List<Grade> grades;

        try (InputStream inputStream = Files.newInputStream(Path.of("example/grades.csv"))) {
            grades = csvGradesLoader.loadData(inputStream);
        } catch (IOException e) {
            grades = Collections.emptyList();
            e.printStackTrace();
        }

        //Optional<Double> result = GradeUtils.gradesAverage(grades.stream().limit(5).collect(Collectors.toList()));
        //System.out.println(result);

        System.out.println("All grades");
        grades.stream().forEach(System.out::println);

        System.out.println("\nAll grades with a value lower than 3.0");
        grades.stream()
                .sorted(Comparator.comparing(Grade::getValue))
                .takeWhile(grade -> grade.getValue() < 3.0)
                .forEach(System.out::println);

        System.out.println("\nAll grades from exams");
        grades.stream()
                .filter(grade -> grade.getGradeWeight() == GradeWeight.EGZ)
                .forEach(System.out::println);

        System.out.println("\nA weighted average for student 00001001");
        double result = GradeUtils.gradesAverage(
                grades.stream()
                .filter(grade -> grade.getStudentId().equals("00001001"))
                .collect(Collectors.toList())
        );
        System.out.println(result);

        System.out.println("\nStudentGrades methods");
        StudentGrades studentGrades1 = new StudentGrades("00001001");
        System.out.println(studentGrades1.getAverage());
        System.out.println("Average in MAT");
        System.out.println(studentGrades1.getAverage("MAT"));

        System.out.println("\nStudents' averages");
        Set<String> idSet = grades.stream()
                .map(Grade::getStudentId)
                .collect(Collectors.toSet());
        LinkedList<StudentGrades> studentGradesList = new LinkedList<>();
        idSet.stream().forEach(id -> studentGradesList.addLast(new StudentGrades(id)));

        studentGradesList.stream()
                .sorted(Comparator.comparing(StudentGrades::getStudentID))
                .forEach(System.out::println);


    }
}
