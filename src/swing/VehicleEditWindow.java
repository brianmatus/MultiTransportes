package swing;

import location.Entity;
import location.EntityHandler;
import location.Facility;
import model.FacilityTable;

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

class VehicleEditWindow extends JDialog{
    private final JTextField codeField;
    private final JTextField nameField;
    private final JComboBox<String> typeList;

    private final JTable table;
    VehicleEditWindow(JFrame parent, JTable table){
        super(parent, true);

        this.table = table;

        setBounds(new Rectangle(500,150));
        setLocationRelativeTo(null);
        setTitle("Editar Instalación");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel nameLabel = new JLabel("Código");
        codeField = new JTextField();

        JLabel lastNameLabel = new JLabel("Nombre");
        nameField = new JTextField();

        JLabel typeLabel = new JLabel("Tipo");

        typeList = new JComboBox<>(new String[]{"Aeropuerto","Estación"});

        JLabel belongsToLabel = new JLabel("Pertenece a");

        Entity[] localities = EntityHandler.getEntitiesFromType("Locality");
        String[] belongings = new String[localities.length];
        for (int i = 0; i < localities.length; i++) {
            belongings[i] = localities[i].getName();
        }
        JComboBox<String> belongsToList = new JComboBox<>(belongings);

        //Load clicked facility information
        int index = table.getSelectedRow();
        String code = (String) table.getModel().getValueAt(index,0);

        Facility selectedFacility = (Facility) EntityHandler.getEntityByCode(code);
        assert selectedFacility != null;
        codeField.setText(selectedFacility.getCode());
        nameField.setText(selectedFacility.getName());
        typeList.setSelectedItem(selectedFacility.getType());
        belongsToList.setSelectedItem(selectedFacility.getBelongsTo());

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            Facility modifiedFacility = (Facility) EntityHandler.getEntityByCode(code);
            String code_ = codeField.getText();
            String name = nameField.getText();

            if (code_.trim().length()==0) {
                JOptionPane.showMessageDialog(VehicleEditWindow.this, "Código inválido");
                return;
            }
            if (name.trim().length()==0) {
                JOptionPane.showMessageDialog(VehicleEditWindow.this, "Nombre inválido");
                return;
            }


            assert modifiedFacility != null;
            modifiedFacility.setType(Objects.requireNonNull(typeList.getSelectedItem()).toString().toUpperCase());
            modifiedFacility.setCode(code_);
            modifiedFacility.setName(name);
            modifiedFacility.setBelongsTo(Objects.requireNonNull(belongsToList.getSelectedItem()).toString().toUpperCase());

            updateTableData(EntityHandler.getEntitiesFromType("Facility"));
            JOptionPane.showMessageDialog(VehicleEditWindow.this, "Se actualizaron los datos.");
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
        container1.add(codeField);

        //container2
        JPanel container2 = new JPanel();
        container2.setBackground(Color.white);
        container2.setSize(500, 50);
        BoxLayout boxLayout2 = new BoxLayout(container2, BoxLayout.X_AXIS);
        container2.setLayout(boxLayout2);
        container2.add(lastNameLabel);
        container2.add(nameField);

        //container3
        JPanel container3 = new JPanel();
        container3.setBackground(Color.white);
        container3.setSize(500, 50);
        BoxLayout boxLayout3 = new BoxLayout(container3, BoxLayout.X_AXIS);
        container3.setLayout(boxLayout3);
        container3.add(typeLabel);
        container3.add(typeList);
        container3.add(belongsToLabel);
        container3.add(belongsToList);

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

    private void updateTableData (Entity[] facilities) {
        table.setModel(new FacilityTable(facilities));
    }
}
