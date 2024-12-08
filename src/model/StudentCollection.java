package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentCollection implements java.io.Serializable{
    private static final long serialVersionUID = 1635142637382L;
    private Map<Integer, Student> students = new HashMap<>();

    public void addStudent(int id, String name, String sur, int immYear, List<String> grades) {
        students.put(id, new Student(name, sur, immYear, grades));
    }

    public boolean verifyStudent(final int id, final int immYear, final String name, final String surname) {
        if( (!name.isEmpty() && !surname.isEmpty()) && !students.containsKey(id)) {
            if(!name.equals(surname) && !name.isEmpty() && !surname.isEmpty() && immYear <= LocalDate.now().getYear()) {
                return true;
            }
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
        Map<Integer, Student> defensiveCopy = new HashMap<>();
        defensiveCopy.putAll(this.students);
        return defensiveCopy;
    }

    public boolean isEmpty() {
        return this.students.isEmpty();
    }
}
