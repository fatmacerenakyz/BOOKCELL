package tr.com.bookcell;

import tr.com.bookcell.book.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        book.setId("1");
        book.setName("Kırmızı Başlıklı Kız");

        Book book1 = new Book();
        book1.setId("2");
        book1.setName("Yeşil Başlıklı Ördek");

        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);

        defaultBookService.add(book);
        defaultBookService.add(book1);
        //defaultBookService.delete(book.getId());
        defaultBookService.getWithId(book1.getId());
        List<Book> bookList = defaultBookService.getAll();
        System.out.println(book);
        System.out.println(bookList);


    }
}
