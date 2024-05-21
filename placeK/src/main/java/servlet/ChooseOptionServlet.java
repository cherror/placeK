package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/chooseOption")
public class ChooseOptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("selectSeat".equals(action)) {
            response.sendRedirect(request.getContextPath() + "/html/displaySeat.html");
        } else if ("checkSeat".equals(action)) {
            response.sendRedirect(request.getContextPath() + "/html/checkMySeat.html");
        } else {
            response.getWriter().println("Invalid action.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
