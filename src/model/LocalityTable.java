package model;

import location.Entity;
import location.Locality;

import javax.swing.table.AbstractTableModel;

public class LocalityTable extends AbstractTableModel {
    private final Entity[] localityArray;
    private final String[] columnNames = {"Código", "Nombre"};
    public LocalityTable(Entity[] localityArrayArray) {
        this.localityArray = localityArrayArray;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount(){
        return this.localityArray.length;
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
        Locality locality = (Locality) this.localityArray[row];
        if(locality != null) {
            switch (column) {
                case 0:
                    valor = locality.getCode();
                    break;
                case 1:
                    valor = locality.getName();
                    break;
            }
        }
        return valor;
    }
}
