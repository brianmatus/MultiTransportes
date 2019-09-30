package model;

import location.Entity;
import location.Slot;

import javax.swing.table.AbstractTableModel;

public class SlotTable extends AbstractTableModel {
    private final Entity[][] slotArray;
    private final String[] columnNames;
    public SlotTable(Entity[][] slotArray) {
        columnNames = new String[slotArray.length];
        java.util.Arrays.fill(columnNames,"");
        this.slotArray = slotArray;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount(){
        return this.slotArray.length;
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
        Slot theSlot = (Slot) slotArray[row][column];
        try {
            return theSlot.getStoredVehicle().getName();
        }
        catch (NullPointerException error) {
            return "Empty";
        }

    }
}
