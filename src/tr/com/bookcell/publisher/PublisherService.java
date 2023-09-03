package tr.com.bookcell.publisher;

import java.util.List;

public interface PublisherService {
    void add(String name);
    List<Publisher> getAll();
    void remove(String name);
}
