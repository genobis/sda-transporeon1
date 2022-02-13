package pl.sda.java.adv.school.model;

public class Grade {
    private String studentId;
    private String subjectCode;
    private GradeWeight gradeWeight;
    private double value;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public GradeWeight getGradeWeight() {
        return gradeWeight;
    }

    public void setGradeWeight(GradeWeight gradeWeight) {
        this.gradeWeight = gradeWeight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "studentId='" + studentId + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", gradeWeight=" + gradeWeight +
                ", value=" + value +
                '}';
    }
}
