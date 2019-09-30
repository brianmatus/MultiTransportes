package swing;

import location.Entity;
import location.EntityHandler;
import model.CountryTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CountryListWindow extends JFrame {

    private final JTable countryTable = new JTable();

    public CountryListWindow(){
        super();
        setBounds(new Rectangle(500, 500));
        setLocationRelativeTo(null);
        setTitle("Países");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Entity[] countryArray = EntityHandler.getEntitiesFromType("Country");
        updateTableData(countryArray);

        countryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    //Load clicked country information
                    int index = countryTable.getSelectedRow();
                    String code = (String) countryTable.getModel().getValueAt(index,0);
                    String name = (String) countryTable.getModel().getValueAt(index,1);
                    System.out.println("Clicked country code:" + code);


                    Entity[] belongingLocalities = EntityHandler.getEntitiesByBelonging(code);
                    LocalityListWindow localityListWindow = new LocalityListWindow(belongingLocalities,name);
                    localityListWindow.setVisible(true);
                }
            }
        });

        JScrollPane jsp = new JScrollPane(countryTable);

        //Main container
        JPanel container1 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(container1, BoxLayout.X_AXIS);
        container1.setLayout(boxlayout);
        container1.add(jsp);

        JPanel mainPanel = new JPanel();
        mainPanel.add(container1);
        add(mainPanel);

    }

    private void updateTableData (Entity[] facilities) {
        countryTable.setModel(new CountryTable(facilities));
    }

}
