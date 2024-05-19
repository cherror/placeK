package model;

import java.util.Date;

public class Seat {
    public String seatNum;
    public int locationID;
    public boolean isRented;
    public String rentTime;
    public String rentedBy;

    public Seat(int locationID, String seatNum, boolean isRented, String rentTime, String rentedBy) {
        this.seatNum = seatNum;
        this.locationID = locationID;
        this.isRented = isRented;
        this.rentTime = rentTime;
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

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentedBy(String rentedBy) {
        this.rentedBy = rentedBy;
    }

    public String getRentedBy() {
        return rentedBy;
    }

}
