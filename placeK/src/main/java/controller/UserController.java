package controller;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import model.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class UserController {
    private MongoClient mongoClient;
    private Map<Integer, HttpSession> userSessions = new ConcurrentHashMap<>();

    public UserController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    //유저 생성
    public boolean createUser(User user) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");

        // userID가 이미 존재하는지 확인
        Document existingUser = collection.find(new Document("userID", user.getID())).first();
        if (existingUser != null) {
            System.out.println("해당 ID가 존재합니다.");
            return false; // 같은 userID가 있으면 생성하지 않고 종료
        }

        Document document = new Document("userID", user.getID())
                .append("password", user.getPassword())
                .append("major", user.getMajor())
                .append("isRented", user.isRented())
                .append("locationID", user.getLocationID())
                .append("seatNum", user.getSeatNum())
                .append("rentedTime", user.getRentedTime());
        collection.insertOne(document);
        return true;
    }

    public User getUserInfo(int userID) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");
        Document query = new Document("userID", userID);
        Document userDoc = collection.find(query).first();
        if (userDoc != null) {
            int id = userDoc.getInteger("userID");
            String password = userDoc.getString("password");
            String major = userDoc.getString("major");
            boolean isRented = userDoc.getBoolean("isRented");
            int locationID = userDoc.getInteger("locationID", -1);
            String seatNum = userDoc.getString("seatNum");
            String rentedTime = userDoc.getString("rentedTime");
            String returnTime = userDoc.getString("returnTime");
            return new User(id, password, major, isRented, locationID, seatNum, rentedTime, returnTime);
        } else {
            return null;
        }

    }

    //유저 좌석 대여 정보 업데이트
    public void updateUserSeatInfo(int userID, boolean isRented, int locationID, String seatNum, String rentedTime, String returnTime) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");

        Document filter = new Document("userID", userID);
        Document update = new Document("$set", new Document("isRented", isRented)
                .append("locationID", locationID)
                .append("seatNum", seatNum)
                .append("rentedTime", rentedTime)
                .append("returnTime", returnTime)
        );
        collection.updateOne(filter, update);
    }

    // 대여 중인 좌석이 있는 모든 사용자 조회
    public List<User> getAllUsersWithRentedSeats() {
        List<User> usersWithRentedSeats = new ArrayList<>();
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");

        for (Document doc : collection.find(Filters.and(
                Filters.exists("rentedTime"),
                Filters.exists("returnTime"),
                Filters.eq("isRented", true)))) {
            User user = new User(
                    doc.getInteger("userID"),
                    doc.getString("password"),
                    doc.getString("major"),
                    doc.getBoolean("isRented"),
                    doc.getInteger("locationID"),
                    doc.getString("seatNum"),
                    doc.getString("rentedTime"),
                    doc.getString("returnTime")
            );
            usersWithRentedSeats.add(user);
        }
        return usersWithRentedSeats;
    }

    public void addUserSession(int userID, HttpSession session) {
        userSessions.put(userID, session);
    }

    public HttpSession getUserSession(int userID) {
        return userSessions.get(userID);
    }
}
