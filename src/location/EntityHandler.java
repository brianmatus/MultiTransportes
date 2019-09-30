package location;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler {

    private static Entity[] entitiesArray = {};


    static void addEntity(Entity newEntity) {

        for (Entity entity : entitiesArray) {
            if (entity.code.equals(newEntity.code)) {
                System.out.println("Code already belongs to another entity");
                System.out.println(entity.code + "," + entity.name);
                return;
            }
        }

        int length = entitiesArray.length;
        Entity[] temporalNewArray = new Entity[length+1];
        System.arraycopy(entitiesArray,0,temporalNewArray,0,length);
        temporalNewArray[length] = newEntity;
        entitiesArray = temporalNewArray;
    }

    public static void removeEntity(String code) {

        int index = -1;
        for (int i = 0; i < entitiesArray.length; i++) {
            if (entitiesArray[i].getCode().equals(code)) {
                index = i;
                break;
            }
        }

        if (index == -1 ) {
            System.out.println("Invalid code, please check");
            return;
        }

        Entity[] temporalNewArray = new Entity[entitiesArray.length-1];
        System.arraycopy(entitiesArray,0,temporalNewArray,0,index);
        System.arraycopy(entitiesArray,index+1,temporalNewArray,index, entitiesArray.length-index-1);
        entitiesArray = temporalNewArray;
    }

    /*public static Entity[] getEntitiesArray (){
        return entitiesArray;
    }

     */

    public static Entity[] getEntitiesFromType(String type) {
        List<Entity> typeEntities = new ArrayList<>();

        for (Entity entity : entitiesArray) {
            if (entity.entityType.equals(type)) {
                typeEntities.add(entity);
            }
        }

        return typeEntities.toArray(new Entity[0]);
    }


    public static Entity getEntityByCode (String code) {
        for (Entity entity : entitiesArray) {
            if (entity.getCode().equals(code)) {
                return entity;
            }
        }
        return null;
    }

    public static Entity[] getEntitiesByBelonging(String code) {
        List<Entity> belongingEntities = new ArrayList<>();

        for (Entity entity : entitiesArray) {
            if (entity.getBelongsTo().equals(code)) {
                belongingEntities.add(entity);
            }
        }

        return belongingEntities.toArray(new Entity[0]);
    }
}
