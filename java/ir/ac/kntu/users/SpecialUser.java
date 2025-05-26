package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SpecialUser {
    SpecialUserProfile specialUserProfile;

    public SpecialUser(SpecialUserProfile specialUserProfile) {
        this.specialUserProfile = specialUserProfile;
    }

    public SpecialUserProfile getSpecialUserProfile() {
        return specialUserProfile;
    }

    public void setSpecialUserProfile(SpecialUserProfile specialUserProfile) {
        this.specialUserProfile = specialUserProfile;
    }

    public void specialUserAddsGame(Data currentData) {
        System.out.println("--game adding--");
        System.out.println("enter name of the game:");
        Scanner sc = new Scanner(System.in);
        String gameName = sc.nextLine();
        System.out.println("enter game genre:");
        String gameGenre = sc.nextLine();
        System.out.println("enter game description:");
        String gameDescription = sc.nextLine();
        System.out.println("enter game price:");
        double gamePrice = sc.nextDouble();
        ArrayList<String> gameReviews = new ArrayList<>();
        ArrayList<String> gameFeedbacks = new ArrayList<>();
        System.out.println("choose game type:");
        System.out.println("1.original");
        System.out.println("2.beta");
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            Game creatededGame = new Game(gameName, gameDescription, gamePrice, 0, 0,
                    0, gameReviews, gameFeedbacks, new HashMap<>(), gameGenre, 1, GameType.Original);
            currentData.getAllGames().add(creatededGame);
        } else if (enteredNum == 2) {
            Game creatededGame = new Game(gameName, gameDescription, gamePrice, 0, 0,
                    0, gameReviews, gameFeedbacks, new HashMap<>(), gameGenre, 1, GameType.Beta);
            currentData.getAllGames().add(creatededGame);
        }
        System.out.println("GAME WAS ADDED SUCCESSFULLY!");
    }

    public void printOptionsOfEditing(Game game) {
        System.out.println("--game " + game.getName() + " editing--");
        System.out.println("choose a number:");
        System.out.println("1.edit name of the game " + game.getName());
        System.out.println("2.edit genre of the game " + game.getName());
        System.out.println("3.edit description of the game " + game.getName());
        System.out.println("4.edit price of the game " + game.getName());
        System.out.println("5.back to previous page");
    }

    public void nameEditing(Game selectedGame) {
        System.out.println("enter new name:");
        Scanner sc = new Scanner(System.in);
        String newName = sc.nextLine();
        selectedGame.setName(newName);
        System.out.println("NAME EDITED SUCCESSFULLY!");
    }

    public void genreEditing(Game selectedGame) {
        System.out.println("enter new genre:");
        Scanner sc = new Scanner(System.in);
        String newGenre = sc.nextLine();
        selectedGame.setGenre(newGenre);
        System.out.println("GENRE EDITED SUCCESSFULLY!");
    }

    public void descriptionEditing(Game selectedGame) {
        System.out.println("enter new description:");
        Scanner sc = new Scanner(System.in);
        String newDescription = sc.nextLine();
        selectedGame.setDescription(newDescription);
        System.out.println("DESCRIPTION EDITED SUCCESSFULLY!");
    }

    public void priceEditing(Game selectedGame) {
        System.out.println("enter new price:");
        Scanner sc = new Scanner(System.in);
        double newPrice = sc.nextDouble();
        selectedGame.setCost(newPrice);
        System.out.println("PRICE EDITED SUCCESSFULLY!");
    }

    public void gameRemoving(Data currentData, Admin currentAdmin) {
        ArrayList<Integer> indexOfMatchedGames = new ArrayList<>();
        System.out.println("--game removing--");
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
            currentData.getAllGames().remove(selectedGame);
            for (int i = 0; i < currentData.getAllUsers().size(); i++) {
                currentData.getAllUsers().get(i).getMyGames().remove(selectedGame);
            }
            System.out.println("GAME WAS REMOVED SUCCESSFULLY!");
            currentAdmin.adminGames(currentData, currentAdmin);
        } else {
            System.out.println("NO GAMES WITH THIS NAME!");
            currentAdmin.adminGames(currentData, currentAdmin);
        }
    }

    public void addAccessory(Data currentData, SpecialUser currentSpecialUser) {
        System.out.println("choose a number:");
        System.out.println("1.add controller");
        System.out.println("2.add monitor");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            addController(currentData, currentSpecialUser);
        } else if (enteredNum == 2) {
            addMonitor(currentData, currentSpecialUser);
        }
    }

    public void addController(Data currentData, SpecialUser currentSpecialUser) {
        System.out.println("enter controller name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("enter controller description:");
        String description = sc.nextLine();
        System.out.println("enter controller cost:");
        double cost = sc.nextDouble();
        ArrayList<String> reviews = new ArrayList<>();
        ArrayList<String> feedbacks = new ArrayList<>();
        System.out.println("enter stock:");
        int stock = sc.nextInt();
        System.out.println("enter paired device");
        String pairedDevice = sc.nextLine();
        pairedDevice = sc.nextLine();
        System.out.println("choose controller type:");
        System.out.println("1.wired");
        System.out.println("2.wireless");
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            GameController gameController = new GameController(name, description, cost, 0, 0,
                    0, reviews, feedbacks, new HashMap<>(), stock, pairedDevice, ControllerType.Wired);
            currentData.getAllGameControllers().add(gameController);
            System.out.println("CONTROLLER WAS ADDED SUCCESSFULLY!");
            if (currentSpecialUser instanceof AccessorySeller) {
                ((AccessorySeller) currentSpecialUser).getCreatedControllers().add(gameController);
            }
        } else if (enteredNum == 2) {
            GameController gameController = new GameController(name, description, cost, 0, 0,
                    0, reviews, feedbacks, new HashMap<>(), stock, pairedDevice, ControllerType.Wireless);
            currentData.getAllGameControllers().add(gameController);
            System.out.println("CONTROLLER WAS ADDED SUCCESSFULLY!");
            if (currentSpecialUser instanceof AccessorySeller) {
                ((AccessorySeller) currentSpecialUser).getCreatedControllers().add(gameController);
            }
        }
    }

    public void addMonitor(Data currentData, SpecialUser currentSpecialUser) {
        System.out.println("enter monitor name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("enter monitor description:");
        String description = sc.nextLine();
        System.out.println("enter monitor cost:");
        double cost = sc.nextDouble();
        ArrayList<String> reviews = new ArrayList<>();
        ArrayList<String> feedbacks = new ArrayList<>();
        System.out.println("enter stock:");
        int stock = sc.nextInt();
        System.out.println("enter picture update:");
        double pictureUpdate = sc.nextDouble();
        System.out.println("enter size:");
        double size = sc.nextDouble();
        System.out.println("enter response time:");
        double responseTime = sc.nextDouble();
        GameMonitor gameMonitor = new GameMonitor(name, description, cost, 0, 0, 0,
                reviews, feedbacks, new HashMap<>(), stock, pictureUpdate, size, responseTime);
        currentData.getAllGameMonitors().add(gameMonitor);
        System.out.println("MONITOR WAS ADDED SUCCESSFULLY!");
        if (currentSpecialUser instanceof AccessorySeller) {
            ((AccessorySeller) currentSpecialUser).getCreatedMonitors().add(gameMonitor);
        }
    }

    public void searchAccessoryToEdit(Data currentData) {
        System.out.println("search accessories to edit:");
        Scanner sc = new Scanner(System.in);
        String accessoryName = sc.nextLine();
        boolean accessoryFound = false;
        for (int i = 0; i < currentData.getAllGameControllers().size(); i++) {
            if (currentData.getAllGameControllers().get(i).getName().equals(accessoryName)) {
                editController(currentData.getAllGameControllers().get(i));
                accessoryFound = true;
                break;
            }
        }
        for (int i = 0; i < currentData.getAllGameMonitors().size(); i++) {
            if (currentData.getAllGameMonitors().get(i).getName().equals(accessoryName)) {
                editMonitor(currentData.getAllGameMonitors().get(i));
                accessoryFound = true;
                break;
            }
        }
        if (!accessoryFound) {
            System.out.println("NO ACCESSORIES WITH THIS NAME!");
            searchAccessoryToEdit(currentData);
        }
    }

    public void editSellerAccessories(Data currentData, AccessorySeller currentSeller) {
        System.out.println("search accessories to edit:");
        Scanner sc = new Scanner(System.in);
        String accessoryName = sc.nextLine();
        boolean accessoryFound = false;
        for (int i = 0; i < currentSeller.getCreatedControllers().size(); i++) {
            if (currentSeller.getCreatedControllers().get(i).getName().equals(accessoryName)) {
                editController(currentSeller.getCreatedControllers().get(i));
                accessoryFound = true;
                break;
            }
        }
        for (int i = 0; i < currentSeller.getCreatedMonitors().size(); i++) {
            if (currentSeller.getCreatedMonitors().get(i).getName().equals(accessoryName)) {
                editMonitor(currentSeller.getCreatedMonitors().get(i));
                accessoryFound = true;
                break;
            }
        }
        if (!accessoryFound) {
            System.out.println("NO ACCESSORIES WITH THIS NAME!");
            editSellerAccessories(currentData, currentSeller);
        }
    }

    public void editController(GameController selectedController) {
        System.out.println("--accessory edit--");
        System.out.println("enter previous name or a new name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        selectedController.setName(name);
        System.out.println("enter previous description or a new description:");
        String description = sc.nextLine();
        selectedController.setDescription(description);
        System.out.println("enter previous cost or a new  cost:");
        double cost = sc.nextDouble();
        selectedController.setCost(cost);
        System.out.println("enter previous stock or a new stock:");
        int stock = sc.nextInt();
        selectedController.setStock(stock);
        System.out.println("enter previous device or a new device");
        String pairedDevice = sc.nextLine();
        pairedDevice = sc.nextLine();
        selectedController.setPairedDevice(pairedDevice);
        System.out.println("choose controller type:");
        System.out.println("1.wired");
        System.out.println("2.wireless");
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            selectedController.setControllerType(ControllerType.Wired);
        } else if (enteredNum == 2) {
            selectedController.setControllerType(ControllerType.Wireless);
        }
        System.out.println("GAME CONTROLLER WAS EDITED SUCCESSFULLY!");
    }

    public void editMonitor(GameMonitor selectedMonitor) {
        System.out.println("--accessory edit--");
        System.out.println("enter previous name or a new name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        selectedMonitor.setName(name);
        System.out.println("enter previous description or a new description:");
        String description = sc.nextLine();
        selectedMonitor.setDescription(description);
        System.out.println("enter previous cost or a new  cost:");
        double cost = sc.nextDouble();
        selectedMonitor.setCost(cost);
        System.out.println("enter previous stock or a new stock:");
        int stock = sc.nextInt();
        selectedMonitor.setStock(stock);
        System.out.println("enter previous picture update or a new picture update:");
        double pictureUpdate = sc.nextDouble();
        selectedMonitor.setPictureUpdate(pictureUpdate);
        System.out.println("enter previous size or a new size:");
        double size = sc.nextDouble();
        selectedMonitor.setSize(size);
        System.out.println("enter previous response time or a new response time:");
        double responseTime = sc.nextDouble();
        selectedMonitor.setResponseTime(responseTime);
        System.out.println("GAME MONITOR WAS EDITED SUCCESSFULLY!");
    }

    public void removeAccessory(ArrayList<GameController> gameControllers, ArrayList<GameMonitor> gameMonitors) {
        System.out.println("search accessories to remove:");
        Scanner sc = new Scanner(System.in);
        String accessoryName = sc.nextLine();
        boolean accessoryFound = false;
        for (int i = 0; i < gameControllers.size(); i++) {
            if (gameControllers.get(i).getName().equals(accessoryName)) {
                gameControllers.remove(i);
                System.out.println("GAME CONTROLLER WAS REMOVED SUCCESSFULLY!");
                accessoryFound = true;
                break;
            }
        }
        for (int i = 0; i < gameMonitors.size(); i++) {
            if (gameMonitors.get(i).getName().equals(accessoryName)) {
                gameMonitors.remove(i);
                System.out.println("GAME MONITOR WAS REMOVED SUCCESSFULLY!");
                accessoryFound = true;
                break;
            }
        }
        if (!accessoryFound) {
            System.out.println("NO ACCESSORIES WITH THIS NAME!");
            removeAccessory(gameControllers, gameMonitors);
        }
    }
}