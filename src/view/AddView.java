package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class AddView {
    private final JFrame frame = new JFrame(" Add a Student..");
    final JTextField[] textFields;
    private final Controller controller;
    private static final int SIZE = 4;

    public AddView(final Controller contr) {
        this.controller = contr;
        final JPanel table = new JPanel();
        this.textFields = new JTextField[5];
        table.setLayout(new GridLayout(5, 2, 5, 5));
        grLayoutAdd(table, textFields, 0, "  Student Id:");
        grLayoutAdd(table, textFields, 1, "  Name:");
        grLayoutAdd(table, textFields, 2, "  Surname:");
        grLayoutAdd(table, textFields, 3, "  Immatriculation Year:");
        grLayoutAdd(table, textFields, 4, "  Grades (or empty): ");

        final JButton done = new JButton("DONE");
        done.addActionListener((ActionEvent e) -> {
            try {
                 final int id = Integer.parseInt(textFields[0].getText());
                 final String name = textFields[1].getText();
                 final String surname = textFields[2].getText();
                 final int ImmYear = Integer.parseInt(textFields[3].getText());
                 final String grades = textFields[4].getText();
                 this.controller.notifyAddStudent(id, name, surname, ImmYear, grades);
            } catch (NumberFormatException ex) {
                showError("Please, insert a number in both ID and Immatriculation Year.");
            }
        });
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(table, BorderLayout.CENTER);
        panel.add(done, BorderLayout.SOUTH);

        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void grLayoutAdd(JPanel panel, JTextField[] textFields, int i,  String description) {
            panel.add(new JLabel(description));
            textFields[i] = new JTextField();
            panel.add(textFields[i]);
    }

    public void showError(final String message) {
        JOptionPane.showMessageDialog(frame, message,
         "Error..", JOptionPane.ERROR_MESSAGE);
    }
    public void showDuplicateMessage(final String message) {
        int option = JOptionPane.showConfirmDialog(frame, message, "Duplicate student..", JOptionPane.YES_NO_OPTION);
        if ( option == JOptionPane.YES_OPTION) {
            this.controller.notifyAddGrades(Integer.parseInt(textFields[0].getText()), textFields[4].getText());
        }
    }
    public void showConfirmMessage(final String message) {
        JOptionPane.showMessageDialog(frame, message, "OK", JOptionPane.INFORMATION_MESSAGE);
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
