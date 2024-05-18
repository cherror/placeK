package controller;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import model.User;

public class UserController {
    private MongoClient mongoClient;

    public UserController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    //유저 생성
    public void createUser(User user) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");
        Document document = new Document("userID", user.getID())
                                .append("password", user.getPassword())
                                .append("major", user.getMajor())
                                .append("isRented", user.isRented());
        collection.insertOne(document);
    }

    //유저 로그인
    public void loginUser(int userID, String password) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");
        Document document = new Document("userID", userID);
        if(document == null) {
            System.out.println("User not found");
            return;
        }

        String storedPassword = document.getString("password");
        if(storedPassword.equals(password)) {
            System.out.println("User logged in");
        } else {
            System.out.println("Wrong password");
        }
    }

    //유저 좌석 대여 정보 업데이트
    public void updateUser(int userID, boolean isRented) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("users");
        Document filter = new Document("userID", userID);
        Document update = new Document("$set", new Document("isRented", isRented));
        collection.updateOne(filter, update);
    }
}
