package servlet;

import controller.LocationController;
import controller.SeatController;
import controller.UserController;
import model.Location;
import model.Seat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/rentedSeat")
public class RentedSeatServlet extends HttpServlet {
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
        //location, seat number를 DisplaySeat에서 받아와야함
        Location locationInfo = locationController.getLocationInfo(1);
        Seat seatInfo = seatController.getSeat(1, "seat1");

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
