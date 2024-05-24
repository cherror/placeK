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
//        MongoCollection<Document> locationCollection = db.getCollection("locations");

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
                    seatDoc.getString("rentedBy")
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
                    id, seatNumber, isRented, null, null
            );

            return seat;
//            List<String> locationList = Arrays.asList("미래관-자율주행스튜디오", "미래관-무한상상실", "미래관-K Smart Lounge", "경영관-스터디카페", "법학관-스터디카페");
//
//            System.out.println("Location Name: " + locationList.get(id - 1));
//            System.out.println("Seat Number: " + seatNumber.substring(4));
//            if (isRented) {
//                System.out.println("사용 불가");
//            } else {
//                System.out.println("사용 가능");
//            }
//            System.out.println("좌석 정보: " + seat.toJson()); //좌석 정보: {"_id": {"$oid": "6648ce1dda641d51482e96f4"}, "locationID": 1, "seatNum": "seat5", "isRented": false, "rentedTime": null, "rentedBy": null}
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

    //좌석 정보 생성
    public void setSeatInfo(int locationID){
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> seatCollection = db.getCollection("seats");
        MongoCollection<Document> locationCollection = db.getCollection("locations");

        Document location = new Document("locationID", locationID);
        Document locationDoc = locationCollection.find(location).first();
        int seatCount = locationDoc.getInteger("seatCount");

        for (int seatNum = 1; seatNum <= seatCount; seatNum++) {
            Document document = new Document("locationID", locationID)
                    .append("seatNum", "seat"+seatNum)
                    .append("isRented", false)
                    .append("rentedTime", null)
                    .append("rentedBy", null);
            seatCollection.insertOne(document);
        }

    }

    //좌석 대여 시간 업데이트하기
//    public void updateRentedTime(int locationID, int seatNum){
//        MongoDatabase db = mongoClient.getDatabase("OOAD");
//        MongoCollection<Document> collection = db.getCollection(String.valueOf(locationID));
//        Document filter = new Document("seatNum", seatNum);
//        //rentTime을 현재 시간으로 변경해야 함
//        Document update = new Document("$set", new Document("rentTime", "aaa"));
//        collection.updateOne(filter, update);
//    }


    //이용 가능한 좌석 가져오기
//    public void getAvailableSeats(int locationID){
//        MongoDatabase db = mongoClient.getDatabase("OOAD");
//        MongoCollection<Document> collection = db.getCollection(String.valueOf(locationID));
//
//    }


}
