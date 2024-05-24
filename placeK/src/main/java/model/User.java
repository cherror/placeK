package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class User {
    private int userId;
    private String password;
    private String major;
    private boolean isRented;
    private int locationID;
    private String seatNum;
    private String rentedTime;

    public User(int userId, String password, String major, boolean isRented, int locationID, String seatNum, String rentedTime) {
        this.userId = userId;
        this.password = password;
        this.major = major;
        this.isRented = isRented;
        this.locationID = locationID;
        this.seatNum = seatNum;
        this.rentedTime = rentedTime;
    }

    public void setID(int id) {
        this.userId = id;
    }

    public int getID() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setLocationID(int locationID) {this.locationID = locationID; }

    public int getLocationID() { return locationID; }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setRentedTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
        this.rentedTime = now.format(formatter);
    }

    public String getRentedTime() {
        return rentedTime;
    }

    public String getSeatNumber() {
        return seatNum.replaceAll("\\D+", "");
    }
}
