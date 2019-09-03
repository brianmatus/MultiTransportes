package model;

import bean.User;

import javax.swing.table.AbstractTableModel;

public class UserTable extends AbstractTableModel {
    private final User[] userArray;
    private final String[] columnNames = {"No.", "Nombre", "Apellido", "Tipo"};
    public UserTable(User[] userArray) {
        this.userArray = userArray;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount(){
        return this.userArray.length;
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
        User user = this.userArray[row];
        if(user != null) {
            switch (column) {
                case 0:
                    valor = String.valueOf(row + 1);
                    break;
                case 1:
                    valor = user.getName();
                    break;
                case 2:
                    valor = user.getLastName();
                    break;
                case 3:
                    valor = user.getType();
                    break;
            }
        }
        return valor;
    }
}
