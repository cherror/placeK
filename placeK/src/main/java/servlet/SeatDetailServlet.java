package servlet;

import controller.LocationController;
import controller.SeatController;
import model.Location;
import model.Seat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//좌석 한개 클릭 했을 때 상세정보
@WebServlet("/servlet/seatDetail")
public class SeatDetailServlet extends HttpServlet {
    private LocationController locationController;
    private SeatController seatController;
    public void init() throws ServletException {
        this.locationController = (LocationController) getServletContext().getAttribute("locationController");
        this.seatController = (SeatController) getServletContext().getAttribute("seatController");
        if (this.locationController == null || this.seatController == null) {
            throw new ServletException("Controllers not initialized!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int locationID;
        String locationIDStr = request.getParameter("locationID");
        String seatID = request.getParameter("seatID");

        try {
            locationID = Integer.parseInt(locationIDStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Location locationInfo = locationController.getLocationInfo(locationID);
        Seat seatInfo = seatController.getSeat(locationID, seatID);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = "{"
                + "\"locationName\":\"" + locationInfo.getLocationName() + "\","
                + "\"seatNum\":\"" + seatInfo.getSeatNumber() + "\","
                + "\"availableMajors\":\"" + locationInfo.getAvailableMajors() + "\","
                + "\"isRented\":" + seatInfo.getIsRented()
                + "}";
        response.getWriter().write(jsonResponse);
    }
}
