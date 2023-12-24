package ru.sadykoff.skillbox.app.services;

import org.apache.log4j.Logger;
import ru.sadykoff.skillbox.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByRegex(String queryRegex) {
        logger.debug(queryRegex);

        var regexParts = queryRegex.toLowerCase().split("=");
        if (regexParts.length == 2) {
            var type = regexParts[0];
            var value = regexParts[1];
            logger.debug(type);
            logger.debug(value);

            if (type.equals("author") || type.equals("title") || type.equals("size")) {
                for (Book book : retreiveAll()) {

                    if (type.equals("author") && book.getAuthor().equals(value)) {
                        logger.info(String.format("remove book by author %s completed: %s", value, book));
                        return repo.remove(book);
                    }
                    if (type.equals("title") && book.getTitle().equals(value)) {
                        logger.info(String.format("remove book by title %s completed: %s", value, book));
                        return repo.remove(book);
                    }
                    try {
                        if (type.equals("size") && book.getSize() == Integer.parseInt(value)) {
                            logger.info(String.format("remove book by size %s completed: %s", value, book));
                            return repo.remove(book);
                        }
                    } catch (Exception e) {
                        logger.error(e);
                    }

                }
            }
        }

        return false;
    }
}
