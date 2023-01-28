package main.java.service;
import main.java.dao.AuthorDao;
import main.java.dto.AuthorDto;
import java.util.List;
import java.util.stream.Collectors;
public class AuthorService {
    private static final AuthorService INSTANCE = new AuthorService();
    private AuthorService(){}
    private final AuthorDao authorDao = AuthorDao.getInstance();
    public List<AuthorDto> findAll(){
        return authorDao.findAll().stream()
                .map(authors -> new AuthorDto(
                        authors.getId(), """
                        %s - %s """.formatted(authors.getFirst_name(), authors.getLast_name())
                ))
                .collect(Collectors.toList());
    }
    public static AuthorService getInstance(){
        return INSTANCE;
    }

 /*   public Integer create(AuthorDto authorDto){
        authorDao.save();
        return authorDao

    }
*/}
