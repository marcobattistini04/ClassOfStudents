package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.FileIO;
import model.StudentCollection;
import view.AddView;
import view.MainView;
import view.RemoveView;
import view.ShowView;

public class MainController {
    private AddController addController;
    private ShowController showController;
    private RemoveController removeController;

    private MainView mainView;
    private AddView addView;
    private ShowView showView;
    private RemoveView removeView;

    private final StudentCollection model;
    final private FileIO fmanager;

    private boolean classModified = false;

    public MainController() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.model = new StudentCollection();
        this.fmanager = new FileIO();
        this.addController = new AddController(model);
        this.removeController = new RemoveController(model);
        this.showController = new ShowController(model);

        this.addController.manageAddingInitStudents(fmanager);
    }

    public void attachMainView(MainView view) {
        this.mainView = view;
    }

    public void notifyPressedAdd() {
        this.addView = new AddView(this);
        addView.display();
    }

    public void notifyAddStudent(final int id, final String name, final String surname, final int immYear, final String grades) {
       if (this.addController.manageAddingStudent(this.addView, id, name, surname, immYear, grades)) {
            this.classModified = true;
            this.mainView.unlockButtons();
       }
    }

    public void notifyAddGrades(final int id, final String grades) {
        this.addController.manageAggingGrades(this.addView, id, grades);
        this.classModified = true;
    }

    public void notifyPressedRemove() {
        this.removeView = new RemoveView(this);
        this.removeView.display();
    }

    public void notifyDeleteStudent(int id) {
        if (this.removeController.manageRemoveStudent(this.removeView, id)) {
            this.classModified = true;
            if (this.model.isEmpty()) {
                this.mainView.lockButtons();
            }
        }
    }

    public void notifyPressedStudents() {
        this.showView = new ShowView(this);
        this.showView.display();
    }

    public void notifyShowStudents() {
        this.showController.manageShowingAllStudents(this.showView);
    }

    public void notifySaved () throws IOException {
            fmanager.writeObject(model.getStudents());
            this.classModified = false;
        }

    public Boolean isClassModified () {
        return this.classModified;
    }

}
