package transport;

public class Vehicle {
    final String code;
    final String name;
    private final int gasCapacity;
    private final String brand;
    private final int costPerDay;
    private String parkedAt;

    String vehicleType ="";

    Vehicle(String code, String name, int gasCapacity, String brand, int costPerDay, String parkedAt) {
        this.code = code;
        this.name = name;
        this.gasCapacity = gasCapacity;
        this.brand = brand;
        this.costPerDay = costPerDay;
        this.parkedAt = parkedAt;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getGasCapacity() {
        return gasCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public String getParkedAt() {
        return parkedAt;
    }

    public void setParkedAt (String parkedAt) {
        this.parkedAt = parkedAt;
    }

    public String getVehicleType() {
        return vehicleType;
    }

}
