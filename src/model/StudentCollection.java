package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentCollection implements java.io.Serializable{
    private static final long serialVersionUID = 1635142637382L;
    private Map<Integer, Student> students = new HashMap<>();

    public void addStudent(int id, String name, String sur, int immYear, List<String> grades) {
        students.put(id, new Student(name, sur, immYear, grades));
    }

    public boolean verifyStudent(final int id, final String name, final String surname) {
        if( (!name.isEmpty() && !surname.isEmpty()) && !students.containsKey(id)) {
            return true;
        }
        return false;
    }

    public boolean isPresent(final int id) {
        return this.students.containsKey(id);
    }

    public void removeStudent(int id) {
            students.remove(id);
    }

    public void addGradeForStudent(int id, List<String> grades) {
            students.get(id).addGrade(grades);
    }

    public Map<Integer, Student> getStudents() {
        return this.students;
    }

    public boolean isEmpty() {
        return this.students.isEmpty();
    }
}
