package location;

public class ParkingSpace extends Entity {
    private final String type;
    public final Slot[][] spaces;
    public ParkingSpace(String code, String name, String type, int rows, int columns, String belongsTo) {
        super(code, name, belongsTo);
        this.entityType = "ParkingSpace";
        this.type = type;
        this.spaces = new Slot[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.spaces[i][j] = new Slot(
                        this.code + "-" + (i+1) + "_" + (j+1),
                        "Slot " + (i+1) + "," + (j+1),
                        this.code,
                        null);
            }
        }
        EntityHandler.addEntity(this);
    }

    public String getType() {
        return type;
    }

}
