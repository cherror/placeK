package controller;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import model.User;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UserController {
    private MongoClient mongoClient;

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

    //유저 로그인
    public Map<String, Object> loginUser(int userID, String password) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");
        Document query = new Document("userID", userID);
        Document userDoc = collection.find(query).first();
        Map<String, Object> result = new HashMap<>();
        //해당 ID가 존재하지 않는 경우
        if(userDoc == null){
            result.put("status", false);
            result.put("message", "해당 ID를 찾을 수 없습니다.");
        } else {
            String storedPassword = userDoc.getString("password");
            if(storedPassword.equals(password)) {
                result.put("status", true);
                result.put("userID", userID);
                result.put("message", "로그인 성공");
            } else {
                result.put("status", false);
                result.put("message", "잘못된 비밀번호입니다.");
            }
        }
        return result;
    }

    public String getUserMajor(int userID) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");
        Document query = new Document("userID", userID);
        Document userDoc = collection.find(query).first();

        return userDoc.getString("major");
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

            return new User(id, password, major, isRented, locationID, seatNum, rentedTime);
        } else {
            return null;
        }

    }

    //유저 좌석 대여 정보 업데이트
    public void updateUserSeatInfo(int userID, boolean isRented, int locationID, String seatNum, String rentedTime) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");

        Document filter = new Document("userID", userID);
        Document update = new Document("$set", new Document("isRented", isRented)
                .append("locationID", locationID)
                .append("seatNum", seatNum)
                .append("rentedTime", rentedTime));
        collection.updateOne(filter, update);

        //2시간 뒤 자동 반납
//        if (isRented) {
//            LocalTime twoHoursLater = now.plusHours(2);
//            String formattedTwoHoursLater = twoHoursLater.format(formatter);
//            String twoHoursLaterStr = twoHoursLater.format(formatter);
//            if (twoHoursLater.isBefore(LocalTime.now())) {
//                Document updatedInfo = new Document("isRented", false);
//                update = new Document("$set", updatedInfo);
//                collection.updateOne(filter, update);
//
//                System.out.println("유저의 대여 시간이 만료되어 자동으로 반납되었습니다.");
//            }
//        }
    }
}
