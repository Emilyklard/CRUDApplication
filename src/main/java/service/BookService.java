package main.java.service;
import main.java.dao.BookDao;
import main.java.dto.BookDto;
import java.util.List;
import static java.util.stream.Collectors.toList;
public class BookService {
    private static final BookService INSTANCE = new BookService();
    private BookService(){}
    private final BookDao bookDao = BookDao.getInstance();
    public List<BookDto> findAllByAuthorId(Integer authorId){
        return bookDao.findAllByAuthorId(authorId).stream()
                .map(book -> new BookDto(
                        book.getId(), book.getName(), book.getYear(), book.getPages(),
                        book.getAuthor_id()
                ))
                .collect(toList());
    }
    public static BookService getInstance(){
        return INSTANCE;
    }
}
