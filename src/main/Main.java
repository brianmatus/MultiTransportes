package main;

import location.*;
import swing.*;
import user.User;
import user.UserHandler;

class Main {
    public static void main(String[] args){


       new Country("count-guat","Guatemala","");
       new Locality("loc-1","Localidad Primera","count-guat");
       new Facility("fac-1","Aeropuerto Primero","Aeropuerto","loc-1");
       new Facility("fac-2","Estación Primera","Estación","loc-1");

        ParkingSpace hang1 = new ParkingSpace("hang-1","Hangar1","Hangar",5,5,"fac-1");
        ParkingSpace parq1 = new ParkingSpace("parq-1","Parqueo1","Parqueo",2,10,"fac-2");
        hang1.spaces[0][0].setStoredVehicle("air-1");
        parq1.spaces[0][0].setStoredVehicle("veh-1");


        User u2 = new User("hector_r","Hector", "Rodriguez", User.Type.USER,"123");
        UserHandler.addUser(u2);

        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);


    }

}
