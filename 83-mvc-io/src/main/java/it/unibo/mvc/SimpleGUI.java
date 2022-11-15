package it.unibo.mvc;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String PRINT = "Print";
    private static final String SHOW_HISTORY = "Show History";
    private final JFrame frame = new JFrame();

    /**.
     * A gui that print the text wrote in textfield on stdout,
     * and can show the history of printed string in the textarea 
     * 
     * @param ctrl the contreller ussed for this GUI
     * 
     */
    public SimpleGUI(final SimpleController ctrl) {
        final JPanel panel1 = new JPanel();
        final JTextArea textArea1 = new JTextArea();
        final JTextField textField1 = new JTextField();
        final JButton print = new JButton(PRINT);
        final JButton showHistory = new JButton(SHOW_HISTORY);
        textArea1.setEditable(false);

        //action listener
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                ctrl.setNextString(textField1.getText());
                ctrl.printString();
            }

        });

        showHistory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final var history = ctrl.getHistoryString();
                textArea1.setText("");
                for (int i = 0; i < history.size(); i++) {
                    textArea1.append(history.get(i) + '\n');
                }

            }

        });

        //set layout
        panel1.setLayout(new BorderLayout());
        panel1.add(textArea1, BorderLayout.CENTER);
        panel1.add(textField1, BorderLayout.NORTH);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(print, BorderLayout.WEST);
        panel2.add(showHistory, BorderLayout.EAST);
        panel1.add(panel2, BorderLayout.SOUTH);

        // Frame size and location
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 3, sh / 3);
        frame.setLocationByPlatform(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**.
    * used to set the JFrame visibile 
    * 
    */
    private void display() {
        frame.setVisible(true);
    }
    /**
     * 
     * @param args
     */
    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.display();
    }
}
