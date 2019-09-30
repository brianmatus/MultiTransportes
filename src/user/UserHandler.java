package user;
public class UserHandler {

    private static User[] usersArray = {new User("1", "Brian","Matus", User.Type.ADMINISTRATOR,"1")};

    public static User[] getUsers() {
        return usersArray;
    }


    public static User[] addUser(User user) {
        int length = usersArray.length;
        User[] temporalNewArray = new User[length+1];
        System.arraycopy(usersArray,0,temporalNewArray,0,length);
        temporalNewArray[length] = user;
        usersArray = temporalNewArray;
        return usersArray;
    }

    public static User[] removeUser(int index) {
        User[] temporalNewArray = new User[usersArray.length-1];
        System.arraycopy(usersArray,0,temporalNewArray,0,index);
        System.arraycopy(usersArray,index+1,temporalNewArray,index, usersArray.length-index-1);
        usersArray = temporalNewArray;
        return usersArray;
    }


}
