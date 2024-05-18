package model;

import java.util.Date;

public class Seat {
    public int seatNum;
    public int locationID;
    public boolean isRented;
    public Date rentTime;
    public int rentedBy;

    public Seat(int seatNum, int locationID, boolean isRented, Date rentTime, int rentedBy) {
        this.seatNum = seatNum;
        this.locationID = locationID;
        this.isRented = isRented;
        this.rentTime = rentTime;
        this.rentedBy = rentedBy;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getSeatNum() {
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

    public void setRentTime(Date rentTime) {
        this.rentTime = rentTime;
    }

    public Date getRentTime() {
        return rentTime;
    }

    public void setRentedBy(int rentedBy) {
        this.rentedBy = rentedBy;
    }

    public int getRentedBy() {
        return rentedBy;
    }

}
