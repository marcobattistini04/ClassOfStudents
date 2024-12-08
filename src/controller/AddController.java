package controller;

import java.io.IOException;
import java.time.LocalDate;
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

        if(model.verifyStudent(id, immYear, name, surname)) {
            if(!grades.isEmpty()){
                String[] words = grades.split(" ");
                if(areGradesConsistent(words)) {
                    for (final String word : words) {
                        list.addFirst(word);
                    }
                } else {
                    addView.showError("Cannot add new student because of invalid Grades");
                    return false;
                }
            }
                model.addStudent(id, name, surname, immYear, list);
                addView.showConfirmMessage("Student added correctly");
                return true;
            
        } else {
           if (name.isEmpty() || surname.isEmpty() || name.equals(surname)) {
            addView.showError("Please, add valid Name and Surname");
           } else {
            if (immYear > LocalDate.now().getYear()) {
                addView.showError("Please, add a valid Immatriculation Year");
            } else {
                addView.showDuplicateMessage("The student is already present, do you want to add new Grades?");
            }
           }

           return false;
        }
    }

    public boolean manageAddingGrades(final AddView addView, final int id, final String grades) {
        List<String> list = new ArrayList<>();
        String[] words = grades.split(" ");
        if(areGradesConsistent(words)) {
            for (final String word : words) {
                list.addFirst(word);
            }
        } else {
            addView.showError("Pleae, add valid grades to the student");
            return false;
        }
        this.model.addGradeForStudent(id, list);
        addView.showConfirmMessage("grades added correctly");
        return true;
    }

    private boolean areGradesConsistent(String [] grades) {
        boolean consistent = true;
        int cont = 0;
        while (cont < grades.length && consistent) {
            try {
                Integer.parseInt(grades[cont]);
            } catch (NumberFormatException ex) {
                if (!grades[cont].equals("30L") && !grades[cont].equals("R")) {
                    consistent = false;
                }
            }
            cont ++;
        }
        return consistent;
    }
}

