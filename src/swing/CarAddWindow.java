package swing;

import location.ParkingSpace;
import transport.Car;
import transport.VehicleHandler;

import java.awt.*;
import javax.swing.*;

class CarAddWindow extends JFrame {
    private final JTextField codeTextBox;
    private final JTextField tankCapacityTextBox;
    private final JTextField maxVelocityTextBox;
    private final JTextField brandTextBox;
    private final JTextField costPerDayTextBox;
    private final JTextField nameTextField;
    private final JTextField transmissionTextField;

    public CarAddWindow(JTable modelTable, ParkingSpace theParkingSpace) {
        //construct components
        JButton addButton = new JButton("Agregar");
        JLabel jcomp2 = new JLabel("Código");
        codeTextBox = new JTextField (5);
        JLabel jcomp6 = new JLabel("Capacidad Tanque");
        tankCapacityTextBox = new JTextField (5);
        JLabel jcomp8 = new JLabel("Velocidad Máxima");
        maxVelocityTextBox = new JTextField (5);
        JLabel jcomp10 = new JLabel("Marca");
        brandTextBox = new JTextField (5);
        JLabel costP = new JLabel("Costo por día");
        costPerDayTextBox = new JTextField (5);
        JLabel jcomp14 = new JLabel("Agregar Carro");
        nameTextField = new JTextField (5);
        JLabel jcomp15 = new JLabel("Transmisión");
        transmissionTextField = new JTextField (5);
        JLabel jcomp16 = new JLabel("Nombre");

        //adjust size and set layout
        setBounds(new Rectangle(278, 372));
        setLayout (null);

        //add components
        add (addButton);
        add (jcomp2);
        add (codeTextBox);
        add (jcomp6);
        add (tankCapacityTextBox);
        add (jcomp8);
        add (maxVelocityTextBox);
        add (jcomp10);
        add (brandTextBox);
        add (costP);
        add (costPerDayTextBox);
        add (jcomp14);
        add (jcomp15);
        add (transmissionTextField);
        add(jcomp16);
        add(nameTextField);

        //set component bounds (only needed by Absolute Positioning)
        addButton.setBounds (160, 145, 100, 20);
        jcomp2.setBounds (10, 20, 100, 25);
        codeTextBox.setBounds (10, 40, 100, 25);
        jcomp6.setBounds (10, 120, 120, 25);
        tankCapacityTextBox.setBounds (10, 140, 100, 25);
        jcomp8.setBounds (10, 170, 100, 25);
        maxVelocityTextBox.setBounds (10, 190, 100, 25);
        jcomp10.setBounds (10, 220, 100, 25);
        brandTextBox.setBounds (10, 240, 100, 25);
        costP.setBounds (10, 270, 100, 25);
        costPerDayTextBox.setBounds (10, 290, 100, 25);
        jcomp14.setBounds (30, 0, 100, 25);
        nameTextField.setBounds (145, 35, 100, 25);
        jcomp15.setBounds (150, 70, 100, 25);
        transmissionTextField.setBounds (150, 90, 100, 25);
        jcomp16.setBounds (145, 10, 100, 25);

        addButton.addActionListener(e-> {
            String code = codeTextBox.getText();
            if (code.trim().length()==0) {
                JOptionPane.showMessageDialog(CarAddWindow.this, "Código inválido");
                return;
            }

            String name = nameTextField.getText();
            if (code.trim().length()==0) {
                JOptionPane.showMessageDialog(CarAddWindow.this, "Nombre inválido");
                return;
            }

            int tankCapacity = Integer.parseInt(tankCapacityTextBox.getText());
            if (tankCapacity<=0) {
                JOptionPane.showMessageDialog(CarAddWindow.this, "Capacidad Tanque inválida");
                return;
            }


            int maxVelocity = Integer.parseInt(maxVelocityTextBox.getText());
            if (maxVelocity<=0) {
                JOptionPane.showMessageDialog(CarAddWindow.this, "Velocidad inválida");
                return;
            }


            String brand = brandTextBox.getText();
            if (brand.trim().length()==0) {
                JOptionPane.showMessageDialog(CarAddWindow.this, "Marca inválida");
                return;
            }

            int costPerDay = Integer.parseInt(costPerDayTextBox.getText());
            if (costPerDay<=0) {
                JOptionPane.showMessageDialog(CarAddWindow.this, "Costo inválido");
                return;
            }


            String transmission = transmissionTextField.getText();
            if (brand.trim().length()==0) {
                JOptionPane.showMessageDialog(CarAddWindow.this, "Transmisión inválida");
                return;
            }

            int i = modelTable.getSelectedRow();
            int j = modelTable.getSelectedColumn();
            String theSlotCode = theParkingSpace.getCode() + "-" + (i+1) + "_" + (j+1);
            System.out.println("Esta si gg " +theSlotCode );

            //If everything went right:
            VehicleHandler.addVehicle(
                    new Car(code,name,tankCapacity,brand,costPerDay,transmission, maxVelocity,theSlotCode)
            );
            modelTable.repaint();
            dispose();


        });
        
    }

}