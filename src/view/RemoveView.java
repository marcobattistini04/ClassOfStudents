package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class RemoveView {
    private final JFrame frame = new JFrame(" Remove a Student..");
    private final Controller controller;
    private static final int SIZE = 5;

    public RemoveView(final Controller contr) {
        this.controller = contr;
        final JTextField info = new JTextField();
        info.setEditable(false);
        info.setText("Delete a student by giving the ID:");
        final JTextField IDSpace = new JTextField();
        final JButton button = new JButton("DELETE");
        button.addActionListener((ActionEvent e) -> {
            try {
                this.controller.notifyDeleteStudent(Integer.parseInt(IDSpace.getText()));
            } catch (NumberFormatException ex) {
                showError("Please, insert the ID of a Student" );
            }
        });
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(info, BorderLayout.NORTH);
        panel.add(IDSpace, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void showError(final String message) {
        JOptionPane.showMessageDialog(frame, message,
         "Error..", JOptionPane.ERROR_MESSAGE);
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
