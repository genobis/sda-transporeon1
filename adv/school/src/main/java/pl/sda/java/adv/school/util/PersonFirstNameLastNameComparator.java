package pl.sda.java.adv.school.util;

import pl.sda.java.adv.school.MainDataClassesOverview;
import pl.sda.java.adv.school.model.Person;

import java.util.Comparator;

public class PersonFirstNameLastNameComparator implements Comparator<Person> {
    public static final Comparator<Person> INSTANCE = new PersonFirstNameLastNameComparator();

    private PersonFirstNameLastNameComparator() {

    }

    @Override
    public int compare(Person o1, Person o2) {
        int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());
        if(firstNameResult == 0) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
        return firstNameResult;
    }
}
