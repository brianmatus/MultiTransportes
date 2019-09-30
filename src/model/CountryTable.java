package model;

import location.Entity;
import location.Country;

import javax.swing.table.AbstractTableModel;

public class CountryTable extends AbstractTableModel {
    private final Entity[] countryArray;
    private final String[] columnNames = {"Código", "Nombre"};
    public CountryTable(Entity[] countryArrayArray) {
        this.countryArray = countryArrayArray;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount(){
        return this.countryArray.length;
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
        Country country = (Country) this.countryArray[row];
        if(country != null) {
            switch (column) {
                case 0:
                    valor = country.getCode();
                    break;
                case 1:
                    valor = country.getName();
                    break;
            }
        }
        return valor;
    }
}
