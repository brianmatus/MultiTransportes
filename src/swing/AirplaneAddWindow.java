package swing;

import location.ParkingSpace;
import transport.Airplane;
import transport.VehicleHandler;

import java.awt.*;
import javax.swing.*;

class AirplaneAddWindow extends JFrame {
    private final JTextField codeTextBox;
    private final JTextField passengerCapacityTextBox;
    private final JTextField tankCapacityTextBox;
    private final JTextField maxHeightTextBox;
    private final JTextField brandTextBox;
    private final JTextField costPerDayTextBox;
    private final JTextField nameTextField;

    public AirplaneAddWindow(JTable modelTable, ParkingSpace theParkingSpace) {


        //construct components
        JButton addButton = new JButton("Agregar");
        JLabel jcomp2 = new JLabel("Código");
        codeTextBox = new JTextField (5);
        JLabel jcomp4 = new JLabel("Capacidad Pasajeros");
        passengerCapacityTextBox = new JTextField (5);
        JLabel jcomp6 = new JLabel("Capacidad Tanque");
        tankCapacityTextBox = new JTextField (5);
        JLabel jcomp8 = new JLabel("Altura Máxima");
        maxHeightTextBox = new JTextField (5);
        JLabel jcomp10 = new JLabel("Marca");
        brandTextBox = new JTextField (5);
        JLabel costP = new JLabel("Costo por día");
        costPerDayTextBox = new JTextField (5);
        JLabel jcomp14 = new JLabel("Agregar Avión");
        nameTextField = new JTextField (5);
        JLabel jcomp16 = new JLabel("Nombre");

        //adjust size and set layout
        setBounds(new Rectangle(278, 372));
        setLayout (null);

        //add components
        add (addButton);
        add (jcomp2);
        add (codeTextBox);
        add (jcomp4);
        add (passengerCapacityTextBox);
        add (jcomp6);
        add (tankCapacityTextBox);
        add (jcomp8);
        add (maxHeightTextBox);
        add (jcomp10);
        add (brandTextBox);
        add (costP);
        add (costPerDayTextBox);
        add (jcomp14);
        add(jcomp16);
        add(nameTextField);

        //set component bounds (only needed by Absolute Positioning)
        addButton.setBounds (160, 145, 100, 20);
        jcomp2.setBounds (10, 20, 100, 25);
        codeTextBox.setBounds (10, 40, 100, 25);
        jcomp4.setBounds (10, 70, 145, 25);
        passengerCapacityTextBox.setBounds (10, 90, 100, 25);
        jcomp6.setBounds (10, 120, 120, 25);
        tankCapacityTextBox.setBounds (10, 140, 100, 25);
        jcomp8.setBounds (10, 170, 100, 25);
        maxHeightTextBox.setBounds (10, 190, 100, 25);
        jcomp10.setBounds (10, 220, 100, 25);
        brandTextBox.setBounds (10, 240, 100, 25);
        costP.setBounds (10, 270, 100, 25);
        costPerDayTextBox.setBounds (10, 290, 100, 25);
        jcomp14.setBounds (30, 0, 100, 25);
        nameTextField.setBounds (145, 35, 100, 25);
        jcomp16.setBounds (145, 10, 100, 25);


        addButton.addActionListener(e-> {
            String code = codeTextBox.getText();
            System.out.println("code = " + code);
            if (code.trim().length()==0) {
                JOptionPane.showMessageDialog(AirplaneAddWindow.this, "Código inválido");
                return;
            }

            String name = nameTextField.getText();
            if (code.trim().length()==0) {
                JOptionPane.showMessageDialog(AirplaneAddWindow.this, "Nombre inválido");
                return;
            }

            int passengerCapacity = Integer.parseInt(passengerCapacityTextBox.getText());
            if (passengerCapacity<=0) {
                JOptionPane.showMessageDialog(AirplaneAddWindow.this, "Capacidad Pasajeros inválida");
                return;
            }


            int tankCapacity = Integer.parseInt(tankCapacityTextBox.getText());
            if (tankCapacity<=0) {
                JOptionPane.showMessageDialog(AirplaneAddWindow.this, "Capacidad Tanque inválida");
                return;
            }


            int maxHeight = Integer.parseInt(maxHeightTextBox.getText());
            if (maxHeight<=0) {
                JOptionPane.showMessageDialog(AirplaneAddWindow.this, "Altura inválida");
                return;
            }


            String brand = brandTextBox.getText();
            if (brand.trim().length()==0) {
                JOptionPane.showMessageDialog(AirplaneAddWindow.this, "Marca inválida");
                return;
            }

            int costPerDay = Integer.parseInt(costPerDayTextBox.getText());
            if (costPerDay<=0) {
                JOptionPane.showMessageDialog(AirplaneAddWindow.this, "Costo inválido");
                return;
            }

            int i = modelTable.getSelectedRow();
            int j = modelTable.getSelectedColumn();
            String theSlotCode = theParkingSpace.getCode() + "-" + (i+1) + "_" + (j+1);

            //If everything went right:
            VehicleHandler.addVehicle(
                    new Airplane(code,name,tankCapacity,passengerCapacity,brand,costPerDay,maxHeight,theSlotCode)
            );
            modelTable.repaint();
            dispose();


        });
    }

}