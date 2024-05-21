package servlet;

import controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/signIn")
public class SignInServlet extends HttpServlet {
    private UserController userController;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 데이터 가져오기
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        // 여기서 데이터베이스에 사용자 정보를 저장하거나 다른 처리를 합니다.
        // 예시로 사용자 정보를 콘솔에 출력합니다.
        System.out.println("Username: " + userID);
        System.out.println("Password: " + password);

        // 성공 메시지를 응답으로 보냅니다.
        response.getWriter().println("Sign up successful!");
    }
}
