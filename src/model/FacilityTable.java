package model;

import location.Entity;
import location.Facility;

import javax.swing.table.AbstractTableModel;

public class FacilityTable extends AbstractTableModel {
    private final Entity[] facilityArray;
    private final String[] columnNames = {"Código", "Nombre","Tipo"};
    public FacilityTable(Entity[] facilityArrayArray) {
        this.facilityArray = facilityArrayArray;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount(){
        return this.facilityArray.length;
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
        Facility facility = (Facility) this.facilityArray[row];
        if(facility != null) {
            switch (column) {
                case 0:
                    valor = facility.getCode();
                    break;
                case 1:
                    valor = facility.getName();
                    break;
                case 2:
                    valor = facility.getType();
                    break;
            }
        }
        return valor;
    }
}
