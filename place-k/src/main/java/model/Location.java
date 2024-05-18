package model;

import java.util.List;

public class Location {
    public int locationID;
    public String locationName;
    public int seatCount;
    public List<String> availableMajors;

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

    public void setAvailableMajors(List<String> majors){
        this.availableMajors = majors;
    }

    public List<String> getAvailableMajors(){
        return availableMajors;
    }


}
