import controller.Controller;
import view.MainView;

public class TestApp {
    public static void main(String[] args) {
        final Controller contr = new Controller();
        final MainView view = new MainView(contr);
        contr.attachMainView(view);
        view.display();
    }
}
