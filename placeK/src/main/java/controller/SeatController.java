package controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Seat;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatController {
    private MongoClient mongoClient;

    public SeatController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    //해당 장소의 모든 좌석 정보 가져오기
    public List<Seat> getSeatsInfo(int locationID){
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> seatCollection = db.getCollection("seats");

        // 모든 좌석 정보 가져오기
        Document location = new Document("locationID", locationID);
        FindIterable<Document> seats = seatCollection.find(location);

        List<Seat> seatList = new ArrayList<>();
        for (Document seatDoc : seats) {
            Seat seat = new Seat(
                    seatDoc.getInteger("locationID"),
                    seatDoc.getString("seatNum"),
                    seatDoc.getBoolean("isRented"),
                    seatDoc.getString("rentedTime"),
                    seatDoc.getString("rentedBy"),
                    seatDoc.getString("returnTime")
            );
            seatList.add(seat);
        }
        return seatList;
    }

    //좌석 한 개의 정보 가져오기
    public Seat getSeat(int locationID, String seatNum) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> userCollection = db.getCollection("seats");
        Document filter = new Document("locationID", locationID).append("seatNum", seatNum);
        Document seatDoc = userCollection.find(filter).first();

        if (seatDoc != null) {
            int id = seatDoc.getInteger("locationID");
            String seatNumber = seatDoc.getString("seatNum");
            boolean isRented = seatDoc.getBoolean("isRented");

            Seat seat = new Seat(
                    id, seatNumber, isRented, null, null, null
            );

            return seat;
        } else {
            System.out.println("좌석 정보 없음");
        }
        return null;
    }

    //좌석 상태 업데이트하기
    public void updateSeatStatus(int locationID, String seatNum, boolean isRented, String rentedTime, String returnTime){
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("seats");
        Document filter = new Document("locationID", locationID).append("seatNum", seatNum);
        Document update = new Document("$set", new Document("isRented", isRented)
                .append("rentedTime", isRented? rentedTime: null)
                .append("returnTime", isRented? returnTime: null)
        );
        collection.updateOne(filter, update);
    }
}
