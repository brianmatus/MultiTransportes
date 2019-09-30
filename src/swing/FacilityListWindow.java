package swing;

import location.Entity;
import location.EntityHandler;
import location.Facility;
import model.FacilityTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

class FacilityListWindow extends JFrame {
    private final JTextField code_TextField = new HintTextField("Código");
    private final JTextField name_TextField = new HintTextField("Nombre");

    private final JComboBox<String> type_Combobox = new JComboBox<>();
    private final JTable facilityTable = new JTable();

    private final String belongsToCode;

    public FacilityListWindow(Entity[] facilityArray, String belongsToName, String belongsToCode){
        super();
        setBounds(new Rectangle(700, 800));
        setLocationRelativeTo(null);
        setTitle("Instalaciones-" + belongsToName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.belongsToCode = belongsToCode;

        //Entity[] facilityArray = EntityHandler.getEntitiesFromType("Facility");
        updateTableData(facilityArray);

        facilityTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = facilityTable.getSelectedRow();
                    String code = (String) facilityTable.getModel().getValueAt(index,0);
                    String name = (String) facilityTable.getModel().getValueAt(index,1);
                    System.out.println("Clicked facility code:" + code);

                    Entity[] belongingParkingSpaces = EntityHandler.getEntitiesByBelonging(code);
                    ParkingSpaceListWindow parkingSpaceListWindow = new ParkingSpaceListWindow(belongingParkingSpaces,name,code);
                    parkingSpaceListWindow.setVisible(true);
                }
            }
        });

        JScrollPane jsp = new JScrollPane(facilityTable);

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
        code_TextField.setPreferredSize(new Dimension(70, HEIGHT));

        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        //New Facility Data Fields
        type_Combobox.addItem("Aeropuerto");
        type_Combobox.addItem("Estación");


        interactionPanel.add(name_TextField);
        interactionPanel.add(code_TextField);
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
                addFacilityFromTable();
            }
        });

        deleteButton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(FacilityListWindow.this, "¿Desea eliminar esta fila?",
                    "Eliminar fila", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){

                int index = facilityTable.getSelectedRow();
                String code = (String) facilityTable.getModel().getValueAt(index,0);
                EntityHandler.removeEntity(code);
                updateTableData(EntityHandler.getEntitiesByBelonging(belongsToCode));
            }
        });
    }

    private void addFacilityFromTable(){

        new Facility(
                code_TextField.getText(),
                name_TextField.getText(),
                Objects.requireNonNull(type_Combobox.getSelectedItem()).toString(),
                 belongsToCode
        );

        //Clean fields
        name_TextField.setText("");
        code_TextField.setText("");
        updateTableData(EntityHandler.getEntitiesByBelonging(belongsToCode));
    }

    private void updateTableData (Entity[] facilities) {
        facilityTable.setModel(new FacilityTable(facilities));
    }

}
