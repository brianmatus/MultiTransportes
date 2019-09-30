package swing;

import user.UserIlegalTypeChangeException;
import user.User;
import user.UserHandler;
import model.UserTable;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

class UserEditWindow extends JDialog{
    private final JTextField nameField;
    private final JTextField lastNameField;
    private final JComboBox<String> typeList;
    private final JTable table;
    UserEditWindow(JFrame parent, JTable table){
        super(parent, true);

        this.table = table;

        setBounds(new Rectangle(500,150));
        setLocationRelativeTo(null);
        setTitle("Editar Usuario");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel nameLabel = new JLabel("Nombre");
        nameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Apellido");
        lastNameField = new JTextField();

        JLabel typeLabel = new JLabel("Tipo");

        user.User.Type[] types = User.Type.values();
        String[] tipos = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            tipos[i] = types[i].toString();
        }
        typeList = new JComboBox<>(tipos);

        //Load clicked user information
        User usuarioSeleccionado = UserHandler.getUsers()[table.getSelectedRow()];
        nameField.setText(usuarioSeleccionado.getName());
        lastNameField.setText(usuarioSeleccionado.getLastName());
        typeList.setSelectedItem(usuarioSeleccionado.getType());

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            User modifiedUser = UserHandler.getUsers()[table.getSelectedRow()];
            String name = nameField.getText();
            String lastName = lastNameField.getText();

            if (name.trim().length()==0) {
                JOptionPane.showMessageDialog(UserEditWindow.this, "Nombre inválido");
                return;
            }
            if (lastName.trim().length()==0) {
                JOptionPane.showMessageDialog(UserEditWindow.this, "Apellido inválido");
                return;
            }



            try {
                modifiedUser.setType(Objects.requireNonNull(typeList.getSelectedItem()).toString().toUpperCase());
                modifiedUser.setName(name);
                modifiedUser.setLastName(lastName);

                updateTableData(UserHandler.getUsers());
                JOptionPane.showMessageDialog(UserEditWindow.this, "Se actualizaron los datos.");
                dispose();
            } catch (UserIlegalTypeChangeException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(UserEditWindow.this, "No se puede convertir un usuario a administrador");
            }

        });
        
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());

        //container1
        JPanel container1 = new JPanel();
        container1.setBackground(Color.white);
        container1.setSize(500, 50);
        BoxLayout boxLayout1 = new BoxLayout(container1, BoxLayout.X_AXIS);
        container1.setLayout(boxLayout1);
        container1.add(nameLabel);
        container1.add(nameField);

        //container2
        JPanel container2 = new JPanel();
        container2.setBackground(Color.white);
        container2.setSize(500, 50);
        BoxLayout boxLayout2 = new BoxLayout(container2, BoxLayout.X_AXIS);
        container2.setLayout(boxLayout2);
        container2.add(lastNameLabel);
        container2.add(lastNameField);

        //container3
        JPanel container3 = new JPanel();
        container3.setBackground(Color.white);
        container3.setSize(500, 50);
        BoxLayout boxLayout3 = new BoxLayout(container3, BoxLayout.X_AXIS);
        container3.setLayout(boxLayout3);
        container3.add(typeLabel);
        container3.add(typeList);

        //container4
        JPanel container4 = new JPanel();
        container4.setBackground(Color.white);
        container4.setSize(500, 50);
        BoxLayout boxLayout4 = new BoxLayout(container4, BoxLayout.X_AXIS);
        container4.setLayout(boxLayout4);
        container4.add(saveButton);
        container4.add(cancelButton);

        //container5
        JPanel container5 = new JPanel();
        container5.setBackground(Color.white);
        container5.setSize(500, 50);
        BoxLayout boxLayout5 = new BoxLayout(container5, BoxLayout.Y_AXIS);
        container5.setLayout(boxLayout5);
        container5.add(container1);
        container5.add(container2);
        container5.add(container3);
        container5.add(container4);

        add(container5);
    }

    private void updateTableData (User[] users) {
        table.setModel(new UserTable(users));
    }
}
