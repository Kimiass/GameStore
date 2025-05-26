package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class AdminUserController {
    public AdminUserController() {
    }

    public void viewActiveUsers(Data currentData, Admin currentAdmin) {
        Collections.sort(currentData.getAllUsers());
        for (int i = 0; i < currentData.getAllUsers().size(); i++) {
            System.out.println(currentData.getAllUsers().get(i).getUserProfile().getUsername());
        }
        System.out.println("1.back to admin menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        currentAdmin.adminMenu(currentData, currentAdmin);
    }

    public void viewUserInfo(Data currentData, Admin currentAdmin) {
        System.out.println("--view user info--");
        System.out.println("choose a number:");
        System.out.println("1.search user by username");
        System.out.println("2.search user by phone number");
        System.out.println("3.search user by email");
        System.out.println("4.back to admin users");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            viewInfoByUsername(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 2) {
            viewInfoByPhoneNumber(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 3) {
            viewInfoByEmail(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 4) {
            currentAdmin.adminUsers(currentData, currentAdmin);
        }
    }

    public void viewInfoByUsername(Data data) {
        System.out.println("enter username:");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < data.getAllUsers().size(); i++) {
            if (data.getAllUsers().get(i).getUserProfile().getUsername().equals(username)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("USERNAME DOESN'T EXIST!");
        } else {
            data.getAllUsers().get(indexOfSelectedUser).getUserProfile().viewProfile(data);
        }
    }

    public void viewInfoByPhoneNumber(Data data) {
        System.out.println("enter phone number:");
        Scanner sc = new Scanner(System.in);
        String phoneNum = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < data.getAllUsers().size(); i++) {
            if (data.getAllUsers().get(i).getUserProfile().getPhoneNumber().equals(phoneNum)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("PHONE NUMBER DOESN'T EXIST!");
        } else {
            data.getAllUsers().get(indexOfSelectedUser).getUserProfile().viewProfile(data);
        }
    }

    public void viewInfoByEmail(Data data) {
        System.out.println("enter email:");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < data.getAllUsers().size(); i++) {
            if (data.getAllUsers().get(i).getUserProfile().getEmail().equals(email)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("EMAIL DOESN'T EXIST!");
        } else {
            data.getAllUsers().get(indexOfSelectedUser).getUserProfile().viewProfile(data);
        }
    }

    public void adminAddsUsers(Data currrentData, Admin currentAdmin) {
        System.out.println("--add a user--");
        Scanner sc = new Scanner(System.in);
        System.out.println("enter username:");
        String username = sc.nextLine();
        boolean usernameExists = false;
        for (int i = 0; i < currrentData.getAllUsers().size(); i++) {
            if (currrentData.getAllUsers().get(i).getUserProfile().getUsername().equals(username)) {
                usernameExists = true;
                break;
            }
        }
        if (usernameExists == false) {
            System.out.println("enter password:");
            String password = sc.nextLine();
            System.out.println("enter phone number:");
            String phoneNum = sc.nextLine();
            System.out.println("enter email:");
            String email = sc.nextLine();
            UserProfile newUserProfile = new UserProfile(username, password, phoneNum, email, 0, 0);
            ArrayList<Game> newUserGames = new ArrayList<>();
            ArrayList<User> newUserFriends = new ArrayList<>();
            ArrayList<User> newUserRequests = new ArrayList<>();
            User createdUser = new User(newUserProfile, newUserGames, new HashMap<>(), new HashMap<>(),
                    new UserGameShop(), newUserFriends, newUserRequests, new UserConnection(), new UserViews());
            currrentData.getAllUsers().add(createdUser);
            System.out.println("USER WAS ADDED SUCCESSFULLY!");
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currrentData, currentAdmin);
        } else {
            System.out.println("USERNAME ALREADY EXISTS!");
            currentAdmin.adminUsers(currrentData, currentAdmin);
        }
    }

    public void adminEditsUsers(Data currentData, Admin currentAdmin) {
        System.out.println("--view user info--");
        System.out.println("choose a number:");
        System.out.println("1.search user by username");
        System.out.println("2.search user by phone number");
        System.out.println("3.search user by email");
        System.out.println("4.back to admin users");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            editInfoByUsername(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 2) {
            editInfoByPhoneNumber(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 3) {
            editInfoByEmail(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 4) {
            currentAdmin.adminUsers(currentData, currentAdmin);
        }
    }

    public void editInfoByUsername(Data currentData) {
        System.out.println("enter username:");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < currentData.getAllUsers().size(); i++) {
            if (currentData.getAllUsers().get(i).getUserProfile().getUsername().equals(username)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("USERNAME DOESN'T EXIST!");
        } else {
            currentData.getAllUsers().get(indexOfSelectedUser).getUserProfile().editProfile(currentData);
        }
    }

    public void editInfoByPhoneNumber(Data currentData) {
        System.out.println("enter phone number:");
        Scanner sc = new Scanner(System.in);
        String phoneNum = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < currentData.getAllUsers().size(); i++) {
            if (currentData.getAllUsers().get(i).getUserProfile().getPhoneNumber().equals(phoneNum)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("PHONE NUMBER DOESN'T EXIST!");
        } else {
            currentData.getAllUsers().get(indexOfSelectedUser).getUserProfile().editProfile(currentData);
        }
    }

    public void editInfoByEmail(Data currentData) {
        System.out.println("enter email:");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < currentData.getAllUsers().size(); i++) {
            if (currentData.getAllUsers().get(i).getUserProfile().getEmail().equals(email)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("EMAIL DOESN'T EXIST!");
        } else {
            currentData.getAllUsers().get(indexOfSelectedUser).getUserProfile().editProfile(currentData);
        }
    }

    public void adminRemovesUsers(Data currentData, Admin currentAdmin) {
        System.out.println("--user removing--");
        System.out.println("choose a number:");
        System.out.println("1.remove user by username");
        System.out.println("2.remove user by phone number");
        System.out.println("3.remove user by email");
        System.out.println("4.back to admin users");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            removeByUsername(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 2) {
            removeByPhoneNumber(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 3) {
            removeByEmail(currentData);
            System.out.println("1.back to admin users");
            int num = sc.nextInt();
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 4) {
            currentAdmin.adminUsers(currentData, currentAdmin);
        }
    }

    public void removeByUsername(Data currentData) {
        System.out.println("enter username:");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < currentData.getAllUsers().size(); i++) {
            if (currentData.getAllUsers().get(i).getUserProfile().getUsername().equals(username)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("USERNAME DOESN'T EXIST!");
        } else {
            currentData.getAllUsers().remove(indexOfSelectedUser);
            System.out.println("USER WAS REMOVED SUCCESSFULLY!");
        }
    }

    public void removeByPhoneNumber(Data currentData) {
        System.out.println("enter phone number:");
        Scanner sc = new Scanner(System.in);
        String phoneNum = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < currentData.getAllUsers().size(); i++) {
            if (currentData.getAllUsers().get(i).getUserProfile().getPhoneNumber().equals(phoneNum)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("PHONE NUMBER DOESN'T EXIST!");
        } else {
            currentData.getAllUsers().remove(indexOfSelectedUser);
            System.out.println("USER WAS REMOVED SUCCESSFULLY!");
        }
    }

    public void removeByEmail(Data data) {
        System.out.println("enter email:");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();
        int indexOfSelectedUser = -1;
        for (int i = 0; i < data.getAllUsers().size(); i++) {
            if (data.getAllUsers().get(i).getUserProfile().getEmail().equals(email)) {
                indexOfSelectedUser = i;
                break;
            }
        }
        if (indexOfSelectedUser == -1) {
            System.out.println("EMAIL DOESN'T EXIST!");
        } else {
            data.getAllUsers().remove(indexOfSelectedUser);
            System.out.println("USER WAS REMOVED SUCCESSFULLY!");
        }
    }
}
