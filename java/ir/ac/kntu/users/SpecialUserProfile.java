package ir.ac.kntu.users;

import ir.ac.kntu.Data;

import java.util.ArrayList;
import java.util.Scanner;

public class SpecialUserProfile extends ProfileGenerality {
    public SpecialUserProfile(String username, String password, String phoneNumber, String email) {
        super(username, password, phoneNumber, email);
    }

    public void viewProfile(Data data) {
        System.out.println("username: " + getUsername());
        System.out.println("password: " + getPassword());
        System.out.println("phoneNumber: " + getPhoneNumber());
        System.out.println("email: " + getEmail());
    }

    public void editProfile(Data data, ArrayList<String> usernames) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--edit profile--");
        System.out.println("enter current username or a new username:");
        String newUsername = sc.nextLine();
        boolean repetitiveUser = false;
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(newUsername)
                    && newUsername.equals(getUsername()) == false) {
                System.out.println("USERNAME ALREADY EXISTS!!");
                repetitiveUser = true;
                editProfile(data, usernames);
            }
        }
        if (repetitiveUser == false) {
            System.out.println("enter current password or a new password:");
            String newPass = sc.nextLine();
            if (newPass.length() < 8) {
                System.out.println("PASSWORD MUST INCLUDE AT LEAST 8 LETTERS!!");
                editProfile(data, usernames);
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
