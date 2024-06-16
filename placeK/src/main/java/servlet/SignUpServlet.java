package servlet;

import model.User;
import controller.UserController;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/signUp")
public class SignUpServlet extends HttpServlet {

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
        String major = request.getParameter("major");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (userID.isEmpty() || major == null || password.isEmpty() || passwordCheck.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": false, \"error\": \"MISSING_FIELDS\"}");
            return;
        } else if (!userID.matches("\\d+")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": false, \"error\": \"ID_MUST_BE_NUMERIC\"}");
            return;
        } else if (!password.equals(passwordCheck)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": false, \"error\": \"PASSWORD_MISMATCH\"}");
            return;
        } else {
            int userIDInt = Integer.parseInt(userID);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User user = new User(userIDInt, hashedPassword, major, false, -1, null, null, null);
            boolean isCreated = userController.createUser(user);
            if (isCreated) {
                response.getWriter().write("{\"status\": true}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"status\": false, \"error\": \"USER_ALREADY_EXISTS\"}");
            }
        }
    }
}
