package model;

import transport.Vehicle;

import javax.swing.table.AbstractTableModel;

public class VehicleTable extends AbstractTableModel {
    private final Vehicle[] vehicleArray;
    private final String[] columnNames = {"Código", "Nombre","Tipo"};
    public VehicleTable(Vehicle[] vehicleArrayArray) {
        this.vehicleArray = vehicleArrayArray;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount(){
        return this.vehicleArray.length;
    }

    @Override
    public int getColumnCount(){
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex){
        return this.columnNames[columnIndex];
    }

    public Object getValueAt(int row, int column){
        String valor = "";
        Vehicle vehicle = this.vehicleArray[row];
        if(vehicle != null) {
            switch (column) {
                case 0:
                    valor = vehicle.getCode();
                    break;
                case 1:
                    valor = vehicle.getName();
                    break;
                case 2:
                    valor = vehicle.getVehicleType();
                    break;
            }
        }
        return valor;
    }
}
