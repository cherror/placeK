package servlet;

import controller.SeatController;
import model.Seat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/displaySeatInfo")
public class DisplaySeatInfoServlet extends HttpServlet {
    private SeatController seatController;
    @Override
    public void init() throws ServletException {
        this.seatController = (SeatController) getServletContext().getAttribute("seatController");
        if(this.seatController == null){
            throw new ServletException("SeatController not found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locationIDStr = request.getParameter("locationID");
        int locationID = Integer.parseInt(locationIDStr);

        List<Seat> seatList = seatController.getSeatsInfo(locationID);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("[");
        for (int i = 0; i < seatList.size(); i++) {
            Seat seat = seatList.get(i);
            jsonResponse.append("{");
            jsonResponse.append("\"locationID\":").append(seat.getLocationID()).append(",");
            jsonResponse.append("\"seatNum\":\"").append(seat.getSeatNumber()).append("\",");
            jsonResponse.append("\"isRented\":").append(seat.getIsRented()).append(",");
            jsonResponse.append("\"rentTime\":").append(seat.getRentTime());
            jsonResponse.append("}");
            if (i < seatList.size() - 1) {
                jsonResponse.append(",");
            }
        }
        jsonResponse.append("]");
        response.getWriter().write(jsonResponse.toString());
    }
}
