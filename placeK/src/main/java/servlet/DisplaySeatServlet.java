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
            String locationUrl = getLocationUrl(locationID);
            if (locationUrl != null) {
                response.getWriter().write(locationUrl);
            } else {
                response.getWriter().write("Invalid location ID: " + locationID);
            }
        } else {
            response.getWriter().write("No location ID selected.");
        }
    }

    protected String getLocationUrl(int locationID) {
        switch (locationID) {
            case 1:
                return "/html/displaySeat/jjs.html";
            case 2:
                return "/html/displaySeat/mss.html";
            case 3:
                return "/html/displaySeat/KsmartLounge.html";
            case 4:
                return "/html/displaySeat/business.html";
            case 5:
                return "/html/displaySeat/law.html";
            default:
                return null;
        }
    }
}
