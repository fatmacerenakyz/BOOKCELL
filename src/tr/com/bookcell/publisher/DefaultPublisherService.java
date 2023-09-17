package tr.com.bookcell.publisher;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.capitalizeForMultipleStrings;

public class DefaultPublisherService implements PublisherService {
    private final PublisherRepository publisherRepository;

    public DefaultPublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public boolean add(String name) {
        String formattedName = capitalizeForMultipleStrings(name);
        Publisher publisher = new Publisher(formattedName);
        for (Publisher tempPublisher : getAll()) {
            if (tempPublisher.getName().equals(publisher.getName())) {
                System.out.println("THERE IS ALREADY " + formattedName + " IN PUBLISHERS LIST");
                return false;
            }
        }


        publisherRepository.add(publisher);
        return true;

    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.getAll();
    }

    @Override
    public Publisher getById(Integer id) {
        for (Publisher temp : getAll()) {
            if (temp.getId().equals(id)) {
                return publisherRepository.getById(id);
            }
        }
        return null;
    }

    @Override
    public void remove(String name) {
        String formattedName = capitalizeForMultipleStrings(name);
        for (Publisher tempPublisher : getAll()) {
            if (tempPublisher.getName().equals(formattedName)) {
                publisherRepository.remove(name);
                return;
            }
        }
        System.out.println("THERE IS NO" + formattedName + " IN PUBLISHERS LIST!");
    }

    @Override
    public Publisher getByName(String name) {
        for (Publisher temp : getAll()) {
            if (temp.getName().equalsIgnoreCase(name)) {
                return publisherRepository.getByName(name);
            }
        }
        return null;
    }
}
