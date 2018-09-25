package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData(){

        System.out.println("ENTRY: initData()");

        Author wladek = new Author("Włodzimierz", "Gajda");
        Book git = new Book("Rozproszony system kontroli wersji", "937923489","Helion");
        wladek.getBooks().add(git);
        git.getAuthors().add(wladek);

        Author hotrmann = new Author("Cay", "Hormtmann");
        Book java = new Book("Java. Podstawy", "3240942309","Helion");
        hotrmann.getBooks().add(java);
        java.getAuthors().add(hotrmann);

        authorRepository.save(wladek);
        authorRepository.save(hotrmann);
        bookRepository.save(git);
        bookRepository.save(java);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
