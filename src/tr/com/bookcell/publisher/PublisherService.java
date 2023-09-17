package tr.com.bookcell.publisher;

import java.util.List;

public interface PublisherService {
    boolean add(String name);
    List<Publisher> getAll();
    Publisher getById(Integer id);
    void remove(String name);
    Publisher getByName(String name);
}
