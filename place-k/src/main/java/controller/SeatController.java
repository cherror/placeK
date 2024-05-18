package controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class SeatController {
    private MongoClient mongoClient;

    public SeatController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    //좌석 정보 가져오기
    public void getSeat(int locationID, int seatNum, int userID){
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection(String.valueOf(locationID));

    }

    //좌석 상태 업데이트하기
    public void updateSeatStatus(int locationID, int seatNum, boolean isRented){
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection(String.valueOf(locationID));
        Document filter = new Document("seatNum", seatNum);
        Document update = new Document("$set", new Document("isRented", isRented));
        collection.updateOne(filter, update);
    }

    //좌석 대여 시간 업데이트하기
    public void updateRentedTime(int locationID, int seatNum){
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection(String.valueOf(locationID));
        Document filter = new Document("seatNum", seatNum);
        //rentTime을 현재 시간으로 변경해야 함
        Document update = new Document("$set", new Document("rentTime", "aaa"));
        collection.updateOne(filter, update);
    }

    //이용 가능한 좌석 가져오기
//    public void getAvailableSeats(int locationID){
//        MongoDatabase db = mongoClient.getDatabase("OOAD");
//        MongoCollection<Document> collection = db.getCollection(String.valueOf(locationID));
//
//    }


}
