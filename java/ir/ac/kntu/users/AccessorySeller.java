package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.GameController;
import ir.ac.kntu.game.GameMonitor;
import ir.ac.kntu.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class AccessorySeller extends SpecialUser {

    private ArrayList<GameController> createdControllers;

    private ArrayList<GameMonitor> createdMonitors;

    public AccessorySeller(SpecialUserProfile specialUserProfile, ArrayList<GameController> createdControllers,
                           ArrayList<GameMonitor> createdMonitors) {
        super(specialUserProfile);
        this.createdControllers = createdControllers;
        this.createdMonitors = createdMonitors;
    }

    public ArrayList<GameController> getCreatedControllers() {
        return createdControllers;
    }

    public ArrayList<GameMonitor> getCreatedMonitors() {
        return createdMonitors;
    }

    public void sellerMenu(Data currentData, AccessorySeller currentSeller) {
        System.out.println("--seller menu--");
        System.out.println("choose a number");
        System.out.println("1.profile");
        System.out.println("2.add a game accessory");
        System.out.println("3.edit my created accessory");
        System.out.println("4.remove my created accessory");
        System.out.println("5.back to previous page");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            sellerProfilePage(currentData, currentSeller);
        } else if (enteredNum == 2) {
            currentSeller.addAccessory(currentData, currentSeller);
            sellerMenu(currentData, currentSeller);
        } else if (enteredNum == 3) {
            currentSeller.editSellerAccessories(currentData, currentSeller);
            sellerMenu(currentData, currentSeller);
        } else if (enteredNum == 4) {
            currentSeller.removeAccessory(createdControllers, createdMonitors);
            sellerMenu(currentData, currentSeller);
        } else if (enteredNum == 5) {
            Main.logIn(currentData);
        }
    }

    public void sellerProfilePage(Data currentData, AccessorySeller currentSeller) {
        System.out.println("--profile page--");
        System.out.println("choose a number:");
        System.out.println("1.view profile");
        System.out.println("2.edit profile");
        System.out.println("3.back to seller menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            currentSeller.getSpecialUserProfile().viewProfile(currentData);
            System.out.println("1.back to profile page");
            int num = sc.nextInt();
            sellerProfilePage(currentData, currentSeller);
        } else if (enteredNum == 2) {
            ArrayList<String> allSellerUsernames = new ArrayList<>();
            for (int i = 0; i < currentData.getAllSellers().size(); i++) {
                allSellerUsernames.add(currentData.getAllSellers().get(i).getSpecialUserProfile().getUsername());
            }
            currentSeller.getSpecialUserProfile().editProfile(currentData, allSellerUsernames);
            System.out.println("1.back to profile page");
            int num = sc.nextInt();
            sellerProfilePage(currentData, currentSeller);
        } else if (enteredNum == 3) {
            sellerMenu(currentData, currentSeller);
        }
    }
}
