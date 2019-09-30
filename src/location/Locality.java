package location;

public class Locality extends Entity {

    public Locality(String code, String name, String belongsTo) {
        super(code, name,belongsTo);
        this.entityType = "Locality";
        EntityHandler.addEntity(this);

    }
}
