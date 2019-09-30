package location;

public class Entity {
    String code;
    String name;
    String entityType;
    private String belongsTo;

    Entity(String code, String name, String belongsTo) {
        this.code = code;
        this.name = name;
        this.belongsTo = belongsTo;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBelongsTo() {return belongsTo;}
    public void setBelongsTo(String belongsTo) { this.belongsTo = belongsTo;}

}
