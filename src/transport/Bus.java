package transport;

import location.EntityHandler;
import location.Slot;

import java.util.Objects;

public class Bus extends Vehicle {

    private final int passengerCapacity;
    private final int maxVelocity;

    public Bus(String code, String name, int gasCapacity, int passengerCapacity, String brand, int costPerDay, int maxVelocity, String parkedAt) {
        super(code,name, gasCapacity, brand,costPerDay, parkedAt);
        this.passengerCapacity = passengerCapacity;
        this.maxVelocity = maxVelocity;
        this.vehicleType = "Bus";

        Slot theParkedSlot = (Slot) EntityHandler.getEntityByCode(parkedAt);
        Objects.requireNonNull(theParkedSlot).setStoredVehicle(this.code);

    }

    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }

    public int getMaxVelocity() {
        return this.maxVelocity;
    }

}
