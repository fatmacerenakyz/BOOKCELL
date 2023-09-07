package tr.com.bookcell.author;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.capitalizeFirst;

public class DefaultAuthorService implements AuthorService {
    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void add(String name, String surname) {
        String formattedName = capitalizeFirst(name);
        String formattedSurname = capitalizeFirst(surname);
        Author author = new Author(formattedName, formattedSurname);
        boolean bool = false;
        for (Author tempAuthor : getAll()) {
            if (tempAuthor.getName().equals(author.getName()) && tempAuthor.getSurname().equals(author.getSurname())) {
                System.out.println("THERE IS ALREADY "+formattedName+" "+formattedSurname+" IN AUTHORS LIST");
                bool = true;
                break;
            }
        }
        if(!bool){
            authorRepository.add(author);
        }
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public List<Author> getByName(String name) {
        String formattedName = capitalizeFirst(name);
        for(Author tempAuthor : getAll()){
            if(tempAuthor.getName().equals(formattedName))
                return authorRepository.getByName(formattedName);
        }
        System.out.println("THERE IS NO "+formattedName+" IN AUTHORS LIST!");
        return null;
    }

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        String formattedName = capitalizeFirst(name);
        String formattedSurname = capitalizeFirst(surname);
        for(Author tempAuthor:getAll()){
            if(tempAuthor.getName().equals(formattedName)&&tempAuthor.getSurname().equals(formattedSurname))
                return authorRepository.getByNameAndSurname(formattedName, formattedSurname);
        }
        System.out.println("THERE IS NO "+formattedName+" "+formattedSurname+" IN AUTHORS LIST!");
        return null;
    }

    @Override
    public void remove(String name, String surname) {
        String formattedName = capitalizeFirst(name);
        String formattedSurname = capitalizeFirst(surname);
        for(Author author : getAll()){
            if(author.getName().equals(formattedName) && author.getSurname().equals(formattedSurname)){
                authorRepository.remove(formattedName, formattedSurname);
                return;
            }
        }
        System.out.println("THERE IS NO "+formattedName+" "+formattedSurname+" IN AUTHORS LIST!");
    }
}
