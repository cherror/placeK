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
        // 폼 데이터 가져오기
        String userID = request.getParameter("username");
        String major = request.getParameter("major");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");

        // 데이터 확인
        if(userID.isEmpty() || major == null || password.isEmpty() || passwordCheck.isEmpty()){
            System.out.println("빈칸을 채워주세요.");
            return;
        } else if(!userID.matches("\\d+")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID는 숫자로만 구성되어야 합니다.");
            System.out.println("ID는 숫자로만 구성되어야 합니다.");
            return;
        } else if (!password.equals(passwordCheck)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        } else {
            int userIDInt = Integer.parseInt(userID);
            User user = new User(userIDInt, password, major, false, -1, null, null);
            boolean isCreated = userController.createUser(user);
            if (isCreated) {
                System.out.println("회원가입 성공");
                response.sendRedirect(request.getContextPath() + "/html/signIn.html");
            } else {
                System.out.println("회원가입 실패. 해당 ID가 존재합니다.");
                response.sendRedirect(request.getContextPath() + "/html/signUp.html");
            }
        }
    }
}
