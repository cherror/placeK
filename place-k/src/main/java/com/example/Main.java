package com.example;

import com.mongodb.client.*;
import controller.UserController;
import model.User;
import window.signup;
//import static spark.Spark.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("HTML Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);

            try {
                // HTML 파일 읽어오기
                File htmlFile = new File("src/main/java/window/signup.html");
                editorPane.setPage(htmlFile.toURI().toURL());
            } catch (IOException e) {
                e.printStackTrace();
            }

            JScrollPane scrollPane = new JScrollPane(editorPane);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }


//        UserController userController  = new UserController(mongoClient);
//        User user = new User(2021, "abcd", "software", false);
//        userController.createUser(user);
//        mongoClient.close();


}