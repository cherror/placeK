package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (user != null) {
            String userJson = "{"
                    + "\"isRented\":" + user.isRented()  // Add this line
                    + "}";
            response.getWriter().write(userJson);
        } else {
            response.getWriter().write("{}");
        }

        doPost(request, response);
    }
}
