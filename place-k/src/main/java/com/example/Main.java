package com.example;

import com.mongodb.client.*;
import controller.LocationController;
import controller.SeatController;
import controller.UserController;
import model.Location;
import model.Seat;
import model.User;
import window.signup;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String uri = "mongodb+srv://judyzero11:bCNfbX9LUUQGY5sH@ooad.ng7ao58.mongodb.net/";
        MongoClient mongoClient = MongoClients.create(uri);

        // MongoDB에 연결되었는지 확인
        try {
            MongoDatabase database = mongoClient.getDatabase("OOAD");
            System.out.println("Connected to MongoDB!");
        } catch (Exception e) {
            System.err.println("Failed to connect to MongoDB: " + e.getMessage());
        }

        UserController userController  = new UserController(mongoClient);
        LocationController locationController = new LocationController(mongoClient);
        SeatController seatController = new SeatController(mongoClient);

        //회원가입 이벤트 리스너
//        User user = new User(1000, "abcd", "software", false, -1, null, null);
//        boolean isCreated = userController.createUser(user);
//
//        if (isCreated) {
//            System.out.println("회원가입 성공");
//        } else {
//            System.out.println("회원가입 실패");
//        }
//
//        mongoClient.close();

//        //로그인 이벤트 리스너
//        Map<String, Object> loginResult = userController.loginUser(1000, "abcd");
//        System.out.println(loginResult.get("message"));
//
//        if ((Boolean) loginResult.get("status")) {
//            int loggedInUserID = (int) loginResult.get("userID");
//            System.out.println("Logged in User ID: " + loggedInUserID);
//        }
//        mongoClient.close();

        //장소 별 데이터 입력
//        locationController.setLocation(1);
//        locationController.setLocation(2);
//        locationController.setLocation(3);
//        locationController.setLocation(4);
//        locationController.setLocation(5);

//      //장소 별 좌석 데이터 입력
//        seatController.setSeatInfo(1);
//        seatController.setSeatInfo(2);
//        seatController.setSeatInfo(3);
//        seatController.setSeatInfo(4);
//        seatController.setSeatInfo(5);


//        해당 장소의 좌석 정보(빌린시간, 좌석번호) 띄우기
//        List<Seat> seatList = seatController.getSeatsInfo(1);
//        for (Seat seat : seatList) {
//            System.out.println("LocationID: " + seat.getLocationID());
//            System.out.println("Seat Number: " + seat.getSeatNum());
//            System.out.println("Is Rented: " + seat.getIsRented());
//            System.out.println("Rented Time: " + seat.getRentTime());
//            System.out.println("Rented By: " + seat.getRentedBy());
//            System.out.println("--------------------");
//        }

        //선택 좌석 정보 띄우기
//        Location locationInfo = locationController.getLocationInfo(1);
//        Seat seatInfo = seatController.getSeat(1, "seat5");
//
//        System.out.println(locationInfo.getLocationName());
//        System.out.println(seatInfo.getSeatNum());
//        System.out.println(locationInfo.getAvailableMajors());
//        System.out.println(seatInfo.getIsRented());

        //좌석 신청하기 - 좌석 신청 가능한 경우만 클릭 되도록
//        1. 대여 장소의 전공 리스트에 해당 유저의 전공이 포함되어있는지 확인
//        Location locationInfo = locationController.getLocationInfo(1);
//        String availableMajor = locationInfo.getAvailableMajors();
//        if(locationInfo.getAvailableMajors() != null) {
//            String userMajor = userController.getUserMajor(1000);
//            if(availableMajor.contains(userMajor)) {
////                2. userDB에 해당 좌석의 정보 저장
//                userController.updateUserSeatInfo(1000,  true,1, "seat1");
////                3. seatDB에 좌석 업데이트
//                 seatController.updateSeatStatus(1, "seat1", true);
//                System.out.println("좌석 대여 성공");
//            } else {
//                System.out.println("좌석 대여 실패 : 해당하는 전공이 아닙니다.");
//            }
//        } else {
//            System.out.println("좌석 대여 실패");
//        }


        //좌석 반납하기
//        seatController.updateSeatStatus(1, "seat5", false);

        //배정 좌석 확인하기
//        학번, 학과, 좌석대여여부, 좌석대여시 -> 장소, 좌석번호, 대여시간, 남은시간
//        그러면 이 모든 정보가 유저한테 저장이 되어 있어야 했던거 아닌가 ...? ㄷㅈ러ㅐㅑㅈ더ㅑㅐ
//        User userInfo = userController.getUserInfo(1000);
//        if (userInfo != null) {
//            System.out.println("User ID: " + userInfo.getID());
//            System.out.println("Major: " + userInfo.getMajor());
//            if (userInfo.isRented()) {
//                System.out.println("Location ID: " + userInfo.getLocationID());
//                System.out.println("Seat Number: " + userInfo.getSeatNum());
//                System.out.println("Rented Time: " + userInfo.getRentedTime());
//            } else {
//                System.out.println("No seat rented.");
//            }
//        } else {
//            System.out.println("User not found.");
//        }

        //화면 렌더링 예시
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("HTML Viewer");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(600, 400);
//
//            JEditorPane editorPane = new JEditorPane();
//            editorPane.setEditable(false);
//
//            try {
//                // HTML 파일 읽어오기
//                File htmlFile = new File("src/main/java/window/signup.html");
//                editorPane.setPage(htmlFile.toURI().toURL());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            JScrollPane scrollPane = new JScrollPane(editorPane);
//            frame.add(scrollPane, BorderLayout.CENTER);
//
//            frame.setVisible(true);
//        });
    }




}