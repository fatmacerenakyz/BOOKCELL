package tr.com.bookcell.publisher;

import java.util.List;
import java.util.Locale;

import static tr.com.bookcell.util.InputFormatter.capitalizeForMultipleStrings;
import static tr.com.bookcell.util.InputFormatter.isEnglish;
import static tr.com.bookcell.util.TestClassMethods.ansiColorRed;
import static tr.com.bookcell.util.TestClassMethods.ansiColorReset;

public class DefaultPublisherService implements PublisherService {
    private final PublisherRepository publisherRepository;

    public DefaultPublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public boolean add(String name) {
        if(!isEnglish(name)){
            System.out.println(ansiColorRed()+"ENGLISH CHARACTERS ONLY."+ansiColorReset());
            return false;
        }
        for (Publisher tempPublisher : getAll()) {
            if (tempPublisher.getName().equals(name.toUpperCase(Locale.ENGLISH))) {
                System.out.println(ansiColorRed()+"THERE IS ALREADY " + name + " IN PUBLISHERS LIST"+ansiColorReset());
                return false;
            }
        }

        Publisher publisher = new Publisher(name.toUpperCase(Locale.ENGLISH));
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
        for (Publisher tempPublisher : getAll()) {
            if (tempPublisher.getName().equals(name.toUpperCase(Locale.ENGLISH))) {
                publisherRepository.remove(name.toUpperCase(Locale.ENGLISH));
                return;
            }
        }
        System.out.println("THERE IS NO" + name + " IN PUBLISHERS LIST!");
    }

    @Override
    public Publisher getByName(String name) {
        for (Publisher temp : getAll()) {
            if (temp.getName().equals(name.toUpperCase(Locale.ENGLISH))) {
                return publisherRepository.getByName(name.toUpperCase(Locale.ENGLISH));
            }
        }
        return null;
    }
}
