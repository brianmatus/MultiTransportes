package swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LoginWindow extends JFrame {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton olaKAse;
    private JLabel answerLabel;

    public LoginWindow() {

        setBounds(new Rectangle(500, 600));
        setLocationRelativeTo(null);
        setTitle("Ventana principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //construct components
        jcomp1 = new JButton ("Button 1");
        jcomp2 = new JButton ("Button 2");
        olaKAse = new JButton ("Ola k ase");
        answerLabel = new JLabel ("--");

        //adjust size and set layout
        setPreferredSize (new Dimension (624, 388));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (olaKAse);
        add (answerLabel);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (25, 35, 100, 20);
        jcomp2.setBounds (55, 65, 100, 20);
        olaKAse.setBounds (460, 120, 140, 20);
        answerLabel.setBounds (245, 165, 100, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("LoginWindow");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new LoginWindow());
        frame.pack();
        frame.setVisible (true);
    }
}
