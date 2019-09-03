package util;
import bean.User;

public class ArrayPosition {
    private static int userCounter = 0;
    public static int getUserCounter(){ return userCounter++; }
    public static void removedUser () { userCounter--;}

    public static int getNotNullIndex (User[]array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return i;
            }
        }
        return array.length-1;
    }
}
