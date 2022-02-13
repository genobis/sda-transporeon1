package pl.sda.java.adv.school.model;

import java.time.LocalDate;
import java.util.Set;

public class Teacher extends Person {
    private Set<String> classTypes;

    public Set<String> getClassTypes() {
        return classTypes;
    }

    public void setClassTypes(Set<String> classTypes) {
        this.classTypes = classTypes;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "classTypes=" + classTypes +
                '}' + super.toString();
    }
}
