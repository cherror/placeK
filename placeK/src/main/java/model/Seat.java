package model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Seat {
    public String seatNum;
    public int locationID;
    public boolean isRented;
    public String rentedTime;
    public String rentedBy;
    public String returnTime;

    public Seat(int locationID, String seatNum, boolean isRented, String rentedTime, String rentedBy, String returnTime) {
        this.seatNum = seatNum;
        this.locationID = locationID;
        this.isRented = isRented;
        this.rentedTime = rentedTime;
        this.rentedBy = rentedBy;
        this.returnTime = returnTime;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.rentedTime = now.format(formatter);
    }

    public String getRentTime() {
        return rentedTime;
    }

    public void setReturnTime() {
        LocalTime now = LocalTime.now();
        //테스트 코드 - 반납시간 1분 뒤로 설정
        LocalTime later = now.plusMinutes(1);
        //LocalTime later = now.plusHours(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.returnTime = later.format(formatter);
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

    public String getRemainingMinutes() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime currentTime = LocalTime.now();
            LocalTime returnTime = LocalTime.parse(this.returnTime, formatter);
            long minutesDiff = ChronoUnit.MINUTES.between(currentTime, returnTime);

            Duration duration = Duration.ofMinutes(minutesDiff);
            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();

            return String.format("%02d:%02d", hours, Math.abs(minutes));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("시간 형식이 올바르지 않습니다.");
            return null;
        }
    }

}
