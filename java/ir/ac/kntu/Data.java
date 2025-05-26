package ir.ac.kntu;

import ir.ac.kntu.game.*;
import ir.ac.kntu.users.*;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Data {
    private ArrayList<User> allUsers;

    private ArrayList<Admin> allAdmins;

    private ArrayList<Developer> allDevelopers;

    private ArrayList<AccessorySeller> allSellers;

    private ArrayList<Game> allGames;

    private ArrayList<GameController> allGameControllers;

    private ArrayList<GameMonitor> allGameMonitors;

    public Data() {
        allUsers = new ArrayList<>();
        allAdmins = new ArrayList<>();
        allDevelopers = new ArrayList<>();
        allSellers = new ArrayList<>();
        allGames = new ArrayList<>();
        allGameControllers = new ArrayList<>();
        allGameMonitors = new ArrayList<>();
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void addUser(User user) {
        allUsers.add(user);
    }

    public ArrayList<Admin> getAllAdmins() {
        return allAdmins;
    }

    public void addAdmin(Admin admin) {
        allAdmins.add(admin);
    }

    public ArrayList<Developer> getAllDevelopers() {
        return allDevelopers;
    }

    public void addDeveloper(Developer developer) {
        allDevelopers.add(developer);
    }

    public ArrayList<AccessorySeller> getAllSellers() {
        return allSellers;
    }

    public void addSeller(AccessorySeller accessorySeller) {
        allSellers.add(accessorySeller);
    }

    public ArrayList<Game> getAllGames() {
        return allGames;
    }

    public void addGame(Game game) {
        allGames.add(game);
    }

    public ArrayList<GameController> getAllGameControllers() {
        return allGameControllers;
    }

    public ArrayList<GameMonitor> getAllGameMonitors() {
        return allGameMonitors;
    }

    public void addGameController(GameController gameController) {
        allGameControllers.add(gameController);
    }

    public void addGameMonitor(GameMonitor gameMonitor) {
        allGameMonitors.add(gameMonitor);
    }

    public void addDefaultGames() {
        ArrayList<String> game1Reviews = new ArrayList<>();
        ArrayList<String> game2Reviews = new ArrayList<>();
        ArrayList<String> game3Reviews = new ArrayList<>();
        ArrayList<String> game4Reviews = new ArrayList<>();
        ArrayList<String> game1Feedback = new ArrayList<>();
        ArrayList<String> game2Feedback = new ArrayList<>();
        ArrayList<String> game3Feedback = new ArrayList<>();
        ArrayList<String> game4Feedback = new ArrayList<>();
        game1Reviews.add("Coolest shooting game ever!");
        game1Reviews.add("Interesting...great graphics...");
        game2Reviews.add("So realistic, best game ever!");
        game2Reviews.add("one of the best racing games i've played so far!");
        game3Reviews.add("The worst! I'll just hit the uninstall button.");
        game3Reviews.add("It's a fun survival game!");
        game4Reviews.add("It takes my mind off all the worries of the day, love it!!");
        game4Reviews.add("A little too simplistic.");
        Game game1 = new Game("Sniper Fury", "If you're looking for some online sniper action," +
                " look no further! No running, just shooting!", 105, 3, 4.1, 10,
                game1Reviews, game1Feedback, new HashMap<>(), "shooter", 3, GameType.Beta);
        Game game2 = new Game("Asphalt", "Race with the most amazing speed machines handpicked " +
                "among world-renown manufacturers!", 750, 2, 4.5, 16,
                game2Reviews, game2Feedback, new HashMap<>(), "racing & flying", 2, GameType.Original);
        Game game3 = new Game("Grounded", "The world is a vast, beautiful, and dangerous place –" +
                " especially when you have been shrunken to the size of an ant!", 95, 0, 3.4,
                8, game3Reviews, game3Feedback, new HashMap<>(), "action & adventure", 1,
                GameType.Original);
        Game game4 = new Game("Wizard of Bingo", "It's time to learn some magic spells and we're" +
                " bringing the most powerful magic bingo contest!", 150, 0, 4.8,
                12, game4Reviews, game4Feedback, new HashMap<>(), "card & board", 4,
                GameType.Beta);
        addGame(game1);
        addGame(game2);
        addGame(game3);
        addGame(game4);
    }

    public void addDefaultUsers() {
        UserProfile profile1 = new UserProfile("kimiaSadeghi", "246810Kim",
                "0912076", "kimias@gmail", 400, 60);
        UserProfile profile2 = new UserProfile("hastiii", "h12345678H",
                "0912384", "hastighodsi@gmail", 300, 30);
        UserProfile profile3 = new UserProfile("hedie", "hedi1234X",
                "0912629", "hedieghodsi@gmail", 700, 15);
        ArrayList<Game> user1Games = new ArrayList<>();
        ArrayList<Game> user2Games = new ArrayList<>();
        ArrayList<Game> user3Games = new ArrayList<>();
        ArrayList<User> user1Friends = new ArrayList<>();
        ArrayList<User> user2Friends = new ArrayList<>();
        ArrayList<User> user3Friends = new ArrayList<>();
        ArrayList<User> user1Requests = new ArrayList<>();
        ArrayList<User> user2Requests = new ArrayList<>();
        ArrayList<User> user3Requests = new ArrayList<>();
        User user1 = new User(profile1, user1Games, new HashMap<>(), new HashMap<>(), new UserGameShop(),
                user1Friends, user1Requests, new UserConnection(), new UserViews());
        User user2 = new User(profile2, user2Games, new HashMap<>(), new HashMap<>(),
                new UserGameShop(), user2Friends, user2Requests, new UserConnection(), new UserViews());
        User user3 = new User(profile3, user3Games, new HashMap<>(), new HashMap<>(),
                new UserGameShop(), user3Friends, user3Requests, new UserConnection(), new UserViews());
        user1.addToMyGames(allGames.get(2));
        user1.addToMyGames(allGames.get(3));
        user2.addToMyGames(allGames.get(0));
        user3.addToMyGames(allGames.get(1));
        user3.addToMyGames(allGames.get(2));
        user1.addFriend(user3);
        user2.addRequest(user3);
        addUser(user1);
        addUser(user2);
        addUser(user3);
    }

    public void addDefaultAdmin() {
        SpecialUserProfile specialUserProfile1 = new SpecialUserProfile("soroosh",
                "Ssh12345", "0912475",
                "srezai@gmail");
        SpecialUserProfile specialUserProfile2 = new SpecialUserProfile("yasamin", "Yasamin1234",
                "0912756", "yasRiahi@gmail");
        AdminUserController adminUserController1 = new AdminUserController();
        AdminUserController adminUserController2 = new AdminUserController();
        Admin admin1 = new Admin(specialUserProfile1, adminUserController1);
        Admin admin2 = new Admin(specialUserProfile2, adminUserController2);
        addAdmin(admin1);
        addAdmin(admin2);
    }

    public void addDefaultDeveloper() {
        ArrayList<Game> developedGames1 = new ArrayList<>();
        developedGames1.add(allGames.get(2));
        developedGames1.add(allGames.get(3));
        Developer developer1 = new Developer(allAdmins.get(0).getSpecialUserProfile(),
                developedGames1, new ArrayList<>());
        addDeveloper(developer1);
        ArrayList<Game> developedGames2 = new ArrayList<>();
        developedGames2.add(allGames.get(1));
        Developer developer2 = new Developer(allAdmins.get(1).getSpecialUserProfile(),
                developedGames2, new ArrayList<>());
        addDeveloper(developer2);
        SpecialUserProfile specialUserProfile3 = new SpecialUserProfile("ali", "A0000ali",
                "0912587", "aliAbdi@gmail");
        ArrayList<Game> developedGames3 = new ArrayList<>();
        developedGames3.add(allGames.get(0));
        Developer developer3 = new Developer(specialUserProfile3, developedGames3, new ArrayList<>());
        addDeveloper(developer3);
    }

    public void addDefaultAccessorySeller() {
        AccessorySeller accessorySeller1 = new AccessorySeller(allAdmins.get(0).getSpecialUserProfile(),
                new ArrayList<>(), new ArrayList<>());
        AccessorySeller accessorySeller2 = new AccessorySeller(allAdmins.get(1).getSpecialUserProfile(),
                new ArrayList<>(), new ArrayList<>());
        addSeller(accessorySeller1);
        addSeller(accessorySeller2);
    }

    public void addDefaultAccessories() {
        ArrayList<String> reviews = new ArrayList<>();
        reviews.add("best quality!");
        reviews.add("could be better if it saved more battery!");
        ArrayList<String> feedback = new ArrayList<>();
        GameController gameController = new GameController("PSController", "control characters",
                400, 0, 4, 8, reviews, feedback, new HashMap<>(), 6,
                "sony", ControllerType.Wireless);
        GameMonitor gameMonitor = new GameMonitor("HDMonitor", "displays HD images", 450,
                0, 3, 6, reviews, feedback, new HashMap<>(), 4,
                200, 12, 0.5);
        addGameController(gameController);
        addGameMonitor(gameMonitor);
    }

    public void adminLogin(Data currentData) {
        System.out.println("--admin login--");
        System.out.println("choose a number:");
        System.out.println("1.previous page");
        System.out.println("2.enter admin info");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            Main.logIn(currentData);
        } else if (enteredNum == 2) {
            System.out.println("--admin login--");
            System.out.println("enter admin username:");
            String adminUser = sc.nextLine();
            adminUser = sc.nextLine();
            System.out.println("enter admin password:");
            String adminPass = sc.nextLine();
            checkAdminInfo(adminUser, adminPass, currentData);
        }
    }

    public void checkAdminInfo(String enteredUser, String enteredPassword, Data currentData) {
        boolean adminInfo = false;
        for (int i = 0; i < currentData.getAllAdmins().size(); i++) {
            adminInfo = (currentData.getAllAdmins().get(i).getSpecialUserProfile().getUsername().equals(enteredUser)
                    && currentData.getAllAdmins().get(i).getSpecialUserProfile().getPassword().equals(enteredPassword));
            if (adminInfo == true) {
                System.out.println("LOGGED IN SUCCESSFULLY!");
                Admin currentAdmin = currentData.allAdmins.get(i);
                currentAdmin.adminMenu(currentData, currentAdmin);
                break;
            }
        }
        if (adminInfo == false) {
            System.out.println("WRONG PASSWORD OR USERNAME!!");
            Main.logIn(currentData);
        }
    }

    public void developerLogin(Data currentData) {
        System.out.println("--developer login--");
        System.out.println("choose a number:");
        System.out.println("1.previous page");
        System.out.println("2.enter developer info");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            Main.logIn(currentData);
        } else if (enteredNum == 2) {
            System.out.println("--developer login--");
            System.out.println("enter developer username:");
            String developerUser = sc.nextLine();
            developerUser = sc.nextLine();
            System.out.println("enter developer password:");
            String developerPass = sc.nextLine();
            checkDeveloperInfo(developerUser, developerPass, currentData);
        }
    }

    public void checkDeveloperInfo(String enteredUser, String enteredPassword, Data currentData) {
        boolean developerInfo = false;
        for (int i = 0; i < currentData.getAllDevelopers().size(); i++) {
            developerInfo = (currentData.getAllDevelopers().get(i).getSpecialUserProfile().getUsername().
                    equals(enteredUser) && currentData.getAllDevelopers().get(i).getSpecialUserProfile().
                    getPassword().equals(enteredPassword));
            if (developerInfo == true) {
                System.out.println("LOGGED IN SUCCESSFULLY!");
                Developer currentDeveloper = currentData.allDevelopers.get(i);
                currentDeveloper.developerMenu(currentData, currentDeveloper);
                break;
            }
        }
        if (developerInfo == false) {
            System.out.println("WRONG PASSWORD OR USERNAME!!");
            Main.logIn(currentData);
        }
    }

    public void sellerLogin(Data currentData) {
        System.out.println("--accessory seller login--");
        System.out.println("choose a number:");
        System.out.println("1.previous page");
        System.out.println("2.enter seller info");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            Main.logIn(currentData);
        } else if (enteredNum == 2) {
            System.out.println("--accessory seller login--");
            System.out.println("enter seller username:");
            String sellerUser = sc.nextLine();
            sellerUser = sc.nextLine();
            System.out.println("enter seller password:");
            String sellerPass = sc.nextLine();
            checkSellerInfo(sellerUser, sellerPass, currentData);
        }
    }

    public void checkSellerInfo(String enteredUser, String enteredPassword, Data currentData) {
        boolean sellerInfo = false;
        for (int i = 0; i < currentData.getAllSellers().size(); i++) {
            sellerInfo = (currentData.getAllSellers().get(i).getSpecialUserProfile().getUsername().
                    equals(enteredUser) && currentData.getAllSellers().get(i).getSpecialUserProfile().
                    getPassword().equals(enteredPassword));
            if (sellerInfo == true) {
                System.out.println("LOGGED IN SUCCESSFULLY!");
                AccessorySeller currentSeller = currentData.allSellers.get(i);
                currentSeller.sellerMenu(currentData, currentSeller);
                break;
            }
        }
        if (sellerInfo == false) {
            System.out.println("WRONG PASSWORD OR USERNAME!!");
            Main.logIn(currentData);
        }
    }

    public void userEntry(Data currentData) {
        System.out.println("--user entry--");
        System.out.println("choose a number:");
        System.out.println("1.previous page");
        System.out.println("2.enter user info");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            Main.logIn(currentData);
        } else if (enteredNum == 2) {
            System.out.println("--user sign up/in--");
            System.out.println("choose a number:");
            System.out.println("already have an account?");
            System.out.println("YES! --> 1.sign in");
            System.out.println("NO --> 2.sign up");
            System.out.println("3.back to previous page");
            int enteredNum2 = sc.nextInt();
            if (enteredNum2 == 1) {
                userSignIn(currentData);
            } else if (enteredNum2 == 2) {
                userSignUp(currentData);
            } else if (enteredNum2 == 3) {
                Main.logIn(currentData);
            }
        }
    }

    public void userSignIn(Data currentData) {
        System.out.println("--user sign in--");
        System.out.println("enter username:");
        Scanner sc = new Scanner(System.in);
        String user = sc.nextLine();
        System.out.println("enter password:");
        String pass = sc.nextLine();
        checkUserInfo(user, pass, currentData);
    }

    public void checkUserInfo(String enteredUser, String enteredPassword, Data currentData) {
        boolean userInfo = false;
        for (int i = 0; i < currentData.getAllUsers().size(); i++) {
            userInfo = (currentData.getAllUsers().get(i).getUserProfile().getUsername().equals(enteredUser)
                    && currentData.getAllUsers().get(i).getUserProfile().getPassword().equals(enteredPassword));
            if (userInfo == true) {
                System.out.println("SIGNED IN SUCCESSFULLY!");
                User currentUser = currentData.getAllUsers().get(i);
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                currentUser.userMenu(currentData, currentUser, stopWatch);
                break;
            }
        }
        if (userInfo == false) {
            System.out.println("WRONG PASSWORD OR USERNAME!!");
            Main.logIn(currentData);
        }
    }

    public void userSignUp(Data currentData) {
        System.out.println("--user sign in--");
        System.out.println("enter username:");
        Scanner sc = new Scanner(System.in);
        String user = sc.nextLine();
        boolean repetitiveUser = false;
        for (int i = 0; i < currentData.getAllUsers().size(); i++) {
            if (currentData.getAllUsers().get(i).getUserProfile().getUsername().equals(user)) {
                System.out.println("USERNAME ALREADY EXISTS!!");
                repetitiveUser = true;
                userSignUp(currentData);
            }
        }
        if (repetitiveUser == false) {
            System.out.println("enter password:");
            String pass = sc.nextLine();
            if (pass.length() < 8) {
                System.out.println("password must include at least 8 characters!!");
                userSignUp(currentData);
            } else if (!pass.matches(".*" + "[A-Z]+" + ".*") ||
                    !pass.matches(".*" + "[a-z]+" + ".*") || !pass.matches(".*" + "[0-9]+" + ".*")) {
                System.out.println("password must include numbers, upper and lowercase letters!");
                userSignUp(currentData);
            } else {
                System.out.println("enter phone number:");
                String phoneNumber = sc.nextLine();
                System.out.println("enter email:");
                String email = sc.nextLine();
                UserProfile profile = new UserProfile(user, pass, phoneNumber, email, 0, 0);
                ArrayList<Game> myGames = new ArrayList<>();
                ArrayList<User> myFriends = new ArrayList<>();
                ArrayList<User> myRequests = new ArrayList<>();
                User newUser = new User(profile, myGames, new HashMap<>(), new HashMap<>(), new UserGameShop(),
                        myFriends, myRequests, new UserConnection(), new UserViews());
                addUser(newUser);
                System.out.println("signed up successfully!!");
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                newUser.userMenu(currentData, newUser, stopWatch);
            }
        }
    }
}