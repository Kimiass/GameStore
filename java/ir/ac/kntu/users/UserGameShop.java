package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.*;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UserGameShop {

    public UserGameShop() {
    }

    public void viewAllProducts(Data currentData) {
        int j = 1;
        for (int i = 0; i < currentData.getAllGames().size(); i++) {
            for (int k = 0; k < currentData.getAllGames().get(i).getName().length() * 1.3; k++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.print("*" + j + ".");
            j++;
            System.out.println(currentData.getAllGames().get(i).getName() + "*");
            for (int k = 0; k < currentData.getAllGames().get(i).getName().length() * 1.3; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 0; i < currentData.getAllGameMonitors().size(); i++) {
            for (int k = 0; k < currentData.getAllGameMonitors().get(i).getName().length() * 1.3; k++) {
                System.out.print("-");
            }
            System.out.println();
            System.out.print("|" + j + ".");
            j++;
            System.out.println(currentData.getAllGameMonitors().get(i).getName() + "|");
            for (int k = 0; k < currentData.getAllGameMonitors().get(i).getName().length() * 1.3; k++) {
                System.out.print("-");
            }
            System.out.println();
        }
        for (int i = 0; i < currentData.getAllGameControllers().size(); i++) {
            for (int k = 0; k < currentData.getAllGameControllers().get(i).getName().length() * 1.3; k++) {
                System.out.print("-");
            }
            System.out.println();
            System.out.print("|" + j + ".");
            j++;
            System.out.println(currentData.getAllGameControllers().get(i).getName() + "|");
            for (int k = 0; k < currentData.getAllGameControllers().get(i).getName().length() * 1.3; k++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public void viewTrendProducts(Data currentData, User currentUser, StopWatch stopWatch) {
        System.out.println("trend Products:");
        ArrayList<GameGenerality> allProducts = new ArrayList<>();
        for (int i = 0; i < currentData.getAllGames().size(); i++) {
            allProducts.add(currentData.getAllGames().get(i));
        }
        for (int i = 0; i < currentData.getAllGameMonitors().size(); i++) {
            allProducts.add(currentData.getAllGameMonitors().get(i));
        }
        for (int i = 0; i < currentData.getAllGameControllers().size(); i++) {
            allProducts.add(currentData.getAllGameControllers().get(i));
        }
        Collections.sort(allProducts);
        for (int i = 0; i < allProducts.size(); i++) {
            System.out.println(allProducts.get(i).getName());
        }
        System.out.println("1.back to user menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        currentUser.userMenu(currentData, currentUser, stopWatch);
    }

    public void viewGameInfo(int chosenGameIndex, ArrayList<Game> games) {
        System.out.println("--selected game info--");
        System.out.println("name: " + games.get(chosenGameIndex - 1).getName());
        System.out.println("genre: " + games.get(chosenGameIndex - 1).getGenre());
        System.out.println("description: " + games.get(chosenGameIndex - 1).getDescription());
        System.out.println("average rate: " + games.get(chosenGameIndex - 1).getAverageRating());
        System.out.println("cost: " + games.get(chosenGameIndex - 1).getCost());
    }

    public void viewAccessoryInfo(GameAccessories gameAccessories) {
        System.out.println("--selected accessory info--");
        System.out.println("name: " + gameAccessories.getName());
        System.out.println("description: " + gameAccessories.getDescription());
        System.out.println("average rate: " + gameAccessories.getAverageRating());
        System.out.println("cost: " + gameAccessories.getCost());
    }

    public void viewMyProducts(User currentUser) {
        int j = 1;
        if (currentUser.getMyGames().size() > 0) {
            System.out.println("games:");
            for (int i = 0; i < currentUser.getMyGames().size(); i++) {
                System.out.println(j + "." + currentUser.getMyGames().get(i).getName());
                j++;
            }
        }
        if (currentUser.getMyMonitors().size() > 0) {
            System.out.println("monitors:");
            for (GameMonitor gameMonitor : currentUser.getMyMonitors().keySet()) {
                String key = gameMonitor.getName();
                System.out.println(j + "." + key);
                j++;
            }
        }
        if (currentUser.getMyControllers().size() > 0) {
            System.out.println("controllers:");
            for (GameController gameController : currentUser.getMyControllers().keySet()) {
                String key = gameController.getName();
                System.out.println(j + "." + key);
                j++;
            }
        }
    }

    public boolean gameAlreadyExists(User currentUser, Game selectedGame) {
        for (int i = 0; i < currentUser.getMyGames().size(); i++) {
            if (currentUser.getMyGames().get(i).equals(selectedGame)) {
                return true;
            }
        }
        return false;
    }

    public boolean userAffordsGame(User currentUser, Game selectedGame) {
        if (currentUser.getUserProfile().getScore() < 20) {
            if (currentUser.getUserProfile().getWallet() >= selectedGame.getCost()) {
                return true;
            } else {
                return false;
            }
        } else if (currentUser.getUserProfile().getScore() >= 20 && currentUser.getUserProfile().getScore() < 50) {
            if (currentUser.getUserProfile().getWallet() * 10 / 9 >= selectedGame.getCost()) {
                return true;
            } else {
                return false;
            }
        } else if (currentUser.getUserProfile().getScore() >= 50 && currentUser.getUserProfile().getScore() < 100) {
            if (currentUser.getUserProfile().getWallet() * 10 / 8 >= selectedGame.getCost()) {
                return true;
            } else {
                return false;
            }
        } else if (currentUser.getUserProfile().getScore() >= 100) {
            if (currentUser.getUserProfile().getWallet() * 10 / 7 >= selectedGame.getCost()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean userAffordsAccessory(User currentUser, GameAccessories gameAccessories) {
        if (currentUser.getUserProfile().getWallet() >= gameAccessories.getCost()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean requiredUserScore(User currentUser, Game selectedGame) {
        if (selectedGame.getLevel() == 2 && currentUser.getUserProfile().getScore() < 20) {
            return false;
        } else if (selectedGame.getLevel() == 3 && currentUser.getUserProfile().getScore() < 50) {
            return false;
        } else if (selectedGame.getLevel() == 4 && currentUser.getUserProfile().getScore() < 100) {
            return false;
        } else {
            return true;
        }
    }

    public void buyGames(Data currentData, User currentUser, Game selectedGame, StopWatch stopWatch) {
        System.out.println("choose a number:");
        System.out.println("1.buy this game");
        System.out.println("2.back to store page");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            if (userAffordsGame(currentUser, selectedGame) && requiredUserScore(currentUser, selectedGame)) {
                currentUser.getMyGames().add(selectedGame);
                currentUser.getUserProfile().spentMoneyOnGame(selectedGame, currentUser);
                selectedGame.setTrendProduct(selectedGame.getTrendProduct() + 1);
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else if (!userAffordsGame(currentUser, selectedGame) && requiredUserScore(currentUser, selectedGame)) {
                System.out.println("purchase error: NOT ENOUGH MONEY IN WALLET!");
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else if (userAffordsGame(currentUser, selectedGame) && !requiredUserScore(currentUser, selectedGame)) {
                System.out.println("purchase error: NOT ENOUGH SCORE!");
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else {
                System.out.println("purchase error: NOT ENOUGH MONEY AND SCORE!");
                currentUser.storePage(currentData, currentUser, stopWatch);
            }
        } else if (enteredNum == 2) {
            currentUser.storePage(currentData, currentUser, stopWatch);
        }
    }


    public void buyController(Data currentData, User currentUser, GameController gameController, StopWatch stopWatch) {
        System.out.println("choose a number:");
        System.out.println("1.buy this controller");
        System.out.println("2.back to store page");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            if (userAffordsAccessory(currentUser, gameController) && gameController.getStock() > 0) {
                currentUser.getUserProfile().spentMoneyOnAccessory(gameController, currentUser);
                currentUser.getMyControllers().computeIfPresent(gameController, (key, val) -> val + 1);
                currentUser.getMyControllers().computeIfAbsent(gameController, k -> 1);
                gameController.setStock(gameController.getStock() - 1);
                gameController.setTrendProduct(gameController.getTrendProduct() + 1);
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else if (userAffordsAccessory(currentUser, gameController) && gameController.getStock() == 0) {
                System.out.println("purchase error: SOLD OUT!");
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else if (!userAffordsAccessory(currentUser, gameController) && gameController.getStock() > 0) {
                System.out.println("purchase error: NOT ENOUGH MONEY IN WALLET!");
                currentUser.storePage(currentData, currentUser, stopWatch);
            }
        } else if (enteredNum == 2) {
            currentUser.storePage(currentData, currentUser, stopWatch);
        }
    }

    public void buyMonitor(Data currentData, User currentUser, GameMonitor gameMonitor, StopWatch stopWatch) {
        System.out.println("choose a number:");
        System.out.println("1.buy this monitor");
        System.out.println("2.back to store page");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            if (userAffordsAccessory(currentUser, gameMonitor) && gameMonitor.getStock() > 0) {
                currentUser.getUserProfile().spentMoneyOnAccessory(gameMonitor, currentUser);
                currentUser.getMyMonitors().computeIfPresent(gameMonitor, (key, val) -> val + 1);
                currentUser.getMyMonitors().computeIfAbsent(gameMonitor, k -> 1);
                gameMonitor.setStock(gameMonitor.getStock() - 1);
                gameMonitor.setTrendProduct(gameMonitor.getTrendProduct() + 1);
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else if (userAffordsAccessory(currentUser, gameMonitor) && gameMonitor.getStock() == 0) {
                System.out.println("purchase error: SOLD OUT!");
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else if (!userAffordsAccessory(currentUser, gameMonitor) && gameMonitor.getStock() > 0) {
                System.out.println("purchase error: NOT ENOUGH MONEY IN WALLET!");
                currentUser.storePage(currentData, currentUser, stopWatch);
            }
        } else if (enteredNum == 2) {
            currentUser.storePage(currentData, currentUser, stopWatch);
        }
    }

    public void searchGames(Data currentData, User currentUser, StopWatch stopWatch) {
        System.out.println("choose a number");
        System.out.println("1.search within all products");
        System.out.println("2.search within games");
        System.out.println("3.search within monitors");
        System.out.println("4.search within controllers");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            allProductsSearchAndBuy(currentData, currentUser, stopWatch);
        } else if (enteredNum == 2) {
            gameSearchAndBuy(currentData, currentUser, stopWatch);
        } else if (enteredNum == 3) {
            monitorSearchAndBuy(currentData, currentUser, stopWatch);
        } else if (enteredNum == 4) {
            controllerSearchAndBuy(currentData, currentUser, stopWatch);
        }
    }

    public void allProductsSearchAndBuy(Data currentData, User currentUser, StopWatch stopWatch) {
        ArrayList<Game> matchedGames = new ArrayList<>();
        ArrayList<GameMonitor> matchesMonitors = new ArrayList<>();
        ArrayList<GameController> matchedControllers = new ArrayList<>();
        System.out.println("--all products search--");
        System.out.println("search a product:");
        Scanner sc = new Scanner(System.in);
        String searchString = sc.nextLine();
        findTheProducts(currentData, matchedGames, matchedControllers, matchesMonitors, searchString);
        if (matchedGames.size() + matchedControllers.size() + matchesMonitors.size() > 0) {
            System.out.println("choose a number:");
            int enteredNum = sc.nextInt();
            if (enteredNum <= matchedGames.size()) {
                Game selectedGame = matchedGames.get(enteredNum - 1);
                currentUser.getUserGameShop().viewGameInfo(enteredNum, matchedGames);
                buyGames(currentData, currentUser, selectedGame, stopWatch);
            } else if (enteredNum > matchedGames.size() && enteredNum <= matchesMonitors.size() + matchedGames.size()) {
                GameMonitor selectedMonitor = matchesMonitors.get(enteredNum - matchedGames.size() - 1);
                currentUser.getUserGameShop().viewAccessoryInfo(selectedMonitor);
                buyMonitor(currentData, currentUser, selectedMonitor, stopWatch);
            } else {
                GameController selectedController = matchedControllers.get(enteredNum - matchedGames.size() -
                        matchesMonitors.size() - 1);
                currentUser.getUserGameShop().viewAccessoryInfo(selectedController);
                buyController(currentData, currentUser, selectedController, stopWatch);
            }
        } else {
            System.out.println("NO PRODUCTS WITH THIS NAME!");
            currentUser.storePage(currentData, currentUser, stopWatch);
        }
    }

    public void findTheProducts(Data currentData, ArrayList<Game> games, ArrayList<GameController> controllers,
                                ArrayList<GameMonitor> monitors, String search) {
        int j = 1;
        for (int i = 0; i < currentData.getAllGames().size(); i++) {
            if (currentData.getAllGames().get(i).getName().toLowerCase().startsWith(search.toLowerCase())) {
                System.out.println(j + "." + currentData.getAllGames().get(i).getName());
                games.add(currentData.getAllGames().get(i));
                j++;
            }
        }
        for (int i = 0; i < currentData.getAllGameMonitors().size(); i++) {
            if (currentData.getAllGameMonitors().get(i).getName().toLowerCase().
                    startsWith(search.toLowerCase())) {
                System.out.println(j + "." + currentData.getAllGameMonitors().get(i).getName());
                monitors.add(currentData.getAllGameMonitors().get(i));
                j++;
            }
        }
        for (int i = 0; i < currentData.getAllGameControllers().size(); i++) {
            if (currentData.getAllGameControllers().get(i).getName().toLowerCase().
                    startsWith(search.toLowerCase())) {
                System.out.println(j + "." + currentData.getAllGameControllers().get(i).getName());
                controllers.add(currentData.getAllGameControllers().get(i));
                j++;
            }
        }
    }

    public void gameSearchAndBuy(Data currentData, User currentUser, StopWatch stopWatch) {
        ArrayList<Integer> indexOfMatchedGames = new ArrayList<>();
        System.out.println("--game search--");
        System.out.println("search a game:");
        Scanner sc = new Scanner(System.in);
        String searchString = sc.nextLine();
        int j = 1;
        for (int i = 0; i < currentData.getAllGames().size(); i++) {
            if (currentData.getAllGames().get(i).getName().toLowerCase().startsWith(searchString.toLowerCase())) {
                System.out.println(j + "." + currentData.getAllGames().get(i).getName());
                indexOfMatchedGames.add(i);
                j++;
            }
        }
        if (j > 1) {
            System.out.println("choose a number:");
            int enteredNum = sc.nextInt();
            Game selectedGame = currentData.getAllGames().get(indexOfMatchedGames.get(enteredNum - 1));
            currentUser.getUserGameShop().viewGameInfo(indexOfMatchedGames.get(enteredNum - 1) + 1,
                    currentData.getAllGames());
            if (currentUser.getUserGameShop().gameAlreadyExists(currentUser, selectedGame)) {
                System.out.println("GAME ALREADY BOUGHT!");
                System.out.println("1.back to store page");
                int num = sc.nextInt();
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else {
                buyGames(currentData, currentUser, selectedGame, stopWatch);
            }
        } else {
            System.out.println("NO GAMES WITH THIS NAME!");
            currentUser.storePage(currentData, currentUser, stopWatch);
        }
    }

    public void monitorSearchAndBuy(Data currentData, User currentUser, StopWatch stopWatch) {
        ArrayList<Integer> indexOfMatchedMonitors = new ArrayList<>();
        System.out.println("--monitor search--");
        System.out.println("search a monitor:");
        Scanner sc = new Scanner(System.in);
        String searchString = sc.nextLine();
        int j = 1;
        for (int i = 0; i < currentData.getAllGameMonitors().size(); i++) {
            if (currentData.getAllGameMonitors().get(i).getName().toLowerCase().
                    startsWith(searchString.toLowerCase())) {
                System.out.println(j + "." + currentData.getAllGameMonitors().get(i).getName());
                indexOfMatchedMonitors.add(i);
                j++;
            }
        }
        if (j > 1) {
            System.out.println("choose a number:");
            int enteredNum = sc.nextInt();
            GameMonitor selectedMonitor = currentData.getAllGameMonitors().get(indexOfMatchedMonitors.get(enteredNum - 1));
            currentUser.getUserGameShop().viewAccessoryInfo(selectedMonitor);
            buyMonitor(currentData, currentUser, selectedMonitor, stopWatch);
        } else {
            System.out.println("NO MONITORS WITH THIS NAME!");
            currentUser.storePage(currentData, currentUser, stopWatch);
        }
    }

    public void controllerSearchAndBuy(Data currentData, User currentUser, StopWatch stopWatch) {
        ArrayList<Integer> indexOfMatchedControllers = new ArrayList<>();
        System.out.println("--controller search--");
        System.out.println("search a controller:");
        Scanner sc = new Scanner(System.in);
        String searchString = sc.nextLine();
        int j = 1;
        for (int i = 0; i < currentData.getAllGameControllers().size(); i++) {
            if (currentData.getAllGameControllers().get(i).getName().toLowerCase().
                    startsWith(searchString.toLowerCase())) {
                System.out.println(j + "." + currentData.getAllGameControllers().get(i).getName());
                indexOfMatchedControllers.add(i);
                j++;
            }
        }
        if (j > 1) {
            System.out.println("choose a number:");
            int enteredNum = sc.nextInt();
            GameController selectedController = currentData.getAllGameControllers().
                    get(indexOfMatchedControllers.get(enteredNum - 1));
            currentUser.getUserGameShop().viewAccessoryInfo(selectedController);
            buyController(currentData, currentUser, selectedController, stopWatch);
        } else {
            System.out.println("NO CONTROLLERS WITH THIS NAME!");
            currentUser.storePage(currentData, currentUser, stopWatch);
        }
    }

    public void priceFilterAndBuyGames(Data currentData, User currentUser, StopWatch stopWatch) {
        ArrayList<Integer> indexOfMatchedGames = new ArrayList<>();
        System.out.println("--price filter--");
        System.out.println("least:");
        Scanner sc = new Scanner(System.in);
        int leastPrice = sc.nextInt();
        System.out.println("at most:");
        int atMostPrice = sc.nextInt();
        int j = 1;
        for (int i = 0; i < currentData.getAllGames().size(); i++) {
            if (currentData.getAllGames().get(i).getCost() >= leastPrice && currentData.getAllGames()
                    .get(i).getCost() <= atMostPrice) {
                System.out.println(j + "." + currentData.getAllGames().get(i).getName());
                indexOfMatchedGames.add(i);
                j++;
            }
        }
        if (j > 1) {
            System.out.println("choose a number:");
            int enteredNum = sc.nextInt();
            viewGameInfo(indexOfMatchedGames.get(enteredNum - 1) + 1, currentData.getAllGames());
            Game selectedGame = currentData.getAllGames().get(indexOfMatchedGames.get(enteredNum - 1));
            if (gameAlreadyExists(currentUser, selectedGame)) {
                System.out.println("GAME ALREADY BOUGHT!");
                System.out.println("1.back to store page");
                int num = sc.nextInt();
                currentUser.storePage(currentData, currentUser, stopWatch);
            } else {
                buyGames(currentData, currentUser, selectedGame, stopWatch);
            }
        } else {
            System.out.println("NO GAMES IN THE SELECTED LIMIT!");
            priceFilterAndBuyGames(currentData, currentUser, stopWatch);
        }
    }

    public void costCalculator(ArrayList<GameGenerality> selectedProducts) {
        double finalCost = 0;
        for (int i = 0; i < selectedProducts.size(); i++) {
            finalCost = finalCost + selectedProducts.get(i).getCost();
        }
        System.out.println("final cost: " + finalCost);
    }
}