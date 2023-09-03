package tr.com.bookcell.publisher;

import tr.com.bookcell.BaseRepository;

import java.util.List;

public interface PublisherRepository extends BaseRepository {
    void add(Publisher publisher);
    List<Publisher> getAll();
    void remove(Publisher publisher);
}
