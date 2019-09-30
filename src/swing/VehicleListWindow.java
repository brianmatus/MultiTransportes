package swing;

import model.VehicleTable;
import transport.Vehicle;
import transport.VehicleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

class VehicleListWindow extends JFrame {

    private final JComboBox<String> type_ComboBox = new JComboBox<>();
    private final JTable vehicleTable = new JTable();

    public VehicleListWindow(){
        super();
        setBounds(new Rectangle(700, 800));
        setLocationRelativeTo(null);
        setTitle("Ventana principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Vehicle[] vehicleArray = VehicleHandler.getVehiclesArray();
        updateTableData(vehicleArray);

        vehicleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    System.out.println("Vehicle clicked");
                }
            }
        });

        JScrollPane jsp = new JScrollPane(vehicleTable);

        //Main container
        JPanel container1 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(container1, BoxLayout.X_AXIS);
        container1.setLayout(boxlayout);
        container1.add(jsp);

        //Interaction panel
        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.X_AXIS));


        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");



        type_ComboBox.addItem("Bus");
        type_ComboBox.addItem("Avión");
        type_ComboBox.addItem("Carro");
        interactionPanel.add(type_ComboBox);

        JPanel mainPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.X_AXIS));
        mainPanel.add(interactionPanel);
        mainPanel.add(addButton);
        mainPanel.add(deleteButton);
        mainPanel.add(container1);
        add(mainPanel);

        addButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                addVehicleFromTable();
            }
        });

        deleteButton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(VehicleListWindow.this, "¿Desea eliminar esta fila?",
                    "Eliminar fila", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){

                int index = vehicleTable.getSelectedRow();
                String code = (String) vehicleTable.getModel().getValueAt(index,0);
                VehicleHandler.removeVehicle(code);
                updateTableData(VehicleHandler.getVehiclesArray());
            }
        });
    }

    private void addVehicleFromTable() {

        String type = Objects.requireNonNull(type_ComboBox.getSelectedItem()).toString();
        switch (type) {
            case "Carro":
                //CarAddWindow carAddWindow = new CarAddWindow(vehicleTable);
                //carAddWindow.setVisible(true);
                break;
            case "Bus":
                //BusAddWindow busAddWindow = new BusAddWindow(vehicleTable);
                //busAddWindow.setVisible(true);
                break;
            case "Avión":
                //AirplaneAddWindow airplaneAddWindow = new AirplaneAddWindow(vehicleTable);
                //airplaneAddWindow.setVisible(true);
        }
    }

    private void updateTableData (Vehicle[] vehicles) {
        vehicleTable.setModel(new VehicleTable(vehicles));
    }

}