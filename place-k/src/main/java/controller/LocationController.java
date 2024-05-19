package controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Location;
import org.bson.Document;

public class LocationController {
    private MongoClient mongoClient;

    public LocationController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public String getMajorList(int locationID){
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("locations");

        Document query = new Document("locationID", locationID);
        Document result = collection.find(query).first();

        if (result != null) {
            return result.getString("availableMajors");
        } else {
            return null;
        }
    }

    public Location getLocationInfo(int locationID){
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("locations");
        Document query = new Document("locationID", locationID);
        Document result = collection.find(query).first();
        if (result != null) {
            String locationName = result.getString("locationName");
            String availableMajors = result.getString("availableMajors");
            int seatCount = result.getInteger("seatCount");
            return new Location(locationID, locationName, seatCount, availableMajors);
        }
        return null;
    }

    public void setLocation(int locationID) {
        MongoDatabase db = mongoClient.getDatabase("OOAD");
        MongoCollection<Document> collection = db.getCollection("locations");

        if(locationID == 1) {
            Document document = new Document("locationID", 1)
                    .append("locationName", "미래관-자율주행스튜디오")
                    .append("seatCount", 40)
                    .append("availableMajors", "software");
            collection.insertOne(document);
            System.out.println("미래관 자율주행스튜디오 생성");
        } else if(locationID == 2) {
            Document document = new Document("locationID", 2)
                    .append("locationName", "미래관-무한상상실")
                    .append("seatCount", 30)
                    .append("availableMajors", "software");
            collection.insertOne(document);
            System.out.println("미래관 무한상상실 생성");
        } else if(locationID == 3) {
            Document document = new Document("locationID", 3)
                    .append("locationName", "미래관-K Smart Lounge")
                    .append("seatCount", 24)
                    .append("availableMajors", "software");
            collection.insertOne(document);
            System.out.println("미래관 K-Smart Lounge 생성");
        } else if(locationID == 4) {
            Document document = new Document("locationID", 4)
                    .append("locationName", "경영관-스터디카페")
                    .append("seatCount", 24)
                    .append("availableMajors", "business");
            collection.insertOne(document);
            System.out.println("경영관 스터디카페 생성");
        } else if(locationID == 5) {
            Document document = new Document("locationID", 5)
                    .append("locationName", "법학-스터디카페")
                    .append("seatCount", 29)
                    .append("availableMajors", "law");
            collection.insertOne(document);
            System.out.println("법학관 스터디카페 생성");
        }

    }

}
