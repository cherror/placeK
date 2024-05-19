package model;

import java.util.List;

public class Location {
    public int locationID;
    public String locationName;
    public int seatCount;
    public String availableMajors;

    public Location(int locationID, String locationName, int seatCount, String availableMajors) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.seatCount = seatCount;
        this.availableMajors = availableMajors;
    }

    public int getLocationID(){
        return locationID;
    }

    public void setLocationName(String name){
        this.locationName = name;
    }

    public String getLocationName(){
        return locationName;
    }

    public void setSeatCount(int count){
        this.seatCount = count;
    }

    public int getSeatCount(){
        return seatCount;
    }

    public void setAvailableMajors(String majors){
        this.availableMajors = majors;
    }

    public String getAvailableMajors(){
        return availableMajors;
    }


}
