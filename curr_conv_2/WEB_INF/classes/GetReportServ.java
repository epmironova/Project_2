import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetReportServ extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      response.setContentType("text/html");

      String url=(String) getServletContext().getAttribute("reportUrl");

    // Redirect call to another url
    response.sendRedirect(url);
  }

}