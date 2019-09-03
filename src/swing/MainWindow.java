package swing;

import bean.User;
import model.UserTable;
import util.ArrayPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class MainWindow extends JFrame {
    private User[] usersArray;
    private final JTextField name_TextField = new JTextField();
    private final JTextField lastName_TextField = new JTextField();
    private final JComboBox<String> type_Combobox = new JComboBox<>();
    private final JTable userTable = new JTable();

    public MainWindow(){
        super();
        setBounds(new Rectangle(500, 600));
        setLocationRelativeTo(null);
        setTitle("Ventana principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usersArray = new User[100];
        User u1 = new User("Brian","Matus", "Administrador");
        User u2 = new User("Hector", "Rodriguez", "Administrador");
        User u3 = new User("Marta", "Castillo", "Moderador");
        User u4 = new User("Miau", "Pio", "Moderador");
        User u5 = new User("GG", "ez", "Moderador");

        addUser(u1);
        addUser(u2);
        addUser(u3);
        addUser(u4);
        addUser(u5);
        updateTableData();

        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    new NewWindow(MainWindow.this,
                            true, usersArray, userTable).setVisible(true);
                }
            }
        });

        JScrollPane jsp = new JScrollPane(userTable);

        //Main container
        JPanel container1 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(container1, BoxLayout.X_AXIS);
        container1.setLayout(boxlayout);
        container1.add(jsp);

        //Interaction panel
        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.X_AXIS));


        //Fields
        name_TextField.setPreferredSize(new Dimension(70, HEIGHT));
        lastName_TextField.setPreferredSize(new Dimension(70, HEIGHT));
        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        //New User Data Fields
        type_Combobox.addItem("Usuario");
        interactionPanel.add(name_TextField);
        interactionPanel.add(lastName_TextField);
        interactionPanel.add(type_Combobox);

        JPanel mainPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.X_AXIS));
        mainPanel.add(interactionPanel);
        mainPanel.add(addButton);
        mainPanel.add(deleteButton);
        mainPanel.add(container1);
        add(mainPanel);

        addButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                addUserFromTable();
            }
        });

        deleteButton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(MainWindow.this, "¿Desea eliminar esta fila?",
                    "Eliminar fila", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                removeUser(userTable.getSelectedRow());
            }
        });
    }

    private void addUserFromTable(){
        User user = new User(
                name_TextField.getText(),
                lastName_TextField.getText(),
                Objects.requireNonNull(type_Combobox.getSelectedItem()).toString());

        //Clean fields
        name_TextField.setText("");
        lastName_TextField.setText("");

        addUser(user);
        updateTableData();
    }

    private void addUser(User user) {
        usersArray[ArrayPosition.getUserCounter()] = user;
    }

    private void removeUser(int index) {
        User[] newArray = new User[usersArray.length];
        System.arraycopy(usersArray,0,newArray,0,index);
        System.arraycopy(usersArray,index+1,newArray,index, usersArray.length-index-1);
        usersArray = newArray;
        ArrayPosition.removedUser();

        updateTableData();
    }

    private void updateTableData () {
        int index = ArrayPosition.getNotNullIndex(usersArray);
        User[] nonEmptyArray = new User[index];
        System.arraycopy(usersArray,0,nonEmptyArray,0,index);
        userTable.setModel(new UserTable(nonEmptyArray));
    }

}
