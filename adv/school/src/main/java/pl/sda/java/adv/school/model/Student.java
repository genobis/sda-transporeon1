package pl.sda.java.adv.school.model;

public class Student extends Person {
    private short startYear;
    private byte schoolYear;
    private char classCode;


    public short getStartYear() {
        return startYear;
    }

    public void setStartYear(short startYear) {
        this.startYear = startYear;
    }

    public byte getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(byte schoolYear) {
        this.schoolYear = schoolYear;
    }

    public char getClassCode() {
        return classCode;
    }

    public void setClassCode(char classCode) {
        this.classCode = classCode;
    }

    @Override
    public String toString() {
        return "Student{" +
                "startYear=" + startYear +
                ", schoolYear=" + schoolYear +
                ", classCode=" + classCode +
                '}' + super.toString();
    }
}
