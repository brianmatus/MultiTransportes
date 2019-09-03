package swing;

import bean.User;
import model.UserTable;
import util.ArrayPosition;

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

class NewWindow extends JDialog{
    private final JTextField nameField;
    private final JTextField lastNameField;
    private final JComboBox<String> typeList;
    private final User[] usersArray;
    private final JTable table;
    NewWindow(JFrame parent, boolean modal, User[] usersArray, JTable table){
        super(parent, modal);

        this.usersArray = usersArray;
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
        String[] tipos = {"Administrador", "Usuario"};
        typeList = new JComboBox<>(tipos);

        //Load clicked user information
        User usuarioSeleccionado = usersArray[table.getSelectedRow()];
        nameField.setText(usuarioSeleccionado.getName());
        lastNameField.setText(usuarioSeleccionado.getLastName());
        typeList.setSelectedItem(usuarioSeleccionado.getType());

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            usersArray[table.getSelectedRow()]
                    = new User(nameField.getText(), lastNameField.getText(), Objects.requireNonNull(typeList.getSelectedItem()).toString());
            updateTableData();
            JOptionPane.showMessageDialog(NewWindow.this, "Se actualizaron los datos.");
            dispose();
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

    private void updateTableData () {
        int index = ArrayPosition.getNotNullIndex(usersArray);
        User[] nonEmptyArray = new User[index];
        System.arraycopy(usersArray,0,nonEmptyArray,0,index);
        table.setModel(new UserTable(nonEmptyArray));
    }
}
