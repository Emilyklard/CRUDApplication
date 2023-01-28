package main.java.servlet;
import main.java.dto.AuthorDto;
import main.java.dto.CreateAuthorDto;
import main.java.entity.Author;
import main.java.service.AuthorService;
import main.java.service.BookService;
import main.java.util.JspHelper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    private final AuthorService authorService = AuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors", authorService.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/authors.jsp")
                .forward(req, resp);

 /*       resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try ( var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список авторов:</h1>");
            printWriter.write("<ul>");
            authorService.findAll().forEach(authorDto -> {
                printWriter.write("""
                        <li> 
                        <a href = "books?authorId=%d">%s
                        </li>"""
                        .formatted(authorDto.getId(), authorDto.getDescription()));
            });
            printWriter.write("</ul>");
 */
    }

    /*    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
            var userDto = CreateAuthorDto.builder()
                    .first_name(req.getParameter("first_name"))
                    .last_name(req.getParameter("last_name"))
                    .build();
            authorService.create(authorDto);
        var first_name = req.getParameter("first_name");
    }
*/}
