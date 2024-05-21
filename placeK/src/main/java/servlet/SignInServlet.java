package servlet;

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
            //로그인 실패 시 모달 띄우기
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID must be an integer");
            return;
        }

        Map<String, Object> loginResult = userController.loginUser(userIDInt, password);
        if ((Boolean) loginResult.get("status")) {
            System.out.println("로그인 성공");

            //user 정보 객체에 저장
            User user = userController.getUserInfo(userIDInt);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("../../html/checkMySeat.html");
//            response.sendRedirect("../../html/chooseOption.html");
        } else {
            System.out.println("로그인 실패");
        }
    }
}
