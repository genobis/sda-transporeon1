package pl.sda.java.adv.school;

import pl.sda.java.adv.school.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class MainStudentsLoader {
    public static void main(String[] args) {
        CsvStudentsLoader csvStudentsLoader = new CsvStudentsLoader();
        List<Student> students;

        try (InputStream inputStream = Files.newInputStream(Path.of("example/students.csv"))) {
            students = csvStudentsLoader.loadData(inputStream);
        } catch (IOException e) {
            students = Collections.emptyList();
            e.printStackTrace();
        }

        //All students
        students.forEach(System.out::println);

        System.out.println("\nSorted by age ascending");
        students.stream()
                .sorted(Comparator.comparing(Student::getBirthDate))
                .forEach(System.out::println);

        System.out.println("\nSorted by age descending");
        students.stream()
                .sorted(Comparator.comparing(Student::getBirthDate).reversed())
                .forEach(System.out::println);

        System.out.println("\nSorted by city and then last name - both ascending");
        students.stream()
                .sorted(Comparator.comparing(Student::getLastName))
                .sorted(Comparator.comparing((student -> student.getAddress().getCity())))
                .forEach(System.out::println);

        System.out.println("\n8th graders sorted by last name and first name");
        students.stream()
                .filter(student -> student.getSchoolYear() == 8)
                .sorted(Comparator.comparing(Student::getFirstName))
                .sorted(Comparator.comparing(Student::getLastName))
                .forEach(System.out::println);

        System.out.println("\nStudents that repeated at least one year of education");
        students.stream()
                .filter(student -> (2022 - student.getStartYear() > student.getSchoolYear()))
                .forEach(System.out::println);

        System.out.println("\nThe oldest student from each city");
        Set<String> cities = students.stream()
                .map(student -> student.getAddress().getCity())
                .collect(Collectors.toSet());

        students.stream()
                .sorted(Comparator.comparing(student -> student.getAddress().getCity()));


        // ------------- not finished

        System.out.println("\nPercentage of students that are not from Kraków: ");
        List<Student> studentsNotKrakow = students.stream()
                .sorted(Comparator.comparing(student -> student.getAddress().getCity()))
                .filter(student -> !student.getAddress().getCity().equals("Kraków"))
                .collect(Collectors.toList());
        System.out.println((double) (studentsNotKrakow.size()) / (double) (students.size()) * 100 + "%");
    }
}
