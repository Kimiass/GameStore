package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.*;

import java.util.Scanner;

public class UserProfile extends ProfileGenerality {

    private double wallet;

    private double score;

    public UserProfile(String username, String password, String phoneNumber, String email,
                       double wallet, double score) {
        super(username, password, phoneNumber, email);
        this.wallet = wallet;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public double addMoney(double addedNumber) {
        wallet = wallet + addedNumber;
        return wallet;
    }

    public double spentMoneyOnGame(Game game, User currentUser) {
        if (currentUser.getUserProfile().score < 20) {
            wallet = wallet - game.getCost();
        } else if (currentUser.getUserProfile().score >= 20 && currentUser.getUserProfile().score < 50) {
            wallet = wallet - game.getCost() * 0.9;
        } else if (currentUser.getUserProfile().score >= 50 && currentUser.getUserProfile().score < 100) {
            wallet = wallet - game.getCost() * 0.8;
        } else if (currentUser.getUserProfile().score >= 100) {
            wallet = wallet - game.getCost() * 0.7;
        }
        System.out.println(game.getName() + " WAS ADDED SUCCESSFULLY!");
        return wallet;
    }

    public double spentMoneyOnAccessory(GameAccessories gameAccessories, User currentUser) {
        wallet = wallet - gameAccessories.getCost();
        System.out.println(gameAccessories.getName() + " WAS ADDED SUCCESSFULLY!");
        return wallet;
    }

    public void viewProfile(Data data) {
        System.out.println("username: " + getUsername());
        System.out.println("password: " + getPassword());
        System.out.println("phoneNumber: " + getPhoneNumber());
        System.out.println("email: " + getEmail());
        System.out.println("wallet: " + wallet);
    }

    public void editProfile(Data data) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--edit profile--");
        System.out.println("enter current username or a new username:");
        String newUsername = sc.nextLine();
        boolean repetitiveUser = false;
        for (int i = 0; i < data.getAllUsers().size(); i++) {
            if (data.getAllUsers().get(i).getUserProfile().getUsername().equals(newUsername)
                    && newUsername.equals(getUsername()) == false) {
                System.out.println("USERNAME ALREADY EXISTS!!");
                repetitiveUser = true;
                editProfile(data);
            }
        }
        if (repetitiveUser == false) {
            System.out.println("enter current password or a new password:");
            String newPass = sc.nextLine();
            if (newPass.length() < 8) {
                System.out.println("PASSWORD MUST INCLUDE AT LEAST 8 LETTERS!!");
                editProfile(data);
            } else if (!newPass.matches(".*" + "[A-Z]+" + ".*") ||
                    !newPass.matches(".*" + "[a-z]+" + ".*") ||
                    !newPass.matches(".*" + "[0-9]+" + ".*")) {
                System.out.println("PASSWORD MUST INCLUDE NUMBERS, UPPER AND LOWERCASE LETTERS!");
            } else {
                System.out.println("enter current phone number or a new phone number:");
                String newPhoneNum = sc.nextLine();
                System.out.println("enter current email or a new email:");
                String newEmail = sc.nextLine();
                setUsername(newUsername);
                setPassword(newPass);
                setPhoneNumber(newPhoneNum);
                setEmail(newEmail);
                System.out.println("PROFILE EDITED SUCCESSFULLY!");
            }
        }
    }
}
