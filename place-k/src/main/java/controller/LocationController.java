package controller;

import com.mongodb.client.MongoClient;

import java.util.ArrayList;
import java.util.List;

public class LocationController {
    private MongoClient mongoClient;

    public LocationController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

//    public List<String> getLocationMajors(){
//        List<String> majors = new ArrayList<>();
//    }
}
