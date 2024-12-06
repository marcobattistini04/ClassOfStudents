import java.io.FileNotFoundException;
import java.io.IOException;

import controller.MainController;
import view.MainView;

public class TestApp {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        final MainController contr = new MainController();
        final MainView view = new MainView(contr);
        contr.attachMainView(view);
        view.display();
    }
}
