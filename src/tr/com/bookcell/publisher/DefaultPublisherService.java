package tr.com.bookcell.publisher;

import java.util.List;

public class DefaultPublisherService implements PublisherService{
    private final PublisherRepository publisherRepository;

    public DefaultPublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void add(String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.add(publisher);
    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.getAll();
    }

    @Override
    public Publisher getByName(String name) {
        return publisherRepository.getByName(name);
    }

    @Override
    public void remove(String name) {
        publisherRepository.remove(name);
    }
}
