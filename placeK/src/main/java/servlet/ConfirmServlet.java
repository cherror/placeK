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

//좌석 신청하기
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
        boolean success = false;
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
        if(locationInfo.getAvailableMajors() != null) {
            String userMajor = userController.getUserMajor(user.getID());
            if(availableMajor.contains(userMajor)) {
//                2. userDB에 해당 좌석의 정보 저장
                userController.updateUserSeatInfo(user.getID(),  true, locationID, seatID);
//                3. seatDB에 좌석 업데이트
                 seatController.updateSeatStatus(locationID, seatID, true);

                success = true;
                jsonResponse = "{"
                        + "\"message\":\"" + "Seat rental complete" + "\""
                        + "}";
            } else {
                jsonResponse = "{"
                        + "\"message\":\"" + "Seat rental failure: Not applicable major" + "\""
                        + "}";
            }
        } else {
            jsonResponse = "{"
                    + "\"message\":\"" + "Seat rental failure" + "\""
                    + "}";
        }
        response.getWriter().write(jsonResponse);

    }
}
