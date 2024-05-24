package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Seat {
    public String seatNum;
    public int locationID;
    public boolean isRented;
    public String rentedTime;
    public String rentedBy;

    public Seat(int locationID, String seatNum, boolean isRented, String rentedTime, String rentedBy) {
        this.seatNum = seatNum;
        this.locationID = locationID;
        this.isRented = isRented;
        this.rentedTime = rentedTime;
        this.rentedBy = rentedBy;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setIsRented(boolean isRented) {
        this.isRented = isRented;
    }

    public boolean getIsRented() {
        return isRented;
    }

    public void setRentTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
        this.rentedTime = now.format(formatter);
    }

    public String getRentTime() {
        return rentedTime;
    }

    public void setRentedBy(String rentedBy) {
        this.rentedBy = rentedBy;
    }

    public String getRentedBy() {
        return rentedBy;
    }

    public String getSeatNumber() {
        return seatNum.replaceAll("\\D+", "");
    }

}
