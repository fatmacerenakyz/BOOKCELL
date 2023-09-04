package tr.com.bookcell.publisher;

import java.util.List;

public interface PublisherService {
    void add(String name);
    List<Publisher> getAll();
    Publisher getByName(String name);
    void remove(String name);
}
