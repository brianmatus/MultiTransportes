package location;

public class Country extends Entity {

    public Country(String code, String name, String belongsTo) {
        super(code, name,belongsTo);
        this.entityType = "Country";
        EntityHandler.addEntity(this);
    }
}
