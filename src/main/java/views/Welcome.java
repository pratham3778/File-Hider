package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the Application");
        System.out.println("Press 1 to Login");
        System.out.println("Press 2 to Sign-up");
        System.out.println("Press 0 to Exit");

        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        switch (choice) {
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }


    private void login() {
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();
        try {
            if (UserDAO.isExists(email)) {
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("Enter the OTP: ");
                String otp = sc.nextLine();
                if (otp.equals(genOTP)){
                    System.out.println("Welcome");
                } else {
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User Not Found ");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String name = sc.nextLine();
        System.out.println("Enter E-mail: ");
        String email = sc.nextLine();

        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.println("Enter the OTP: ");
        String otp = sc.nextLine();
        if (otp.equals(genOTP)){
            User user = new User(name, email);
            int response = UserService.saveUser(user);


            
            //you left here
        } else {
            System.out.println("Wrong OTP");
        }

    }
}

