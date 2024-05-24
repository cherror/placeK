package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class User {
    private int userId;
    private String password;
    private String major;
    private boolean isRented;
    private int locationID;
    private String seatNum;
    private String rentedTime;
    private String returnTime;

    public User(int userId, String password, String major, boolean isRented, int locationID, String seatNum, String rentedTime, String returnTime) {
        this.userId = userId;
        this.password = password;
        this.major = major;
        this.isRented = isRented;
        this.locationID = locationID;
        this.seatNum = seatNum;
        this.rentedTime = rentedTime;
        this.returnTime = returnTime;
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

    public void setRentedTime(String rentedTime) {
        this.rentedTime = rentedTime;
    }

    public String getRentedTime() {
        return rentedTime;
    }

    public void setReturnTime() {
        LocalTime now = LocalTime.now();
        LocalTime later = now.plusHours(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
        this.returnTime = later.format(formatter);
    }

    public void setExtendTime(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
            LocalTime currentReturnTime = LocalTime.parse(returnTime, formatter);
            LocalTime extendedTime = currentReturnTime.plusHours(2);
            this.returnTime = extendedTime.format(formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            System.out.println("반납 시간 형식이 올바르지 않습니다.");
        }
    }

    public String getReturnTime() {
        return returnTime;
    }

    public String getSeatNumber() {
        return seatNum.replaceAll("\\D+", "");
    }

    public long getRemainingMinutes() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
            LocalTime currentTime = LocalTime.now();
            LocalTime returnTime = LocalTime.parse(this.returnTime, formatter);
            return ChronoUnit.MINUTES.between(currentTime, returnTime);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("시간 형식이 올바르지 않습니다.");
            return -1;
        }
    }
}
