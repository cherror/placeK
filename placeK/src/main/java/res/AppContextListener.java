package res;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import controller.LocationController;
import controller.SeatController;
import controller.UserController;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String uri = "mongodb+srv://judyzero11:bCNfbX9LUUQGY5sH@ooad.ng7ao58.mongodb.net/";
        MongoClient mongoClient = MongoClients.create(uri);

        // MongoDB에 연결되었는지 확인
        try {
            MongoDatabase database = mongoClient.getDatabase("OOAD");
            System.out.println("Connected to MongoDB!");
        } catch (Exception e) {
            System.err.println("Failed to connect to MongoDB: " + e.getMessage());
        }

        UserController userController  = new UserController(mongoClient);
        LocationController locationController = new LocationController(mongoClient);
        SeatController seatController = new SeatController(mongoClient);


        // 컨트롤러를 서블릿 컨텍스트에 저장
        sce.getServletContext().setAttribute("userController", userController);
        sce.getServletContext().setAttribute("locationController", locationController);
        sce.getServletContext().setAttribute("seatController", seatController);

        System.out.println("MongoDB connection and controllers initialized.");
    }


}
