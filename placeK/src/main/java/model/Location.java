package model;

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

    public String getLocationName(){
        return locationName;
    }

    public String getAvailableMajors(){
        return availableMajors;
    }


}
