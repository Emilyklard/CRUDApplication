package main.java.dao;
import main.java.entity.Book;
import main.java.util.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class BookDao implements Dao<Long, Book> {
    private static final BookDao INSTANCE = new BookDao();
    private static final String FIND_ALL = """
SELECT *
FROM book
""";
    private static final String FIND_ALL_BY_AUTHOR_ID = """
SELECT *
FROM book
WHERE book.id = ?
""";
    private BookDao(){}

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public List<Book> findAllByAuthorId(Integer authorId){
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_AUTHOR_ID)) {
            preparedStatement.setObject(1, authorId);
            var resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List <Book> findAll(){
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public Book save(Book entity) {
        return null;
    }

    public static BookDao getInstance(){
        return INSTANCE;
}
    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getObject("id", java.lang.Long.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("year", Short.class),
                resultSet.getObject("pages", Short.class),
                resultSet.getObject("author_id", Integer.class));
    }
}

