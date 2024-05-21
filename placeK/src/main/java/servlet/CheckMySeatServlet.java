package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servlet/checkMySeat")
public class CheckMySeatServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String locationName;
        if (user != null) {
            if(user.getLocationID() == 1){
                locationName = "미래관-자율주행스튜디오";
            } else if(user.getLocationID() == 2){
                locationName = "미래관-무한상상실";
            } else if(user.getLocationID() == 3){
                locationName = "미래관-K Smart Lounge";
            } else if(user.getLocationID() == 4){
                locationName = "경영관-스터디카페";
            } else if(user.getLocationID() == 5){
                locationName = "법학관-스터디카페";
            } else {
                locationName = "장소 없음";
            }
            String userJson = "{"
                    + "\"userID\":" + user.getID() + ","
                    + "\"location\":\"" + locationName + "\","
                    + "\"seatNum\":\"" + user.getSeatNumber() + "\","
                    + "\"rentedTime\":\"" + user.getRentedTime() + "\","
                    + "\"major\":\"" + user.getMajor() + "\""
                    + "}";
            response.getWriter().write(userJson);
            System.out.println(userJson);
        } else {
            response.getWriter().write("{}");
        }
    }
}
