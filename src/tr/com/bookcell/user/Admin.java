package tr.com.bookcell.user;

import java.util.Objects;

public class Admin extends User {
    private String userName;

    public Admin() {
    }

    public Admin(String id, String email, String password, String userName) {
        super(id, email, password);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(userName, admin.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userName='" + userName + '\'' +
                '}';
    }
}