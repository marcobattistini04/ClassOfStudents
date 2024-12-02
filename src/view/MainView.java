package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView {
    private static final int SIZE = 5;
    private final JFrame frame = new JFrame();
    private final JButton add;
    private final JButton students;
    private final JButton remove;
    private final Controller controller;
        public MainView(final Controller contr) {
        this.controller = contr;

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        this.add = new JButton("ADD");
        this.students = new JButton("STUDENTS");
        students.setEnabled(false);
        this.remove = new JButton("REMOVE");
        remove.setEnabled(false);
        buttons.add(add, FlowLayout.LEFT);
        buttons.add(students, FlowLayout.CENTER);
        buttons.add(remove, FlowLayout.RIGHT);

        add.addActionListener((ActionEvent e) -> {
            controller.notifyPressedAdd();
            students.setEnabled(true);
            remove.setEnabled(true);
        });
        students.addActionListener((ActionEvent e) -> {
            controller.notifyPressedStudents();
        });
        remove.addActionListener((ActionEvent e) -> {
            controller.notifyPressedRemove();
        });

        final JTextField title = new JTextField();
        title.setText("A simple app to maintain a class of student");
        title.setEditable(false);
        final JTextField readMe = new JTextField();
        readMe.setText("Choose between adding, removing a student or showing all the students");

        panel.add(title, BorderLayout.NORTH);
        panel.add(readMe, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e ) {
                int n = JOptionPane.showConfirmDialog(frame, "Do you really want to quit?", "Quitting..", JOptionPane.YES_NO_OPTION);
                if ( n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public void unlockButtons() {
        this.remove.setEnabled(true);
    }

    public void lockButtons() {
        this.remove.setEnabled(false);
    }
    public void display() {
        this.frame.setLocationByPlatform(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth() / (SIZE - 2);
        int height = (int) screenSize.getHeight() /SIZE;
        this.frame.setSize(width, height);
        this.frame.setVisible(true);
    }

}


