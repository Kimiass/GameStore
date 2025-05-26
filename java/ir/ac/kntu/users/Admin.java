package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.*;
import ir.ac.kntu.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Admin extends SpecialUser {

    private AdminUserController adminUserController;

    public Admin(SpecialUserProfile specialUserProfile, AdminUserController adminUserController) {
        super(specialUserProfile);
        this.adminUserController = adminUserController;
    }

    public AdminUserController getAdminUserController() {
        return adminUserController;
    }

    public void adminMenu(Data currentData, Admin currentAdmin) {
        System.out.println("--admin menu--");
        System.out.println("choose a number:");
        System.out.println("1.profile");
        System.out.println("2.games");
        System.out.println("3.users");
        System.out.println("4.accessories");
        System.out.println("5.report a game error");
        System.out.println("6.most active users");
        System.out.println("7.back to previous page");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            adminProfilePage(currentData, currentAdmin);
        } else if (enteredNum == 2) {
            currentAdmin.adminGames(currentData, currentAdmin);
        } else if (enteredNum == 3) {
            currentAdmin.adminUsers(currentData, currentAdmin);
        } else if (enteredNum == 4) {
            currentAdmin.adminAccessories(currentData, currentAdmin);
        } else if (enteredNum == 5) {
            reportError(currentData, currentAdmin);
        } else if (enteredNum == 6) {
            currentAdmin.getAdminUserController().viewActiveUsers(currentData, currentAdmin);
        } else if (enteredNum == 7) {
            Main.logIn(currentData);
        }
    }

    public void adminProfilePage(Data currentData, Admin currentAdmin) {
        System.out.println("--profile page--");
        System.out.println("choose a number:");
        System.out.println("1.view profile");
        System.out.println("2.edit profile");
        System.out.println("3.back to admin menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            currentAdmin.getSpecialUserProfile().viewProfile(currentData);
            System.out.println("1.back to profile page");
            int num = sc.nextInt();
            adminProfilePage(currentData, currentAdmin);
        } else if (enteredNum == 2) {
            ArrayList<String> allAdminUsernames = new ArrayList<>();
            for (int i = 0; i < currentData.getAllAdmins().size(); i++) {
                allAdminUsernames.add(currentData.getAllAdmins().get(i).getSpecialUserProfile().getUsername());
            }
            currentAdmin.getSpecialUserProfile().editProfile(currentData, allAdminUsernames);
            System.out.println("1.back to profile page");
            int num = sc.nextInt();
            adminProfilePage(currentData, currentAdmin);
        } else if (enteredNum == 3) {
            adminMenu(currentData, currentAdmin);
        }
    }

    public void adminGames(Data currentData, Admin currentAdmin) {
        System.out.println("--admin games--");
        System.out.println("choose a number");
        System.out.println("1.add a game");
        System.out.println("2.edit a game");
        System.out.println("3.remove a game");
        System.out.println("4.back to admin menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            adminAddsGames(currentData, currentAdmin);
        } else if (enteredNum == 2) {
            adminEditsGames(currentData, currentAdmin);
        } else if (enteredNum == 3) {
            adminRemovesGames(currentData, currentAdmin);
        } else if (enteredNum == 4) {
            currentAdmin.adminMenu(currentData, currentAdmin);
        }
    }

    public void adminAddsGames(Data currentData, Admin currentAdmin) {
        currentAdmin.specialUserAddsGame(currentData);
        currentAdmin.adminGames(currentData, currentAdmin);
    }

    public void adminEditsGames(Data currentData, Admin currentAdmin) {
        ArrayList<Integer> indexOfMatchedGames = new ArrayList<>();
        System.out.println("--game editing--");
        System.out.println("search the name of the game:");
        Scanner sc = new Scanner(System.in);
        String gameName = sc.nextLine();
        int j = 1;
        for (int i = 0; i < currentData.getAllGames().size(); i++) {
            if (currentData.getAllGames().get(i).getName().toLowerCase().startsWith(gameName.toLowerCase())) {
                System.out.println(j + "." + currentData.getAllGames().get(i).getName());
                indexOfMatchedGames.add(i);
                j++;
            }
        }
        if (j > 1) {
            System.out.println("choose a number:");
            int enteredNum = sc.nextInt();
            Game selectedGame = currentData.getAllGames().get(indexOfMatchedGames.get(enteredNum - 1));
            currentAdmin.printOptionsOfEditing(selectedGame);
            int enteredNum2 = sc.nextInt();
            if (enteredNum2 == 1) {
                currentAdmin.nameEditing(selectedGame);
                currentAdmin.adminGames(currentData, currentAdmin);
            } else if (enteredNum2 == 2) {
                currentAdmin.genreEditing(selectedGame);
                currentAdmin.adminGames(currentData, currentAdmin);
            } else if (enteredNum2 == 3) {
                currentAdmin.descriptionEditing(selectedGame);
                currentAdmin.adminGames(currentData, currentAdmin);
            } else if (enteredNum2 == 4) {
                currentAdmin.priceEditing(selectedGame);
                currentAdmin.adminGames(currentData, currentAdmin);
            } else if (enteredNum2 == 5) {
                currentAdmin.adminGames(currentData, currentAdmin);
            }
        } else {
            System.out.println("NO GAMES WITH THIS NAME!");
            currentAdmin.adminGames(currentData, currentAdmin);
        }
    }

    public void adminRemovesGames(Data currentData, Admin currentAdmin) {
        currentAdmin.gameRemoving(currentData, currentAdmin);
    }

    public void adminUsers(Data currentData, Admin currentAdmin) {
        System.out.println("--admin users--");
        System.out.println("choose a number");
        System.out.println("1.view user info");
        System.out.println("2.add a user");
        System.out.println("3.edit user info");
        System.out.println("4.remove a user");
        System.out.println("5.back to admin menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            adminUserController.viewUserInfo(currentData, currentAdmin);
        } else if (enteredNum == 2) {
            adminUserController.adminAddsUsers(currentData, currentAdmin);
        } else if (enteredNum == 3) {
            adminUserController.adminEditsUsers(currentData, currentAdmin);
        } else if (enteredNum == 4) {
            adminUserController.adminRemovesUsers(currentData, currentAdmin);
        } else if (enteredNum == 5) {
            currentAdmin.adminMenu(currentData, currentAdmin);
        }
    }

    public void adminAccessories(Data currentData, Admin currentAdmin) {
        System.out.println("--admin accessories--");
        System.out.println("choose a number:");
        System.out.println("1.add accessory");
        System.out.println("2.edit accessory");
        System.out.println("3.remove accessory");
        System.out.println("4.back to admin menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            currentAdmin.addAccessory(currentData, currentAdmin);
            currentAdmin.adminAccessories(currentData, currentAdmin);
        } else if (enteredNum == 2) {
            currentAdmin.searchAccessoryToEdit(currentData);
            currentAdmin.adminAccessories(currentData, currentAdmin);
        } else if (enteredNum == 3) {
            currentAdmin.removeAccessory(currentData.getAllGameControllers(), currentData.getAllGameMonitors());
            currentAdmin.adminAccessories(currentData, currentAdmin);
        } else if (enteredNum == 4) {
            adminMenu(currentData, currentAdmin);
        }
    }

    public void reportError(Data currentData, Admin currentAdmin) {
        System.out.println("--error report--");
        if (currentData.getAllGames().size() > 0) {
            System.out.println("choose a number:");
            for (int i = 0; i < currentData.getAllGames().size(); i++) {
                System.out.println(i + 1 + "." + currentData.getAllGames().get(i).getName());
            }
            Scanner sc = new Scanner(System.in);
            int enteredNum = sc.nextInt();
            Game selectedGame = currentData.getAllGames().get(enteredNum - 1);
            System.out.println("1.report an error");
            System.out.println("2.back to admin menu");
            int enteredNum2 = sc.nextInt();
            if (enteredNum2 == 1) {
                currentData.getAllGames().remove(selectedGame);
                reportErrorToDevelopers(currentData, selectedGame);
                System.out.println("1.back to admin menu");
                int enteredNum3 = sc.nextInt();
                currentAdmin.adminMenu(currentData, currentAdmin);
            } else if (enteredNum2 == 2) {
                currentAdmin.adminMenu(currentData, currentAdmin);
            }
        } else {
            System.out.println("NO GAMES AT THE TIME!");
            adminMenu(currentData, currentAdmin);
        }
    }

    public void reportErrorToDevelopers(Data currentData, Game selectedGame) {
        Collections.sort(currentData.getAllDevelopers());
        currentData.getAllDevelopers().get(0).getScheduledEvents().add(selectedGame);
        System.out.println("GAME ERROR WAS REPORTED SUCCESSFULLY TO " + currentData.getAllDevelopers().
                get(0).getSpecialUserProfile().getUsername() + "!");
    }
}