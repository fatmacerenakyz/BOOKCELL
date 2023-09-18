package tr.com.bookcell.author;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Locale;

import static tr.com.bookcell.util.InputFormatter.*;
import static tr.com.bookcell.util.TestClassMethods.ansiColorRed;
import static tr.com.bookcell.util.TestClassMethods.ansiColorReset;

public class DefaultAuthorService implements AuthorService {
    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean add(String name, String surname) {
        if(!isEnglish(name)||!isEnglish(surname)){
            System.out.println(ansiColorRed()+ "ENGLISH CHARACTERS ONLY."+ansiColorReset());
            return false;
        }
        for (Author tempAuthor : getAll()) {
            if (tempAuthor.getName().equals(name.toUpperCase(Locale.ENGLISH)) && tempAuthor.getSurname().equals(surname.toUpperCase(Locale.ENGLISH))) {
                System.out.println("THERE IS ALREADY " + name + " " + surname + " IN AUTHORS LIST");
                return false;
            }
        }
        Author author = new Author(name.toUpperCase(Locale.ENGLISH), surname.toUpperCase(Locale.ENGLISH));
        authorRepository.add(author);
        return true;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public List<Author> getByName(String name) {
        for (Author tempAuthor : getAll()) {
            if (tempAuthor.getName().equals(name.toUpperCase(Locale.ENGLISH)))
                return authorRepository.getByName(name.toUpperCase(Locale.ENGLISH));
        }
        return null;
    }

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        for (Author tempAuthor : getAll()) {
            if (tempAuthor.getName().equals(name.toUpperCase(Locale.ENGLISH)) && tempAuthor.getSurname().equals(surname.toUpperCase(Locale.ENGLISH)))
                return authorRepository.getByNameAndSurname(name.toUpperCase(Locale.ENGLISH), surname.toUpperCase(Locale.ENGLISH));
        }
        return null;
    }

    @Override
    public void remove(String name, String surname) {
        for (Author author : getAll()) {
            if (author.getName().equals(name.toUpperCase()) && author.getSurname().equals(surname.toUpperCase())) {
                authorRepository.remove(name.toUpperCase(Locale.ENGLISH), surname.toUpperCase(Locale.ENGLISH));
                return;
            }
        }
        System.out.println("THERE IS NO " + name + " " + surname + " IN AUTHORS LIST!");
    }
}
