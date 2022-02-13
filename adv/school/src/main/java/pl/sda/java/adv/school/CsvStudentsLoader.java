package pl.sda.java.adv.school;

import pl.sda.java.adv.school.model.Address;
import pl.sda.java.adv.school.model.Student;
import pl.sda.java.adv.school.util.AbstractCsvLoader;


import java.time.LocalDate;
import java.util.*;

public class CsvStudentsLoader extends AbstractCsvLoader<Student> {

    protected Optional<Student> parseLine(String value) {
        String[] values = value.replaceAll("\"", "")
                .split(",");

        if (values.length  < 10 ) {
            return Optional.empty();
        } else {
            try {
                Student student = new Student();
                student.setId(values[0]);
                student.setLastName(values[1]);
                student.setFirstName(values[2]);
                student.setStartYear(Short.parseShort(values[3]));
                student.setSchoolYear(Byte.parseByte(values[4]));
                student.setClassCode(values[5].charAt(0));
                student.setBirthDate(LocalDate.of(
                        Integer.parseInt(values[6]),
                        Integer.parseInt(values[7]),
                        Integer.parseInt(values[8])
                ));
                Address address = new Address();
                address.setCity(values[9]);
                student.setAddress(address);

                return Optional.of(student);
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
    }
}
