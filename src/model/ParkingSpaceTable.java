package model;

import location.Entity;
import location.ParkingSpace;

import javax.swing.table.AbstractTableModel;

public class ParkingSpaceTable extends AbstractTableModel {
    private final Entity[] parkingSpaceArray;
    private final String[] columnNames = {"Código","Nombre","Tipo", "Filas", "Columnas"};
    public ParkingSpaceTable(Entity[] parkingSpaceArray) {
        this.parkingSpaceArray = parkingSpaceArray;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount(){
        return this.parkingSpaceArray.length;
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
        ParkingSpace parkingSpace = (ParkingSpace) this.parkingSpaceArray[row];
        if(parkingSpace != null) {
            switch (column) {
                case 0:
                    valor = parkingSpace.getCode();
                    break;
                case 1:
                    valor = parkingSpace.getName();
                    break;
                case 2:
                    valor = parkingSpace.getType();
                    break;
                case 3:
                    valor = "" + parkingSpace.spaces.length;
                    break;
                case 4:
                    valor = "" + parkingSpace.spaces[0].length;
                    break;
            }
        }
        return valor;
    }
}
