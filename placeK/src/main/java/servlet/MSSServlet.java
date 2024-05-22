package servlet;

import controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/displaySeat/mms")
public class MSSServlet extends DisplaySeatServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("MSSServlet init");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 여기에 JJS 페이지로 넘어갔을 때 실행할 로직을 추가합니다.
        request.getRequestDispatcher("/html/displaySeat/mss.html").forward(request, response);
    }
}
