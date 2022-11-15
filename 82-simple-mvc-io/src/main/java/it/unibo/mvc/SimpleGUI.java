package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final String TITLE = "My first graphical interface";
    private final JFrame frame = new JFrame(TITLE);

    /**
    * A simple GUI that save in a file 
    *  Default file is output.txt in user directory
    * 
    */
    public SimpleGUI(final Controller controller) {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        final JTextArea textArea1 = new JTextArea();
        panel1.add(textArea1);
        final JButton save = new JButton("Save");
        panel1.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    controller.save(textArea1.getText());
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        // Frame size and location
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 3, sh / 3);
        frame.setLocationByPlatform(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
    * used to set the JFrame visibile 
    * 
    */
    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }
}
