package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.*;
import ir.ac.kntu.Main;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User implements Comparable<User> {
    private UserProfile userProfile;

    private ArrayList<Game> myGames;

    HashMap<GameController, Integer> myControllers;

    HashMap<GameMonitor, Integer> myMonitors;

    private UserGameShop userGameShop;

    private ArrayList<User> myFriends;

    private ArrayList<User> requests;

    private UserConnection userConnection;

    private UserViews userViews;

    public User(UserProfile userProfile, ArrayList<Game> myGames, HashMap<GameController, Integer> myControllers,
                HashMap<GameMonitor, Integer> myMonitors, UserGameShop userGameShop, ArrayList<User> myFriends,
                ArrayList<User> requests, UserConnection userConnection, UserViews userViews) {
        this.userProfile = userProfile;
        this.myGames = myGames;
        this.myControllers = myControllers;
        this.myMonitors = myMonitors;
        this.userGameShop = userGameShop;
        this.myFriends = myFriends;
        this.requests = requests;
        this.userConnection = userConnection;
        this.userViews = userViews;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public ArrayList<Game> getMyGames() {
        return myGames;
    }

    public void addToMyGames(Game game) {
        myGames.add(game);
    }

    public UserGameShop getUserGameShop() {
        return userGameShop;
    }

    public ArrayList<User> getMyFriends() {
        return myFriends;
    }

    public void addFriend(User friend) {
        myFriends.add(friend);
    }

    public ArrayList<User> getRequests() {
        return requests;
    }

    public void addRequest(User request) {
        requests.add(request);
    }

    public UserConnection getUserConnection() {
        return userConnection;
    }

    public HashMap<GameController, Integer> getMyControllers() {
        return myControllers;
    }

    public HashMap<GameMonitor, Integer> getMyMonitors() {
        return myMonitors;
    }

    public UserViews getUserViews() {
        return userViews;
    }

    @Override
    public int compareTo(User user) {
        return user.getUserProfile().getScore() > this.userProfile.getScore() ? 1 : -1;
    }

    public void userMenu(Data currentData, User currentUser, StopWatch stopWatch) {
        System.out.println("--user menu--");
        System.out.println("choose a number:");
        System.out.println("1.profile");
        System.out.println("2.store");
        System.out.println("3.trend products");
        System.out.println("4.library");
        System.out.println("5.friends");
        System.out.println("6.calculate selected items payment");
        System.out.println("7.sign out");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            currentUser.profilePage(currentData, currentUser, stopWatch);
        } else if (enteredNum == 2) {
            storePage(currentData, currentUser, stopWatch);
        } else if (enteredNum == 3) {
            currentUser.getUserGameShop().viewTrendProducts(currentData, currentUser, stopWatch);
        } else if (enteredNum == 4) {
            libraryPage(currentData, currentUser, stopWatch);
        } else if (enteredNum == 5) {
            friendsPage(currentData, currentUser, stopWatch);
        } else if (enteredNum == 6) {
            selectItemsToPay(currentData, currentUser, stopWatch);
        } else if (enteredNum == 7) {
            stopWatch.stop();
            currentUser.getUserProfile().setScore(currentUser.getUserProfile().getScore()
                    + stopWatch.getTime() / 5000);
            Main.logIn(currentData);
        }
    }

    public void profilePage(Data data, User currentUser, StopWatch stopWatch) {
        System.out.println("--profile page--");
        System.out.println("choose a number:");
        System.out.println("1.view profile");
        System.out.println("2.edit profile");
        System.out.println("3.add money to your account");
        System.out.println("4.back to user menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            currentUser.getUserProfile().viewProfile(data);
            System.out.println("1.back to profile page");
            int num = sc.nextInt();
            profilePage(data, currentUser, stopWatch);
        } else if (enteredNum == 2) {
            currentUser.getUserProfile().editProfile(data);
            System.out.println("1.back to profile page");
            int num = sc.nextInt();
            profilePage(data, currentUser, stopWatch);
        } else if (enteredNum == 3) {
            System.out.println("how much money do you want to add?");
            int addedMoney = sc.nextInt();
            currentUser.getUserProfile().addMoney(addedMoney);
            System.out.println("MONEY WAS ADDED SUCCESSFULLY TO YOUR WALLET!");
            System.out.println("1.back to profile page");
            int num = sc.nextInt();
            profilePage(data, currentUser, stopWatch);
        } else if (enteredNum == 4) {
            userMenu(data, currentUser, stopWatch);
        }
    }

    public void storePage(Data currentData, User currentUser, StopWatch stopWatch) {
        System.out.println("--store page--");
        System.out.println("choose a number:");
        System.out.println("1.view all games and accessories");
        System.out.println("2.search games and accessories");
        System.out.println("3.price filter");
        System.out.println("4.back to user menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            System.out.println("--all games and accessories--");
            System.out.println("choose a number:");
            currentUser.userGameShop.viewAllProducts(currentData);
            int enteredNum2 = sc.nextInt();
            if (enteredNum2 <= currentData.getAllGames().size()) {
                Game selectedGame = currentData.getAllGames().get(enteredNum2 - 1);
                currentUser.userGameShop.viewGameInfo(enteredNum2, currentData.getAllGames());
                if (currentUser.userGameShop.gameAlreadyExists(currentUser, selectedGame)) {
                    System.out.println("GAME ALREADY BOUGHT!");
                    System.out.println("1.back to store page");
                    int num = sc.nextInt();
                    storePage(currentData, currentUser, stopWatch);
                } else {
                    currentUser.getUserGameShop().buyGames(currentData, currentUser, selectedGame, stopWatch);
                }
            } else if (enteredNum2 > currentData.getAllGames().size() &&
                    enteredNum2 <= currentData.getAllGames().size() + currentData.getAllGameMonitors().size()) {
                GameMonitor selectedMonitor = currentData.getAllGameMonitors().
                        get(enteredNum2 - currentData.getAllGames().size() - 1);
                currentUser.userGameShop.viewAccessoryInfo(selectedMonitor);
                currentUser.userGameShop.buyMonitor(currentData, currentUser, selectedMonitor, stopWatch);
            } else {
                GameController selectedController = currentData.getAllGameControllers().get(enteredNum2
                        - currentData.getAllGames().size() - currentData.getAllGameMonitors().size() - 1);
                currentUser.userGameShop.viewAccessoryInfo(selectedController);
                currentUser.userGameShop.buyController(currentData, currentUser, selectedController, stopWatch);
            }
        } else if (enteredNum == 2) {
            currentUser.getUserGameShop().searchGames(currentData, currentUser, stopWatch);
        } else if (enteredNum == 3) {
            currentUser.getUserGameShop().priceFilterAndBuyGames(currentData, currentUser, stopWatch);
        } else if (enteredNum == 4) {
            currentUser.userMenu(currentData, currentUser, stopWatch);
        }
    }

    public void libraryPage(Data currentData, User currentUser, StopWatch stopWatch) {
        System.out.println("--library page--");
        if (currentUser.getMyGames().size() + currentUser.getMyMonitors().size() +
                currentUser.getMyControllers().size() > 0) {
            System.out.println("your current games and accessories");
            System.out.println("choose a number:");
            Scanner sc = new Scanner(System.in);
            currentUser.getUserGameShop().viewMyProducts(currentUser);
            int enteredNum = sc.nextInt();
            if (enteredNum <= currentUser.getMyGames().size()) {
                Game selectedGame = currentUser.getMyGames().get(enteredNum - 1);
                gameLibrary(currentData, currentUser, selectedGame, enteredNum, stopWatch);
            } else if (enteredNum > currentUser.getMyGames().size() && enteredNum <= currentUser.getMyGames().size() +
                    currentUser.getMyMonitors().size()) {
                ArrayList<GameMonitor> monitors = new ArrayList<>();
                for (GameMonitor gameMonitor : currentUser.getMyMonitors().keySet()) {
                    monitors.add(gameMonitor);
                }
                GameMonitor gameMonitor = monitors.get(enteredNum - currentUser.getMyGames().size() - 1);
                currentUser.userGameShop.viewAccessoryInfo(gameMonitor);
                currentUser.accessoryLibrary(currentData, currentUser, gameMonitor, stopWatch);
            } else {
                ArrayList<GameController> controllers = new ArrayList<>();
                for (GameController gameController : currentUser.getMyControllers().keySet()) {
                    controllers.add(gameController);
                }
                GameController gameController = controllers.get(enteredNum - currentUser.getMyGames().size() -
                        currentUser.getMyMonitors().size() - 1);
                currentUser.userGameShop.viewAccessoryInfo(gameController);
                currentUser.accessoryLibrary(currentData, currentUser, gameController, stopWatch);
            }
        } else {
            System.out.println("SORRY BUT YOU DON'T HAVE ANY GAMES!");
            userMenu(currentData, currentUser, stopWatch);
        }
    }

    public void gameLibrary(Data currentData, User currentUser, Game selectedGame,
                            int enteredNum, StopWatch stopWatch) {
        currentUser.getUserGameShop().viewGameInfo(enteredNum, currentUser.getMyGames());
        Scanner sc = new Scanner(System.in);
        if (selectedGame.getGameType().equals(GameType.Original)) {
            System.out.println("1.community");
            System.out.println("2.back to user menu");
            int enteredNum2 = sc.nextInt();
            if (enteredNum2 == 1) {
                System.out.println("--rating and reviews--");
                System.out.println("reviews:");
                for (int i = 0; i < selectedGame.getGameReviews().size(); i++) {
                    System.out.println(selectedGame.getGameReviews().get(i));
                }
                System.out.println("average rate: " + selectedGame.getAverageRating());
                currentUser.getUserViews().userRateOrReviewOnGame(currentData, currentUser,
                        selectedGame, stopWatch);
            } else if (enteredNum2 == 2) {
                userMenu(currentData, currentUser, stopWatch);
            }
        } else if (selectedGame.getGameType().equals(GameType.Beta)) {
            System.out.println("1.feedback");
            System.out.println("2.back to user menu");
            int enteredNum2 = sc.nextInt();
            if (enteredNum2 == 1) {
                currentUser.userViews.userAddsFeedback(currentData, currentUser, selectedGame, stopWatch);
            } else if (enteredNum2 == 2) {
                userMenu(currentData, currentUser, stopWatch);
            }
        }
    }

    public void accessoryLibrary(Data currentData, User currentUser, GameAccessories selectedAccessory,
                                 StopWatch stopWatch) {
        System.out.println("1.community");
        System.out.println("2.back to user menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum2 = sc.nextInt();
        if (enteredNum2 == 1) {
            System.out.println("--rating and reviews--");
            System.out.println("reviews:");
            for (int i = 0; i < selectedAccessory.getGameReviews().size(); i++) {
                System.out.println(selectedAccessory.getGameReviews().get(i));
            }
            System.out.println("average rate: " + selectedAccessory.getAverageRating());
            currentUser.getUserViews().userRateOrReviewOnAccessory(currentData, currentUser,
                    selectedAccessory, stopWatch);
        } else if (enteredNum2 == 2) {
            userMenu(currentData, currentUser, stopWatch);
        }
    }

    public void friendsPage(Data data, User currentUser, StopWatch stopWatch) {
        System.out.println("--friends page--");
        System.out.println("choose a number:");
        System.out.println("1.view all friends");
        System.out.println("2.search a friend");
        System.out.println("3.request a user");
        System.out.println("4.view requests");
        System.out.println("5.back to user menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            currentUser.userConnection.viewAllFriends(data, currentUser, stopWatch);
        } else if (enteredNum == 2) {
            currentUser.userConnection.searchFriends(data, currentUser, stopWatch);
        } else if (enteredNum == 3) {
            currentUser.userConnection.requestFriends(data, currentUser, stopWatch);
        } else if (enteredNum == 4) {
            currentUser.userConnection.viewRequests(data, currentUser, stopWatch);
        } else if (enteredNum == 5) {
            userMenu(data, currentUser, stopWatch);
        }
    }

    public void selectItemsToPay(Data currentData, User currentUser, StopWatch stopWatch) {
        currentUser.getUserGameShop().viewAllProducts(currentData);
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
        System.out.println("select your items:");
        System.out.println("enter 0 after selecting");
        ArrayList<GameGenerality> selectedProducts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        while (enteredNum != 0) {
            selectedProducts.add(allProducts.get(enteredNum - 1));
            enteredNum = sc.nextInt();
        }
        currentUser.getUserGameShop().costCalculator(selectedProducts);
        System.out.println("1.back to user menu");
        int enteredNum2 = sc.nextInt();
        currentUser.userMenu(currentData, currentUser, stopWatch);
    }
}