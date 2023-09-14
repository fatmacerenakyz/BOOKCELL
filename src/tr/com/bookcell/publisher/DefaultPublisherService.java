package tr.com.bookcell.publisher;

import java.util.ArrayList;
import java.util.List;

import static tr.com.bookcell.util.InputFormatter.capitalizeFirst;
import static tr.com.bookcell.util.InputFormatter.capitalizeForMultipleStrings;

public class DefaultPublisherService implements PublisherService{
    private final PublisherRepository publisherRepository;

    public DefaultPublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void add(String name) {
        String formattedName = capitalizeForMultipleStrings(name);
        Publisher publisher = new Publisher(formattedName);
        boolean bool = false;
        for(Publisher tempPublisher : getAll()){
            if(tempPublisher.getName().equals(publisher.getName())){
                System.out.println("THERE IS ALREADY "+formattedName+" IN PUBLISHERS LIST");
                bool=true;
                break;
            }
        }
        if(!bool){
            publisherRepository.add(publisher);
        }
    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.getAll();
    }

    @Override
    public Publisher getByName(String name) {
        String formattedName = capitalizeForMultipleStrings(name);
        for(Publisher tempPublisher : getAll()){
            if(tempPublisher.getName().equals(formattedName)){
                return publisherRepository.getByName(formattedName);
            }
        }
        return null;
    }

    @Override
    public void remove(String name) {
        String formattedName = capitalizeForMultipleStrings(name);
        for(Publisher tempPublisher : getAll()){
            if(tempPublisher.getName().equals(formattedName)){
                publisherRepository.remove(name);
                return;
            }
        }
        System.out.println("THERE IS NO"+formattedName+" IN PUBLISHERS LIST!");
    }
}
