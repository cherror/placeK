package servlet;

import controller.LocationController;
import controller.SeatController;
import controller.UserController;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/servlet/signIn")
public class SignInServlet extends HttpServlet {
    private UserController userController;

    @Override
    public void init() throws ServletException {
        this.userController = (UserController) getServletContext().getAttribute("userController");
        if (this.userController == null) {
            throw new ServletException("UserController not initialized!");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("username");
        String password = request.getParameter("password");

        int userIDInt;
        try {
            userIDInt = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": false, \"error\": \"ID must be an integer\"}");
            return;
        }

        Map<String, Object> loginResult = userController.loginUser(userIDInt, password);
        if ((Boolean) loginResult.get("status")) {
            User user = userController.getUserInfo(userIDInt);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            userController.addUserSession(userIDInt, session);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\": true}");
        } else {
            String error = (String) loginResult.get("error");
            response.setContentType("application/json");
            if ("USER_NOT_FOUND".equals(error)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"status\": false, \"error\": \"USER_NOT_FOUND\"}");
            } else if ("WRONG_PASSWORD".equals(error)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"status\": false, \"error\": \"WRONG_PASSWORD\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"status\": false, \"error\": \"UNKNOWN_ERROR\"}");
            }
        }
    }
}
