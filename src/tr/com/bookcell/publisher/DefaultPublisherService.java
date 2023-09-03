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
    public void remove(String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.remove(publisher);
    }
}
