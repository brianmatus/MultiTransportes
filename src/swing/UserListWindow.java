package swing;

import user.User;
import user.UserHandler;
import model.UserTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

class UserListWindow extends JFrame {
    private final JTextField name_TextField = new HintTextField("Nombre");
    private final JTextField lastName_TextField = new HintTextField("Apellido");

    private final JTextField username_TextField = new HintTextField("Usuario");
    private final JTextField password_TextField = new HintTextField("Contraseña");

    private final JComboBox<String> type_Combobox = new JComboBox<>();
    private final JTable userTable = new JTable();

    public UserListWindow(){
        super();
        setBounds(new Rectangle(700, 800));
        setLocationRelativeTo(null);
        setTitle("Ventana principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        updateTableData(UserHandler.getUsers());

        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    new UserEditWindow(UserListWindow.this,
                            userTable).setVisible(true);
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
        username_TextField.setPreferredSize(new Dimension(70, HEIGHT));
        lastName_TextField.setPreferredSize(new Dimension(70, HEIGHT));
        password_TextField.setPreferredSize(new Dimension(70, HEIGHT));

        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        //New User Data Fields
        for (User.Type value : User.Type.values()) {
            type_Combobox.addItem(value.toString());
        }
        interactionPanel.add(username_TextField);
        interactionPanel.add(name_TextField);
        interactionPanel.add(lastName_TextField);
        interactionPanel.add(password_TextField);
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
            if(JOptionPane.showConfirmDialog(UserListWindow.this, "¿Desea eliminar esta fila?",
                    "Eliminar fila", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                //removeUser(userTable.getSelectedRow());
                User[] updatedUsers = UserHandler.removeUser(userTable.getSelectedRow());
                updateTableData(updatedUsers);
            }
        });
    }

    private void addUserFromTable(){
        User user = new User(
                username_TextField.getText(),
                name_TextField.getText(),
                lastName_TextField.getText(),
                User.Type.valueOf(Objects.requireNonNull(type_Combobox.getSelectedItem()).toString()),
                password_TextField.getText()
        );

        //Clean fields
        username_TextField.setText("");
        name_TextField.setText("");
        lastName_TextField.setText("");
        password_TextField.setText("");


        User[] updatedUsers = UserHandler.addUser(user);
        updateTableData(updatedUsers);
    }

    private void updateTableData (User[] users) {
        userTable.setModel(new UserTable(users));
    }

}
