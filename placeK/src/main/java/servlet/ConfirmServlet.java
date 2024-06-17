package servlet;

import controller.LocationController;
import controller.SeatController;
import controller.UserController;
import model.Location;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servlet/confirm")
public class ConfirmServlet extends HttpServlet {
    private LocationController locationController;
    private UserController userController;
    private SeatController seatController;

    @Override
    public void init() throws ServletException {
        this.locationController = (LocationController) getServletContext().getAttribute("locationController");
        this.userController = (UserController) getServletContext().getAttribute("userController");
        this.seatController = (SeatController) getServletContext().getAttribute("seatController");

        if (this.locationController == null || this.userController == null || this.seatController == null) {
            throw new ServletException("Controllers not found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int locationID;
        String jsonResponse;

        String locationIDStr = request.getParameter("locationID");
        String seatID = request.getParameter("seatID");

        try {
            locationID = Integer.parseInt(locationIDStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Location locationInfo = locationController.getLocationInfo(locationID);
        String availableMajor = locationInfo.getAvailableMajors();

        if (user.isRented()) {
            jsonResponse = "{\"status\": \"seat_already_assigned\"}";
        } else if (availableMajor != null && !availableMajor.contains(user.getMajor())) {
            jsonResponse = "{\"status\": \"major_mismatch\"}";
        } else {
            //session 관리
            user.setRented(true);
            user.setLocationID(locationID);
            user.setSeatNum(seatID);
            user.setRentedTime();
            user.setReturnTime();
            session.setAttribute("user", user);

            String rentedTime = user.getRentedTime();
            String returnTime = user.getReturnTime();

            // userDB에 해당 좌석의 정보 저장
            userController.updateUserSeatInfo(user.getID(), true, locationID, seatID, rentedTime, returnTime);
            // seatDB에 좌석 업데이트
            seatController.updateSeatStatus(locationID, seatID, true, rentedTime, returnTime);

            jsonResponse = "{\"status\": \"success\"}";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}