package pl.sda.java.adv.school.util;

import pl.sda.java.adv.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private final List<Student> students;

    public StudentService(List<Student> students) {
        this.students = students;
    }

    public Map<String, Student> getStudentIdToStudentMap() {
        return students.stream()
                .collect(Collectors.toUnmodifiableMap(
                        Student::getId, // student -> student.getId()
                        student -> student //Function.identity()
                ));
    }

    public Map<String,List<Student>> getCityToStudentsMap() {
        return students.stream()
                .collect(Collectors.groupingBy(student -> student.getAddress().getCity()));
    }

    public List<Student> getByAgeAscending() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getBirthDate))
                .collect(Collectors.toList());
    }

    public List<Student> getByAgeDesc() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getBirthDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsSortedByCityAndLastName() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getLastName))
                .sorted(Comparator.comparing((student -> student.getAddress().getCity())))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByYearSortedByLastAndFirstName(byte schoolYear) {
        return students.stream()
                .filter(student -> student.getSchoolYear() == schoolYear)
                .sorted(Comparator.comparing(Student::getFirstName))
                .sorted(Comparator.comparing(Student::getLastName))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsWhichRepeatedAYear() {
        return students.stream()
                .filter(student -> (2022 - student.getStartYear() > student.getSchoolYear()))
                .collect(Collectors.toList());

    }

    /*public List<Student> getOldestStudentFromEachCity() {

    }*/

    public double getRatioOfStudentsNotFrom(String city) {
        List<Student> studentsNotKrakow = students.stream()
                .sorted(Comparator.comparing(student -> student.getAddress().getCity()))
                .filter(student -> !student.getAddress().getCity().equals("Kraków"))
                .collect(Collectors.toList());

        return (double) (studentsNotKrakow.size()) / (double) (students.size()) * 100;
    }
}
