package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 데이터 가져오기
        String username = request.getParameter("username");
        String major = request.getParameter("major");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");

        // 비밀번호 확인
        if (!password.equals(passwordCheck)) {
            response.getWriter().println("Passwords do not match!");
            return;
        }

        // 여기서 데이터베이스에 사용자 정보를 저장하거나 다른 처리를 합니다.
        // 예시로 사용자 정보를 콘솔에 출력합니다.
        System.out.println("Username: " + username);
        System.out.println("Major: " + major);

        // 성공 메시지를 응답으로 보냅니다.
        response.getWriter().println("Sign up successful!");
    }
}
