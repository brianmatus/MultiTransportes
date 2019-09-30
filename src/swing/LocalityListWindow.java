package swing;

import location.Entity;
import location.EntityHandler;
import model.LocalityTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LocalityListWindow extends JFrame {

    private final JTable localityTable = new JTable();

    public LocalityListWindow(Entity[] localityArray, String belongsToName){
        super();
        setBounds(new Rectangle(500, 500));
        setLocationRelativeTo(null);
        setTitle("Localidades-" + belongsToName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Entity[] localityArray = EntityHandler.getEntitiesFromType("Locality");
        updateTableData(localityArray);

        localityTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = localityTable.getSelectedRow();
                    String code = (String) localityTable.getModel().getValueAt(index,0);
                    String name = (String) localityTable.getModel().getValueAt(index,1);
                    System.out.println("Clicked locality code:" + code);

                    Entity[] belongingFacilities = EntityHandler.getEntitiesByBelonging(code);

                    FacilityListWindow facilityListWindow = new FacilityListWindow(belongingFacilities,name,code);
                    facilityListWindow.setVisible(true);
                }
            }
        });

        JScrollPane jsp = new JScrollPane(localityTable);

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
        localityTable.setModel(new LocalityTable(facilities));
    }

}
