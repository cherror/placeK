package res;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String connectionString = "mongodb+srv://judyzero11:bCNfbX9LUUQGY5sH@ooad.ng7ao58.mongodb.net/";  // MongoDB 연결 문자열
        String dbName = "OOAD";  // 사용할 데이터베이스 이름

        MongoDBConnectionManager.initialize(connectionString, dbName);
        System.out.println("MongoDB connection initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MongoDBConnectionManager.close();
        System.out.println("MongoDB connection closed.");
    }
}
