package servlet;

import model.User;
import controller.UserController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/signUp")
public class SignupServlet extends HttpServlet {

    private UserController userController;

    @Override
    public void init() throws ServletException {
        // 서블릿 초기화 시점에 컨텍스트에서 UserController를 가져옵니다.
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
                response.sendRedirect("../../html/signin.html");
                System.out.println("회원가입 성공");
                // 회원가입이 성공하면 로그인 페이지로 이동
                response.sendRedirect(request.getContextPath() + "/html/signin.html");
            } else {
                System.out.println("회원가입 실패. 해당 ID가 존재합니다.");
                // 회원가입이 실패하면 회원가입 페이지로 이동
                response.sendRedirect(request.getContextPath() + "/html/signup.html");
            }
        }
    }
}
