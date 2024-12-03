import java.io.FileNotFoundException;
import java.io.IOException;

import controller.Controller;
import view.MainView;

public class TestApp {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        final Controller contr = new Controller();
        final MainView view = new MainView(contr);
        contr.attachMainView(view);
        view.display();
    }
}
