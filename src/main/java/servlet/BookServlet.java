package main.java.servlet;
import main.java.dto.BookDto;
import main.java.service.BookService;
import main.java.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private final BookService bookService = BookService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var authorId = Integer.valueOf(req.getParameter("authorId"));
        List<BookDto> bookDto = bookService.findAllByAuthorId(authorId);
        req.setAttribute("books", bookDto);
        req.getSession().setAttribute("bookMap", bookDto.stream()
                .collect(toMap(BookDto::getId, BookDto::getName)));
req.getRequestDispatcher(JspHelper.getPath("books"))
        .forward(req, resp);
      /*  try( var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Книги выбранного автора: </h1>");
            printWriter.write("<ul>");
            bookService.findAllByAuthorId(authorId).forEach(bookDto -> printWriter.write("""
                    <li>
                     %s - %s - %s
                     </li>""".formatted(bookDto.getName(), bookDto.getYear(), bookDto.getPages())));
            printWriter.write("</ul>");
        }
 */   }
}
