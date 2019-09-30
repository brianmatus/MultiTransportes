package location;

public class Facility extends Entity {

    private String type;


    public Facility(String code,String name, String type, String belongsTo) {
        super(code, name, belongsTo);
        this.type = type;
        this.entityType = "Facility";
        EntityHandler.addEntity(this);

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
