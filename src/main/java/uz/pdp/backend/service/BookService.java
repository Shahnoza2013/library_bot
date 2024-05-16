package uz.pdp.backend.service;

import uz.pdp.backend.model.book.Books;
import uz.pdp.backend.model.users.Users;
import uz.pdp.backend.repository.FileWriterAndLoader;
import uz.pdp.backend.statics.PathConstants;

import java.util.List;
import java.util.Objects;

public class BookService implements BaseService, PathConstants {
    FileWriterAndLoader<Books> writerAndLoader;

    public BookService() {
        this.writerAndLoader =new FileWriterAndLoader<>(BOOK_PATH);
    }

    public void save(Books myBook ) {
        List<Books> books = writerAndLoader.load(Books.class);
        for (int i = 0; i < books.size(); i++) {
            Books cur = books.get(i);
            if(Objects.equals(cur.getId(),myBook.getId())){
                books.set(i, myBook);
                writerAndLoader.write(books);
                return;
            }
        }books.add(myBook);
        writerAndLoader.write(books);
        return;
    }

    public Books get(Long id ) {
        List<Books> books = writerAndLoader.load(Books.class);
        for (int i = 0; i < books.size(); i++) {
            Books cur = books.get(i);
            if(Objects.equals(cur.getId(),id)){
                return cur;
            }
        }return null;
    }
}


