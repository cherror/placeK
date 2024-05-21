package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/displaySeat")
public class DisplaySeatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locationIDStr = request.getParameter("locationID");
        if (locationIDStr != null) {
            int locationID = Integer.parseInt(locationIDStr);
            // locationID 값을 터미널에 출력
            System.out.println("Selected Location ID: " + locationID);
            response.getWriter().println("Selected Location ID: " + locationID);
        } else {
            // locationID 값이 없는 경우 에러 메시지 출력
            response.getWriter().println("No location ID selected.");
        }
    }
}
