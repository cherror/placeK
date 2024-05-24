package servlet;

import controller.SeatController;
import controller.UserController;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servlet/returnSeat")
public class ReturnServlet extends HttpServlet {
    private SeatController seatController;
    private UserController userController;

    @Override
    public void init() throws ServletException {
        this.seatController = (SeatController) getServletContext().getAttribute("seatController");
        this.userController = (UserController) getServletContext().getAttribute("userController");

        if (seatController == null || userController == null) {
            throw new ServletException("Controllers not found");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int locationID;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String seatID = request.getParameter("seatID");
        String locationIDStr = request.getParameter("locationID");

        if (seatID == null || locationIDStr == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\":\"Missing seatID or locationID.\"}");
            return;
        }

        try {
            locationID = Integer.parseInt(locationIDStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\":\"Invalid locationID format.\"}");
            return;
        }
        String seatIDStr = "seat"+seatID;
        //좌석 반납
        try {
            seatController.updateSeatStatus(locationID, seatIDStr, false, null, null);
            userController.updateUserSeatInfo(user.getID(), false, -1, null, null, null);

            user.setRented(false);
            user.setLocationID(-1);
            user.setSeatNum(null);
            session.setAttribute("user", user);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Seat returned successfully.\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\":\"Error returning seat.\"}");
        }
    }
}
