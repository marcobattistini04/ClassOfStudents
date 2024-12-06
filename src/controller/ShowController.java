package controller;

import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import model.Student;
import model.StudentCollection;
import view.ShowView;

public class ShowController {
    private final static int MAXGRADE = 33;
    private final StudentCollection model;
    
    public ShowController(final StudentCollection model) {
        this.model = model;
    }

    public void manageShowingAllStudents(final ShowView showView) {
        final Set<Map.Entry<Integer, Student>> students = model.getStudents().entrySet();
        Iterator<Map.Entry<Integer, Student>> studentsIter = students.iterator();
        Map.Entry<Integer, Student> entry;
        int sum = 0;
        int cont = 0;
        double medium;

        showView.clear();

        while (studentsIter.hasNext()) {
            entry = studentsIter.next();
            List<String> grades = entry.getValue().getActualGrades();
            Iterator<String> gradesIter = grades.iterator();
            while (gradesIter.hasNext()) {
                final String grade = gradesIter.next();
                try {
                    sum = sum + Integer.parseInt(grade);
                    cont ++;
                } catch (NumberFormatException e) {
                    if (grade.equals("30L")) {
                        sum = sum + MAXGRADE;
                        cont ++;
                    }
                }
            }
            medium = (double) sum /cont;
            showView.printStudent(entry.getKey(), entry.getValue().getName(), 
            entry.getValue().getSurname(), entry.getValue().getImmatriculationYear(), entry.getValue().getActualGrades(), medium);
        }
    }
}
