package main.java.dao;
import lombok.Getter;
import lombok.SneakyThrows;
import main.java.entity.Author;
import main.java.util.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
@Getter
public class AuthorDao implements Dao<Long, Author>{
    private static final AuthorDao INSTANCE = new AuthorDao();
    private static final String FIND_ALL = """
SELECT *
FROM author
""";
    private static final String SAVE_SQL = "INSERT INTO author (first_name, last_name) VALUES " + "(?, ?)";
    private AuthorDao(){}


    @Override
    public List<Author> findAll() {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(buildAuthor(resultSet));
            }
            return authors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Author entity) {

    }

    @Override
    @SneakyThrows
    public Author save(Author entity) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getFirst_name());
            preparedStatement.setObject(2, entity.getLast_name());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        }
    }

    public static AuthorDao getInstance(){
        return INSTANCE;
    }
    private Author buildAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("first_name", String.class),
                resultSet.getObject("last_name", String.class));
    }
}
