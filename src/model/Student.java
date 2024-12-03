package model;

import java.util.ArrayList;
import java.util.List;

public class Student implements java.io.Serializable{
    private static final long serialVersionUID = 567742502623265945L;
    private final String  name;
    private final String  surname;
    private final int immatriculationYear;
    private List<String> grades = new ArrayList<>();
    public Student(String name, String surname, int imYear, List<String> actualGrades) {
        this.name = name;
        this.surname = surname;
        this.immatriculationYear = imYear;
        this.grades = actualGrades;
    }

    public void addGrade(List<String> grades) {
        this.grades.addAll(grades);
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getImmatriculationYear() {
        return this.immatriculationYear;
    }

    /* DO NOT USE LIST.COPYOF() beacuse it would throw Unsupported operationException() */
    public List<String> getActualGrades() {
        List<String> actualGrades = new ArrayList<>();
        actualGrades.addAll(this.grades); 
        return actualGrades;
    }

}