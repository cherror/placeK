package model;

public class User {
    private int id;
    private String password;
    private String major;
    private boolean isRented;

    public User(int id, String password, String major, boolean isRented) {
        this.id = id;
        this.password = password;
        this.major = major;
        this.isRented = isRented;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
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

}
