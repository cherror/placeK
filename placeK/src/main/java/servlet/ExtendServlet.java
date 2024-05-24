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

@WebServlet("/servlet/extendSeat")
public class ExtendServlet extends HttpServlet {
    private SeatController seatController;
    private UserController userController;

    @Override
    public void init() throws ServletException {
        this.seatController = (SeatController) getServletContext().getAttribute("seatController");
        this.userController = (UserController) getServletContext().getAttribute("userController");

        if (this.seatController == null || this.userController == null) {
            throw new ServletException("seatController or userController is null");
        }
        System.out.println("extendServlet init");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int locationID;
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
        System.out.println("seatIDStr:" + seatIDStr);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //시간 연장
        try {
            user.setExtendTime();
            String rentedTime = user.getRentedTime();
            String returnTime = user.getReturnTime();
            userController.updateUserSeatInfo(user.getID(), true, locationID, seatID, rentedTime, returnTime);
            seatController.updateSeatStatus(locationID, seatIDStr, true, rentedTime, returnTime);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"failure\"}");
        }
    }
}
