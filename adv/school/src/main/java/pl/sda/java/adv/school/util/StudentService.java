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

    public Map<String, List<Student>> getCityToStudentsMap() {
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
        Comparator<Student> cityComparator = Comparator.comparing(student -> student.getAddress().getCity());
        return students.stream()
                //.sorted(Comparator.comparing((Function<Student,String>) student -> student.getAddress().getCity()).thenComparing(Student::getLastName))
                .sorted(cityComparator.thenComparing(Student::getLastName))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByYearSortedByLastAndFirstName(byte schoolYear) {
        return students.stream()
                .filter(student -> student.getSchoolYear() == schoolYear)
                .sorted(Comparator.comparing(Student::getLastName)
                        .thenComparing(Student::getFirstName))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsWhichRepeatedAYear() {
        return students.stream()
                .filter(student -> 2022 - student.getStartYear() > student.getSchoolYear())
                .collect(Collectors.toList());

    }

    public Map<String, Student> getOldestStudentFromEachCity() {
//        return students.stream()
//                .collect(Collectors.groupingBy(student -> student.getAddress().getCity(),
//                        Collectors.minBy(Comparator.comparing(Student::getBirthDate))))
//                .values()
//                .stream()
//                .map(Optional::get)
//                .collect(Collectors.toUnmodifiableMap(
//                        student -> student.getAddress().getCity(),
//                        Function.identity()
//                ));

        return students.stream()
                .collect(Collectors.toUnmodifiableMap(
                        student -> student.getAddress().getCity(),
                        student -> student,
                        (student1, student2) -> {
                            if (student1.getBirthDate().isBefore(student2.getBirthDate())) {
                                return student1;
                            }
                            return student2;
                        }
                ));
    }

    public double getRatioOfStudentsNotFrom(String city) {
        long studentsNotFromCity = students.stream()
                .filter(student -> !student.getAddress().getCity().equals(city))
                .count();

        return studentsNotFromCity * 100.0/ students.size() ;
    }
}
