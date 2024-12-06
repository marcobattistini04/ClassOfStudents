package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.FileIO;
import model.Student;
import model.StudentCollection;
import view.AddView;

public class AddController {
     private StudentCollection model;

    public AddController(final StudentCollection model) {
        this.model = model;
    }

    public void manageAddingInitStudents(final FileIO fManager) throws ClassNotFoundException, IOException {
        final Map<Integer, Student> listOfStudents = fManager.readObjects();
        if( listOfStudents != null) {
            final Set<Map.Entry<Integer, Student>> students = listOfStudents.entrySet();
            Iterator<Map.Entry<Integer, Student>> iter = students.iterator();
            Map.Entry<Integer, Student> entry;

            while (iter.hasNext()) {
                entry = iter.next();
                
                this.model.addStudent(entry.getKey(), entry.getValue().getName(),
                entry.getValue().getSurname(), entry.getValue().getImmatriculationYear(), entry.getValue().getActualGrades());
            }
        }  
    }

    public boolean manageAddingStudent(final AddView addView, final int id, final String name, final String surname, final int immYear, final String grades) {
        List<String> list = new ArrayList<>();

        if(model.verifyStudent(id, name, surname)) {
            if(grades.isEmpty()) {
                model.addStudent(id, name, surname, immYear, list);
            } else {
                String[] words = grades.split(" ");
                for (final String word : words) {
                    list.addFirst(word);
                }
            }
                model.addStudent(id, name, surname, immYear, list);
                addView.showConfirmMessage("Student added correctly");
                return true;
            
        } else {
            if (!name.isEmpty() && !surname.isEmpty()) {
                addView.showDuplicateMessage("This student is already present. Do you want to add new Grades?");
               
            } else {
                addView.showError("Cannot add new student because of invalid entrans");
            }  
            return false;
        }
    }

    public boolean manageAggingGrades(final AddView addView, final int id, final String grades) {
        List<String> list = new ArrayList<>();
        String[] words = grades.split(" ");
        for (final String word : words) {
            list.addFirst(word);
        }
        this.model.addGradeForStudent(id, list);
        addView.showConfirmMessage("grades added correctly");
        return true;
    }
}
