package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.MainController;

public class ShowView {
    private static final int SIZE = 3;
    private final JFrame frame = new JFrame();
    final JTextArea textArea = new JTextArea();

    public ShowView(MainController controller) {
        final JTextField title = new JTextField("Press SHOW to view all the students registered so far..");
        title.setEditable(false);

        final JButton button = new JButton("SHOW");
        button.addActionListener((ActionEvent e) -> {
            controller.notifyShowStudents();
        });

        this.textArea.setEditable(false);
        final JScrollPane scroll = new JScrollPane(this.textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(title, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void clear() {
        this.textArea.setText("");
    }
    public void printStudent(final int id, final String name, final String surname, final int immYear, final List<String> grades) {
        this.textArea.append("\n  Student ID: " + id);
        this.textArea.append("\n  Name: " + name);
        this.textArea.append("\n  Surname: " + surname);
        this.textArea.append("\n  ImmatriculationYear: " + immYear);
        this.textArea.append("\n  Grades: \n");
        for (final String grade : grades) {
            this.textArea.append("  " + grade);
        }
        this.textArea.append("\n\n");
    }

    public void display() {
        this.frame.setLocationByPlatform(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth() / SIZE;
        int height = (int) screenSize.getHeight() /SIZE;
        this.frame.setSize(width, height);
        this.frame.setVisible(true);
    }
}
