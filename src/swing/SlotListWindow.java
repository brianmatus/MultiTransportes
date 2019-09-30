package swing;

import location.*;
import model.SlotTable;
import transport.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

class SlotListWindow extends JFrame {

    private final JTable slotTable = new JTable();
    private final Slot[][] slotArray;
    private boolean isMovingCar = false;
    private Slot previousVehicleSlot;
    private Vehicle vehicleToBeMoved;

    SlotListWindow(ParkingSpace theParkingSpace){
        super();
        setBounds(new Rectangle(700, 800));
        setLocationRelativeTo(null);

        setTitle("Vehículos-" + theParkingSpace.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.slotArray = theParkingSpace.spaces;
        updateTableData(slotArray);





        JScrollPane jsp = new JScrollPane(slotTable);
        //Main container
        JPanel container1 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(container1, BoxLayout.X_AXIS);
        container1.setLayout(boxlayout);
        container1.add(jsp);

        //Interaction panel
        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.X_AXIS));

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(e-> {
            System.out.println("Add button clicked");
            addVehicle();
        });

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(e -> {

            int i = slotTable.getSelectedRow();
            int j = slotTable.getSelectedColumn();
            String code;
            try {
                code = slotArray[i][j].getStoredVehicle().getCode();
                System.out.println("Vehicle to delete code: " + code);
                if(JOptionPane.showConfirmDialog(SlotListWindow.this, "¿Desea eliminar este vehículo?",
                        "Eliminar vehículo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){

                    VehicleHandler.removeVehicle(code);
                }

            }
            catch (NullPointerException error) {
                JOptionPane.showMessageDialog(SlotListWindow.this, "No hay un vehículo en este espacio");
            }


        });

        JButton moveButton = new JButton("Mover");
        moveButton.addActionListener(e -> {
            int i = slotTable.getSelectedRow();
            int j = slotTable.getSelectedColumn();

            if (i==-1 || j==-1) {
                JOptionPane.showMessageDialog(SlotListWindow.this, "Debe seleccionar un espacio");
                return;

            }

            String code;
            try {
                code = slotArray[i][j].getStoredVehicle().getCode();
                System.out.println("Clicked vehicle code: " + code);
                isMovingCar = true;
                vehicleToBeMoved = VehicleHandler.getVehicleByCode(code);
                previousVehicleSlot = slotArray[i][j];
                JOptionPane.showMessageDialog(SlotListWindow.this, "Haga doble click a la casilla destino");


            }
            catch (NullPointerException error) {
                System.out.println("Clicked empty space");
                JOptionPane.showMessageDialog(SlotListWindow.this, "No hay un vehículo en este espacio");
            }
        });


        JPanel mainPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.X_AXIS));
        mainPanel.add(interactionPanel);
        mainPanel.add(addButton);
        mainPanel.add(deleteButton);
        mainPanel.add(moveButton);
        mainPanel.add(container1);
        add(mainPanel);



        slotTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int i = slotTable.getSelectedRow();
                    int j = slotTable.getSelectedColumn();
                    if (isMovingCar) {
                        moveVehicle(i,j);
                    }
                    else {
                        showVehicleInfo(i,j);
                    }
                }
            }
        });

    }


    private void showVehicleInfo(int i, int j) {
        String code;
        try {
            code = slotArray[i][j].getStoredVehicle().getCode();
            Vehicle theVehicle = VehicleHandler.getVehicleByCode(code);
            switch (Objects.requireNonNull(theVehicle).getVehicleType()) {
                case "Car":
                    Car theCar = (Car) theVehicle;
                    JOptionPane.showMessageDialog(SlotListWindow.this,
                            "Código: " + theCar.getCode() + "\n" +
                            "Nombre:" + theCar.getName() + "\n" +
                            "Capacidad de Tanque(gal):" + theCar.getGasCapacity() + "\n" +
                            "Marca:" + theCar.getBrand() + "\n" +
                            "Transmisión:" + theCar.getTransmission() + "\n" +
                            "Vel. Máxima:" + theCar.getMaxVelocity() + "\n" +
                            "Costo por día:" + theCar.getCostPerDay() + "\n");

                    System.out.println("Velocidad máxima:" + theCar.getMaxVelocity());
                    break;
                case "Bus":
                    Bus theBus = (Bus) theVehicle;
                    JOptionPane.showMessageDialog(SlotListWindow.this,
                            "Código: " + theBus.getCode() + "\n" +
                                    "Nombre:" + theBus.getName() + "\n" +
                                    "Capacidad de Tanque(gal):" + theBus.getGasCapacity() + "\n" +
                                    "Cantidad de Pasajeros:" + theBus.getPassengerCapacity() + "\n" +
                                    "Marca:" + theBus.getBrand() + "\n" +
                                    "Vel. Máxima:" + theBus.getMaxVelocity() + "\n" +
                                    "Costo por día:" + theBus.getCostPerDay() + "\n");
                    break;
                case "Airplane":
                    Airplane theAirplane = (Airplane) theVehicle;
                    JOptionPane.showMessageDialog(SlotListWindow.this,
                            "Código: " + theAirplane.getCode() + "\n" +
                                    "Nombre:" + theAirplane.getName() + "\n" +
                                    "Capacidad de Tanque(gal):" + theAirplane.getGasCapacity() + "\n" +
                                    "Cantidad de Pasajeros:" + theAirplane.getPassengerCapacity() + "\n" +
                                    "Marca:" + theAirplane.getBrand() + "\n" +
                                    "Vel. Máxima:" + theAirplane.getMaxHeight() + "\n" +
                                    "Costo por día:" + theAirplane.getCostPerDay() + "\n");
                    break;
            }
        }
        catch (NullPointerException error) {
            //
        }
    }

    private void moveVehicle (int i, int j) {
        String code;
        try {
            //noinspection UnusedAssignment
            code = slotArray[i][j].getStoredVehicle().getCode();
            JOptionPane.showMessageDialog(SlotListWindow.this, "Ya hay un vehículo en este espacio, intente otra casilla");
        }
        catch (NullPointerException error) {
            Slot theNewSlot = slotArray[i][j];
            theNewSlot.setStoredVehicle(vehicleToBeMoved.getCode());
            vehicleToBeMoved.setParkedAt(theNewSlot.getCode());
            previousVehicleSlot.setStoredVehicle(null);
            isMovingCar = false;
            vehicleToBeMoved = null;
            previousVehicleSlot = null;
            slotTable.repaint();
        }
    }

    private void addVehicle() {

        int i = slotTable.getSelectedRow();
        int j = slotTable.getSelectedColumn();

        if (i==-1 || j==-1) {
            JOptionPane.showMessageDialog(SlotListWindow.this, "Debe seleccionar un espacio primero");
            return;
        }


        if (slotArray[i][j].getStoredVehicle() != null) {
            JOptionPane.showMessageDialog(SlotListWindow.this, "Ya hay un vehículo en este espacio");
            return;

        }
        ParkingSpace theParkingSpace = (ParkingSpace) EntityHandler.getEntityByCode(slotArray[0][0].getBelongsTo());

        if (Objects.requireNonNull(theParkingSpace).getType().equals("Parqueo")) {
            String[] options = {"Carro", "Bus"};
            int result = JOptionPane.showOptionDialog(
                    this,
                    "Qué vehículo desea crear?",
                    "Creación Vehículo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //no custom icon
                    options,  //button titles
                    options[0] //default button
            );
            if(result == JOptionPane.YES_OPTION){
                CarAddWindow carAddWindow = new CarAddWindow(slotTable,theParkingSpace);
                carAddWindow.setVisible(true);
            }else if (result == JOptionPane.NO_OPTION){
                BusAddWindow busAddWindow = new BusAddWindow(slotTable,theParkingSpace);
                busAddWindow.setVisible(true);
            }else {
                System.out.println("None selected");
            }
        }
        else {
            AirplaneAddWindow airplaneAddWindow = new AirplaneAddWindow(slotTable,theParkingSpace);
            airplaneAddWindow.setVisible(true);
        }
    }

    private void updateTableData (Slot[][] slots) {
        slotTable.setModel(new SlotTable(slots));
    }

}
