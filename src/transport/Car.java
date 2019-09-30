package transport;

import location.EntityHandler;
import location.Slot;

import java.util.Objects;

public class Car extends  Vehicle {

    private final int maxVelocity;
    private final String transmission;


    public Car(String code, String name, int gasCapacity, String brand, int costPerDay, String transmission, int maxVelocity, String parkedAt) {
        super(code,name,gasCapacity,brand,costPerDay,parkedAt);
        this.transmission = transmission;
        this.maxVelocity = maxVelocity;
        this.vehicleType = "Car";

        Slot theParkedSlot = (Slot) EntityHandler.getEntityByCode(parkedAt);
        Objects.requireNonNull(theParkedSlot).setStoredVehicle(this.code);
    }


    public int getMaxVelocity() {
        return this.maxVelocity;
    }

    public String getTransmission() {
        return this.transmission;
    }
}
