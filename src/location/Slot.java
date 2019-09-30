package location;

import transport.Vehicle;
import transport.VehicleHandler;

public class Slot extends Entity {

    private String storedVehicle;

    public Slot(String code, String name, String belongsTo, String storedVehicle) {
        super(code, name,belongsTo);

        this.entityType = "Slot";
        this.storedVehicle = storedVehicle;
        EntityHandler.addEntity(this);
    }

    public Vehicle getStoredVehicle() {
        return VehicleHandler.getVehicleByCode(storedVehicle);
    }

    public void setStoredVehicle(String storedVehicle) {
        this.storedVehicle = storedVehicle;
    }
}
