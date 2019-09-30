package transport;

import location.EntityHandler;
import location.Slot;

import java.util.Objects;

public class Airplane extends Vehicle {

    private int maxHeight;
    private final int passengerCapacity;
    public Airplane(String code, String name, int gasCapacity, int passengerCapacity, String brand, int costPerDay, int maxHeight, String parkedAt) {
        super(code, name, gasCapacity, brand, costPerDay, parkedAt);
        this.vehicleType = "Airplane";
        this.maxHeight = maxHeight;
        Slot theParkedSlot = (Slot) EntityHandler.getEntityByCode(parkedAt);
        Objects.requireNonNull(theParkedSlot).setStoredVehicle(this.code);
        this.maxHeight = maxHeight;
        this.passengerCapacity = passengerCapacity;

    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

}
