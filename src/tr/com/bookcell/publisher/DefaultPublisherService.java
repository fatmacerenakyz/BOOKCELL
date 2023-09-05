package tr.com.bookcell.publisher;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.capitalizeFirst;

public class DefaultPublisherService implements PublisherService{
    private final PublisherRepository publisherRepository;

    public DefaultPublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void add(String name) {
        String formattedName = capitalizeFirst(name);
        Publisher publisher = new Publisher(formattedName);
        for(Publisher tempPublisher : getAll()){
            if(tempPublisher.getName().equals(publisher.getName())){
                System.out.println("THERE IS ALREADY "+formattedName+" IN PUBLISHERS LIST");
                break;
            }
            publisherRepository.add(publisher);
        }
    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.getAll();
    }

    @Override
    public Publisher getByName(String name) {
        String formattedName = capitalizeFirst(name);
        return publisherRepository.getByName(formattedName);
    }

    @Override
    public void remove(String name) {
        String formattedName = capitalizeFirst(name);
        for(Publisher tempPublisher : getAll()){
            if(tempPublisher.getName().equals(formattedName)){
                publisherRepository.remove(name);
                break;
            }
            System.out.println("THERE IS NO"+formattedName+" IN PUBLISHERS LIST!");
        }
    }
}
