package swing;

import user.User;
import user.UserHandler;

import java.awt.*;
import javax.swing.*;

public class LoginWindow extends JFrame {

    public LoginWindow() {
        //construct components
        JButton loginButton = new JButton("Iniciar Sesión");

        JLabel jcomp2 = new JLabel("Inicio de Sesión");
        JTextField userTextField = new JTextField(5);
        JPasswordField passwordTextField = new JPasswordField(5);
        JLabel jcomp5 = new JLabel("Usuario");
        JLabel jcomp6 = new JLabel("Contraseña");

        //set components properties
        userTextField.setToolTipText ("Usuario");
        passwordTextField.setToolTipText ("Contraseña");

        //adjust size and set layout
        setBounds(new Rectangle(286, 307));
        setLayout (null);

        loginButton.addActionListener(e -> {
            String username = userTextField.getText();
            String enteredPassword = new String(passwordTextField.getPassword());

            User[] usersArray = UserHandler.getUsers();
            boolean userExists = false;
            for (User user : usersArray) {
                if (user.getUsername().equals(username)) {
                    userExists = true;
                    if (user.matchPassword(enteredPassword)) {
                        System.out.println("GG ez");
                        CountryListWindow countryListWindow = new CountryListWindow();
                        countryListWindow.setVisible(true);
                        this.setVisible(false);
                    }
                    else {
                        System.out.println("Contraseña invalida");
                    }
                }
            }
            if (!userExists) {
                System.out.println("Usuario inexistente");
            }
        });

        //add components
        add (loginButton);
        add (jcomp2);
        add (userTextField);
        add (passwordTextField);
        add (jcomp5);
        add (jcomp6);

        //set component bounds (only needed by Absolute Positioning)
        loginButton.setBounds (80, 220, 140, 25);
        jcomp2.setBounds (95, 10, 100, 25);
        userTextField.setBounds (60, 110, 180, 25);
        passwordTextField.setBounds (60, 175, 180, 25);
        jcomp5.setBounds (60, 90, 100, 25);
        jcomp6.setBounds (60, 155, 100, 25);
    }
}
