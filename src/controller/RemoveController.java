package controller;

import model.StudentCollection;
import view.RemoveView;

public class RemoveController {
   private final StudentCollection model;
   public RemoveController(final StudentCollection model) {
        this.model = model;
   }

   public boolean manageRemoveStudent(final RemoveView removeView, final int id) {
    if(model.isPresent(id)) {
        model.removeStudent(id);
        removeView.showConfirmMessage("Student removed correctly");
        return true;
    } else {
        removeView.showError("Cannot remove any student with ID: " + id);
        return false;
    }
   }
}
