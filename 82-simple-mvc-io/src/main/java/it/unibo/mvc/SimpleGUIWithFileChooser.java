package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
public final class SimpleGUIWithFileChooser {
    
    private final String SAVE = "SAVE";
    private final String BROWSE = "BROWSE";
    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        final JTextArea textArea1 = new JTextArea();
        panel1.add(textArea1);
        final JButton save = new JButton(SAVE);
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

        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        final JTextArea textArea2 = new JTextArea(controller.getCourrentPath());
        textArea2.setEditable(false);
        final JButton browse = new JButton(BROWSE);
        browse.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(controller.getCourrentfile());
                final int result = chooser.showSaveDialog(frame);
                if(result == JFileChooser.APPROVE_OPTION) {
                    controller.setCourrentfile(chooser.getSelectedFile());
                    textArea2.setText(controller.getCourrentPath());
                } 
                if(result != JFileChooser.APPROVE_OPTION && result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, result, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel2.add(textArea2, BorderLayout.WEST);
        panel2.add(browse, BorderLayout.EAST);
        panel1.add(panel2, BorderLayout.NORTH);

        // Frame size and location
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 3, sh / 3);
        frame.setLocationByPlatform(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();    }
}
