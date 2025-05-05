
// Service: UserService.java
package service;

import entity.Book;
import repository.BookRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserService {
    private BookRepository bookRepo;

    public UserService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getIssuedBooks(String userId) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookRepo.getAllBooks()) {
            if (userId.equals(book.getIssuedToUserId())) {
                result.add(book);
            }
        }
        return result;
    }

    public Collection<Book> viewAllBooks() {
        return bookRepo.getAllBooks();
    }

}

