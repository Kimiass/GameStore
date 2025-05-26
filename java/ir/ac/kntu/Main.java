package ir.ac.kntu;

import java.util.Scanner;

public class Main {

    public static void logIn(Data currentData) {
        System.out.println("--entry type--");
        System.out.println("choose a number:");
        System.out.println("1.admin");
        System.out.println("2.game developer");
        System.out.println("3.accessory seller");
        System.out.println("4.user");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            currentData.adminLogin(currentData);
        } else if (enteredNum == 2) {
            currentData.developerLogin(currentData);
        } else if (enteredNum == 3) {
            currentData.sellerLogin(currentData);
        } else if (enteredNum == 4) {
            currentData.userEntry(currentData);
        }
    }

    public static void main(String[] args) {
        Data currentData = new Data();
        currentData.addDefaultGames();
        currentData.addDefaultUsers();
        currentData.addDefaultAdmin();
        currentData.addDefaultDeveloper();
        currentData.addDefaultAccessorySeller();
        currentData.addDefaultAccessories();
        logIn(currentData);
    }
}