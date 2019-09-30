package swing;

import location.*;
import model.ParkingSpaceTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

class ParkingSpaceListWindow extends JFrame {
    private final JTextField code_TextField = new HintTextField("Código");
    private final JTextField name_TextField = new HintTextField("Nombre");
    private final JTextField rows_TextField = new HintTextField("Filas");
    private final JTextField columns_TextField = new HintTextField("Columnas");

    private final JTable parkingSpaceTable = new JTable();

    private final String belongsToCode;


    ParkingSpaceListWindow(Entity[] parkingSpaceArray, String belongsToName, String belongsToCode){
        super();
        setBounds(new Rectangle(700, 800));
        setLocationRelativeTo(null);
        Facility theFacility = (Facility) EntityHandler.getEntityByCode(belongsToCode);
        String theType = Objects.requireNonNull(theFacility).getType();
        if (theType.equals("Aeropuerto")) {
            setTitle("Hangares-" + belongsToName);
        }
        else {
            setTitle("Parqueos-" + belongsToName);

        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.belongsToCode = belongsToCode;
        updateTableData(parkingSpaceArray);

        parkingSpaceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = parkingSpaceTable.getSelectedRow();
                    String code = (String) parkingSpaceTable.getModel().getValueAt(index,0);
                    System.out.println("Clicked parking space code:" + code);

                    ParkingSpace theParkingSpace = (ParkingSpace) EntityHandler.getEntityByCode(code);
                    SlotListWindow slotListWindow = new SlotListWindow(Objects.requireNonNull(theParkingSpace));
                    slotListWindow.setVisible(true);

                }
            }
        });

        JScrollPane jsp = new JScrollPane(parkingSpaceTable);

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
        rows_TextField.setPreferredSize(new Dimension(70, HEIGHT));
        columns_TextField.setPreferredSize(new Dimension(70, HEIGHT));


        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        JComboBox<String> belongsTo_Combobox = new JComboBox<>();
        belongsTo_Combobox.setEnabled(false);


        interactionPanel.add(name_TextField);
        interactionPanel.add(code_TextField);
        interactionPanel.add(rows_TextField);
        interactionPanel.add(columns_TextField);
        interactionPanel.add(belongsTo_Combobox);

        JPanel mainPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.X_AXIS));
        mainPanel.add(interactionPanel);
        mainPanel.add(addButton);
        mainPanel.add(deleteButton);
        mainPanel.add(container1);
        add(mainPanel);

        addButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                addParkingSpaceFromTable();
            }
        });

        deleteButton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(ParkingSpaceListWindow.this, "¿Desea eliminar esta fila?",
                    "Eliminar fila", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){

                int index = parkingSpaceTable.getSelectedRow();
                String code = (String) parkingSpaceTable.getModel().getValueAt(index,0);
                EntityHandler.removeEntity(code);
                updateTableData(EntityHandler.getEntitiesByBelonging(belongsToCode));
                parkingSpaceTable.repaint();
            }
        });
    }

    private void addParkingSpaceFromTable(){

        Facility facility = (Facility) EntityHandler.getEntityByCode(belongsToCode);


        assert facility != null;
        new ParkingSpace(
                code_TextField.getText(),
                name_TextField.getText(),
                facility.getType().equals("Aeropuerto") ? "Hangar" : "Parqueo"
                ,
                Integer.parseInt(rows_TextField.getText()),
                Integer.parseInt(columns_TextField.getText()),
                belongsToCode
        );

        //Clean fields
        name_TextField.setText("");
        code_TextField.setText("");
        rows_TextField.setText("");
        columns_TextField.setText("");
        updateTableData(EntityHandler.getEntitiesByBelonging(belongsToCode));
    }

    private void updateTableData (Entity[] parkingSpaces) {
        parkingSpaceTable.setModel(new ParkingSpaceTable(parkingSpaces));
    }

}
