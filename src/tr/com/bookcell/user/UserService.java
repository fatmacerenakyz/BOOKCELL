package tr.com.bookcell.user;

public interface UserService {
    void add(User user);
    void delete(User user);
    User getWithId(String id);
}
