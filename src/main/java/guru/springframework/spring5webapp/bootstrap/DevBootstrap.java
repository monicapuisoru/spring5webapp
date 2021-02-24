package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.models.Author;
import guru.springframework.spring5webapp.models.Book;
import guru.springframework.spring5webapp.models.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private PublisherRepository publisherRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;


    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){

        Publisher pub1 = new Publisher();
        pub1.setName("Harper Collins1");

        publisherRepository.save(pub1);

        Author yuval = new Author("Yuval", "Noah");
        Book sapiens = new Book("Homo sapiens", "1234", pub1);
        yuval.getBooks().add(sapiens);
        sapiens.getAuthors().add(yuval);

        authorRepository.save(yuval);
        bookRepository.save(sapiens);


        Author jkr = new Author("J.K.", "Rowling");
        Book hp = new Book("Harry Potter", "1122", pub1);
        jkr.getBooks().add(hp);
        hp.getAuthors().add(jkr);

        authorRepository.save(jkr);
        bookRepository.save(hp);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
