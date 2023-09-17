package tr.com.bookcell.author;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.capitalizeFirst;
import static tr.com.bookcell.util.InputFormatter.capitalizeForMultipleStrings;

public class DefaultAuthorService implements AuthorService {
    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean add(String name, String surname) {
        String formattedName = capitalizeForMultipleStrings(name);
        String formattedSurname = capitalizeFirst(surname);
        for (Author tempAuthor : getAll()) {
            if (tempAuthor.getName().equals(formattedName) && tempAuthor.getSurname().equals(formattedSurname)) {
                System.out.println("THERE IS ALREADY " + formattedName + " " + formattedSurname + " IN AUTHORS LIST");
                return false;
            }
        }
        Author author = new Author(formattedName, formattedSurname);
        authorRepository.add(author);
        return true;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public List<Author> getByName(String name) {
        String formattedName = capitalizeForMultipleStrings(name);
        for (Author tempAuthor : getAll()) {
            if (tempAuthor.getName().equals(formattedName))
                return authorRepository.getByName(formattedName);
        }
        return null;
    }

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        String formattedName = capitalizeForMultipleStrings(name);
        String formattedSurname = capitalizeFirst(surname);
        for (Author tempAuthor : getAll()) {
            if (tempAuthor.getName().equals(formattedName) && tempAuthor.getSurname().equals(formattedSurname))
                return authorRepository.getByNameAndSurname(formattedName, formattedSurname);
        }
        return null;
    }

    @Override
    public void remove(String name, String surname) {
        String formattedName = capitalizeForMultipleStrings(name);
        String formattedSurname = capitalizeFirst(surname);
        for (Author author : getAll()) {
            if (author.getName().equals(formattedName) && author.getSurname().equals(formattedSurname)) {
                authorRepository.remove(formattedName, formattedSurname);
                return;
            }
        }
        System.out.println("THERE IS NO " + formattedName + " " + formattedSurname + " IN AUTHORS LIST!");
    }
}
