package pl.sda.java.adv.school;

import pl.sda.java.adv.school.model.Person;
import pl.sda.java.adv.school.model.Student;
import pl.sda.java.adv.school.model.Teacher;
import pl.sda.java.adv.school.util.PersonFirstNameLastNameComparator;

import java.util.*;
import java.util.function.Function;

public class MainDataClassesOverview {

    public static void main(String[] args) {
        Student student1 = new Student();

        student1.setFirstName("Michał");
        student1.setLastName("Zielinski");

        Student student2 = new Student();

        student2.setFirstName("Bogdan");
        student2.setLastName("Czarny");

        Student student3 = new Student();

        student3.setFirstName("Bożydar");
        student3.setLastName("Filar");

        Student student4 = new Student();

        student4.setFirstName("Amadeusz");
        student4.setLastName("Tyrała");

        Student student5 = new Student();
        student5.setFirstName("Julia");
        student5.setLastName("Filar");

        System.out.println("\nLet's have an array we can iterate over");

        Student[] studentsArray = new Student[]{student1, student2, student3, student4, student5};

        for (Student student : studentsArray) {
            System.out.println(student);
        }

        System.out.println("\nLet's do the same with list");

        List<Student> studentsList = List.of(student1, student2, student3, student4, student5);

        for (Student student : studentsList) {
            System.out.println(student);
        }

        System.out.println("\nLet's do the same with list the old school way (not recommended)");

        for (int i = 0; i < studentsList.size(); i++) {
            System.out.println(studentsList.get(i));
        }

        System.out.println("\nLet's do the same with list using forEach");

        studentsList.forEach(System.out::println);

        System.out.println("\nLet's use stream on array");

        Arrays.stream(studentsArray).forEach(System.out::println);

        System.out.println("\nLet's sort the list");

        //we need to create the ArrayList because result of List.of(...) is NOT mutable!
        List<Student> studentsArrayList = new ArrayList<>(studentsList);
        Collections.sort(studentsArrayList);
        studentsArrayList.forEach(System.out::println);

        System.out.println("\nLet's have some teachers");
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Genowefa");
        teacher1.setLastName("Pompke");
        teacher1.setClassTypes(Set.of("MAT", "INF"));

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Adam");
        teacher2.setLastName("Juzprzekładam");
        Set<String> classesSet = new HashSet<>();
        classesSet.add("POL");
        classesSet.add("HIS");
        classesSet.add("POL");
        teacher2.setClassTypes(classesSet);

        List<Teacher> teachersList = new LinkedList<>();
        teachersList.add(teacher1);
        teachersList.add(teacher2);
        teachersList.forEach(System.out::println);

        System.out.println("\nLets print the list of all people");
        List<Person> personList = new LinkedList<>();
        personList.addAll(studentsArrayList);
        personList.addAll(teachersList);

        Collections.sort(personList);

        personList.forEach(System.out::println);

        System.out.println("\nLet's use comparator");

//        Collections.sort(personList, PersonFirstNameLastNameComparator.INSTANCE);

//        Collections.sort(personList, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());
//
//                if (firstNameResult == 0) {
//                    return o1.getLastName().compareTo(o2.getLastName());
//                }
//                return firstNameResult;
//            }
//        });

//        Collections.sort(personList, new NestedPersonFirstNameLastNameComparator());

        //Collections.sort(personList, ((o1, o2) -> {
        //    int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());
//
        //    if (firstNameResult == 0) {
        //        return o1.getLastName().compareTo(o2.getLastName());
        //    }
        //    return firstNameResult;
        //}));

        Comparator<Person> firstNameComparator = Comparator.comparing(Person::getLastName)
                        .thenComparing(Person::getFirstName);

        Collections.sort(personList, firstNameComparator);


        personList.forEach(person -> System.out.println(person));

//        personList.forEach(new Consumer<Person>() {
//            @Override
//            public void accept(Person person) {
//                System.out.println(person);
//            }
//        });

        System.out.println("\nLet's use Iterator directly");

        Iterator<Person> personIterator = personList.iterator();
        while (personIterator.hasNext()) {
            Person person = personIterator.next();
            System.out.println(person);
        }

    }

    //Internal class (bound to object)
    public class PersonFirstNameLastNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());

            if (firstNameResult == 0) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
            return firstNameResult;
        }
    }

    //Nested (static internal) class, available from static context
    public static class NestedPersonFirstNameLastNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());

            if (firstNameResult == 0) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
            return firstNameResult;
        }
    }
}
