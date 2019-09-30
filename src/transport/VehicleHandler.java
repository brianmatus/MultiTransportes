package transport;

import location.EntityHandler;
import location.Slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehicleHandler {

    //private static Vehicle[] vehiclesArray = new Vehicle[1];
    private static Vehicle[] vehiclesArray = {
            new Car("veh-1","Bugatti",300,"Buggati",200,"Mecánico", 200,"parq-1-1_1"),
            new Airplane("air-1","Jet Privado",200,15,"GG ez Marca",200,1600,"hang-1-1_1")
    };


    public static void addVehicle (Vehicle newVehicle) {

        for (Vehicle vehicle : vehiclesArray) {
            if (vehicle.code.equals(newVehicle.code)) {
                System.out.println("Code already belongs to another vehicle");
                System.out.println(vehicle.code + "," + vehicle.name);
                return;
            }
        }

        int length = vehiclesArray.length;
        Vehicle[] temporalNewArray = new Vehicle[length+1];
        System.arraycopy(vehiclesArray,0,temporalNewArray,0,length);
        temporalNewArray[length] = newVehicle;
        vehiclesArray = temporalNewArray;
    }

    public static void removeVehicle(String code) {

        int index = -1;
        for (int i = 0; i < vehiclesArray.length; i++) {
            if (vehiclesArray[i].getCode().equals(code)) {
                index = i;
                break;
            }
        }

        if (index == -1 ) {
            System.out.println("Invalid code, please check");
            return;
        }

        Vehicle theVehicle = vehiclesArray[index];
        try {
            String parkedAtCode = theVehicle.getParkedAt();
            Slot theSlot = (Slot) EntityHandler.getEntityByCode(parkedAtCode);
            System.out.println("Encountered slot code:" + Objects.requireNonNull(theSlot).getCode());
        }
        catch (NullPointerException error) {
            System.out.println("Not parked anywhere lol");
        }





        Vehicle[] temporalNewArray = new Vehicle[vehiclesArray.length-1];
        System.arraycopy(vehiclesArray,0,temporalNewArray,0,index);
        System.arraycopy(vehiclesArray,index+1,temporalNewArray,index, vehiclesArray.length-index-1);
        vehiclesArray = temporalNewArray;
    }

    public static Vehicle[] getVehiclesArray (){
        return vehiclesArray;
    }

    public static Vehicle[] getVehiclesFromType(String type) {
        List<Vehicle> typeVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehiclesArray) {
            if (vehicle.vehicleType.equals(type)) {
                typeVehicles.add(vehicle);
            }
        }

        return typeVehicles.toArray(new Vehicle[0]);
    }

    public static Vehicle getVehicleByCode (String code) {
        for (Vehicle vehicle : vehiclesArray) {
            if (vehicle.getCode().equals(code)) {
                return vehicle;
            }
        }
        return null;
    }
}
