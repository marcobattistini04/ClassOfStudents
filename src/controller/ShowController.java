package controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import model.Student;
import model.StudentCollection;
import view.ShowView;

public class ShowController {
    private final StudentCollection model;
    
    public ShowController(final StudentCollection model) {
        this.model = model;
    }

    public void manageShowingAllStudents(final ShowView showView) {
        final Set<Map.Entry<Integer, Student>> students = model.getStudents().entrySet();
        Iterator<Map.Entry<Integer, Student>> iter = students.iterator();
        Map.Entry<Integer, Student> entry;
      

        showView.clear();

        while (iter.hasNext()) {
            entry = iter.next();
            showView.printStudent(entry.getKey(), entry.getValue().getName(), 
            entry.getValue().getSurname(), entry.getValue().getImmatriculationYear(), entry.getValue().getActualGrades());
        }
    }
}
