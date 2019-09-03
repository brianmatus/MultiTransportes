package main;

import swing.LoginWindow;
import swing.MainWindow;
import util.Hash;
import java.security.NoSuchAlgorithmException;

class Main {
    public static void main(String[] args){
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);

        MainWindow mainWindow = new MainWindow();
        //mainWindow.setVisible(true);

        try {
            String hashedPassword = Hash.getTextHash("Contraseña super segura");
            System.out.println("hashedPassword = " + hashedPassword);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

}
