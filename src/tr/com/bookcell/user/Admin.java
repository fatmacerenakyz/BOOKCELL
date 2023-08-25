package tr.com.bookcell.user;

public class Admin extends User {
    public Admin(){}

    @Override
    public String toString() {
        return super.toString();
    }

    public Admin(String id, String name, String surname, String userName, String email, String password) {
        super(id, name, surname, userName, email, password);
    }

}