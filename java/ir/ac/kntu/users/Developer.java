package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.*;
import ir.ac.kntu.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Developer extends SpecialUser implements Comparable<Developer> {

    private ArrayList<Game> developedGames;

    private ArrayList<Game> scheduledEvents;

    public Developer(SpecialUserProfile specialUserProfile, ArrayList<Game> developedGames,
                     ArrayList<Game> scheduledEvents) {
        super(specialUserProfile);
        this.developedGames = developedGames;
        this.scheduledEvents = scheduledEvents;
    }

    public ArrayList<Game> getDevelopedGames() {
        return developedGames;
    }

    public ArrayList<Game> getScheduledEvents() {
        return scheduledEvents;
    }

    @Override
    public int compareTo(Developer developer) {
        return developer.getScheduledEvents().size() < this.scheduledEvents.size() ? 1 : -1;
    }

    public void developerMenu(Data currentData, Developer currentDeveloper) {
        System.out.println("--developer menu--");
        System.out.println("choose a number");
        System.out.println("1.add a game");
        System.out.println("2.edit my developed games");
        System.out.println("3.remove my developed games");
        System.out.println("4.view feedbacks");
        System.out.println("5.game error inbox");
        System.out.println("6.back to previous page");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            developerAddsGames(currentData, currentDeveloper);
        } else if (enteredNum == 2) {
            developerEditsGame(currentData, currentDeveloper);
        } else if (enteredNum == 3) {
            developerRemovesGame(currentData, currentDeveloper);
        } else if (enteredNum == 4) {
            developerViewsFeedbacks(currentData, currentDeveloper);
        } else if (enteredNum == 5) {
            checkInboxErrors(currentData, currentDeveloper);
        } else if (enteredNum == 6) {
            Main.logIn(currentData);
        }
    }

    public void developerAddsGames(Data currentData, Developer currentDeveloper) {
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
                    0, gameReviews, gameFeedbacks, new HashMap<>(), gameGenre, 0,
                    GameType.Original);
            currentData.getAllGames().add(creatededGame);
            currentDeveloper.developedGames.add(creatededGame);
            System.out.println("GAME WAS ADDED SUCCESSFULLY!");
            addOtherDevelopers(currentData, currentDeveloper, creatededGame);
        } else if (enteredNum == 2) {
            Game creatededGame = new Game(gameName, gameDescription, gamePrice, 0, 0,
                    0, gameReviews, gameFeedbacks, new HashMap<>(), gameGenre, 0,
                    GameType.Beta);
            currentData.getAllGames().add(creatededGame);
            currentDeveloper.developedGames.add(creatededGame);
            System.out.println("GAME WAS ADDED SUCCESSFULLY!");
            addOtherDevelopers(currentData, currentDeveloper, creatededGame);
        }
    }

    public void addOtherDevelopers(Data currentData, Developer currentDeveloper, Game createdGame) {
        System.out.println("choose a number");
        System.out.println("1.select a developer to be able to edit or remove this game");
        System.out.println("2.back to developer menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1) {
            currentDeveloper.chooseDevelopers(currentData, currentDeveloper, createdGame);
        } else if (enteredNum == 2) {
            currentDeveloper.developerMenu(currentData, currentDeveloper);
        }
    }

    public void chooseDevelopers(Data currentData, Developer currentDeveloper, Game createdGame) {
        System.out.println("--developer selecting--");
        System.out.println("select a developer:");
        int j = 1;
        ArrayList<Developer> developerOptions = new ArrayList<>();
        for (int i = 0; i < currentData.getAllDevelopers().size(); i++) {
            if (currentData.getAllDevelopers().get(i).equals(currentDeveloper)) {
                continue;
            } else {
                System.out.println(j + "." + currentData.getAllDevelopers().get(i).specialUserProfile.getUsername());
                developerOptions.add(currentData.getAllDevelopers().get(i));
                j++;
            }
        }
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        developerOptions.get(enteredNum - 1).developedGames.add(createdGame);
        developerMenu(currentData, currentDeveloper);
    }

    public void developerEditsGame(Data currentData, Developer currentDeveloper) {
        ArrayList<Integer> indexOfMatchedGames = new ArrayList<>();
        System.out.println("--game editing--");
        System.out.println("search the name of the game:");
        Scanner sc = new Scanner(System.in);
        String gameName = sc.nextLine();
        int j = 1;
        for (int i = 0; i < currentDeveloper.developedGames.size(); i++) {
            if (currentDeveloper.developedGames.get(i).getName().toLowerCase().startsWith(gameName.toLowerCase())) {
                System.out.println(j + "." + currentDeveloper.developedGames.get(i).getName());
                indexOfMatchedGames.add(i);
                j++;
            }
        }
        if (j > 1) {
            System.out.println("choose a number:");
            int enteredNum = sc.nextInt();
            Game selectedGame = currentDeveloper.developedGames.get(indexOfMatchedGames.get(enteredNum - 1));
            currentDeveloper.printOptionsOfEditing(selectedGame);
            int enteredNum2 = sc.nextInt();
            if (enteredNum2 == 1) {
                currentDeveloper.nameEditing(selectedGame);
                currentDeveloper.developerMenu(currentData, currentDeveloper);
            } else if (enteredNum2 == 2) {
                currentDeveloper.genreEditing(selectedGame);
                currentDeveloper.developerMenu(currentData, currentDeveloper);
            } else if (enteredNum2 == 3) {
                currentDeveloper.descriptionEditing(selectedGame);
                currentDeveloper.developerMenu(currentData, currentDeveloper);
            } else if (enteredNum2 == 4) {
                currentDeveloper.priceEditing(selectedGame);
                currentDeveloper.developerMenu(currentData, currentDeveloper);
            } else if (enteredNum2 == 5) {
                currentDeveloper.developerMenu(currentData, currentDeveloper);
            }
        } else {
            System.out.println("NO GAMES WITH THIS NAME!");
            currentDeveloper.developerMenu(currentData, currentDeveloper);
        }
    }

    public void developerRemovesGame(Data currentData, Developer currentDeveloper) {
        ArrayList<Integer> indexOfMatchedGames = new ArrayList<>();
        System.out.println("--game removing--");
        System.out.println("search the name of the game:");
        Scanner sc = new Scanner(System.in);
        String gameName = sc.nextLine();
        int j = 1;
        for (int i = 0; i < currentDeveloper.developedGames.size(); i++) {
            if (currentDeveloper.developedGames.get(i).getName().toLowerCase().startsWith(gameName.toLowerCase())) {
                System.out.println(j + "." + currentDeveloper.developedGames.get(i).getName());
                indexOfMatchedGames.add(i);
                j++;
            }
        }
        if (j > 1) {
            System.out.println("choose a number:");
            int enteredNum = sc.nextInt();
            Game selectedGame = currentDeveloper.developedGames.get(indexOfMatchedGames.get(enteredNum - 1));
            currentData.getAllGames().remove(selectedGame);
            for (int i = 0; i < currentData.getAllUsers().size(); i++) {
                currentData.getAllUsers().get(i).getMyGames().remove(selectedGame);
            }
            System.out.println("GAME WAS REMOVED SUCCESSFULLY!");
            currentDeveloper.developerMenu(currentData, currentDeveloper);
        } else {
            System.out.println("NO GAMES WITH THIS NAME!");
            currentDeveloper.developerMenu(currentData, currentDeveloper);
        }
    }

    public void developerViewsFeedbacks(Data currentData, Developer currentDeveloper) {
        int j = 1;
        ArrayList<Game> betaGames = new ArrayList<>();
        for (int i = 0; i < currentDeveloper.developedGames.size(); i++) {
            if (currentDeveloper.developedGames.get(i).getGameType().equals(GameType.Beta)) {
                betaGames.add(currentDeveloper.developedGames.get(i));
            }
        }
        for (int i = 0; i < betaGames.size(); i++) {
            System.out.println(j + "." + betaGames.get(i).getName());
            j++;
        }
        if (j > 1) {
            System.out.println("choose a number:");
            Scanner sc = new Scanner(System.in);
            int enteredNum = sc.nextInt();
            Game selectedGame = betaGames.get(enteredNum - 1);
            System.out.println(selectedGame.getName() + "'s feedbacks:");
            for (int i = 0; i < selectedGame.getGameFeedbacks().size(); i++) {
                System.out.println(selectedGame.getGameFeedbacks().get(i));
            }
            System.out.println("1.back to developer menu");
            int enteredNum2 = sc.nextInt();
            currentDeveloper.developerMenu(currentData, currentDeveloper);
        } else {
            System.out.println("YOU HAVEN'T DEVELOPED ANY BETA GAMES!");
            currentDeveloper.developerMenu(currentData, currentDeveloper);
        }
    }

    public void checkInboxErrors(Data currentData, Developer currentDeveloper) {
        System.out.println("--inbox errors--");
        if (currentDeveloper.scheduledEvents.size() > 0) {
            System.out.println("choose a number:");
            for (int i = 0; i < currentDeveloper.getScheduledEvents().size(); i++) {
                System.out.println(i + 1 + "." + currentDeveloper.getScheduledEvents().get(i).getName());
            }
            Scanner sc = new Scanner(System.in);
            int enteredNum = sc.nextInt();
            Game selectedGame = currentDeveloper.getScheduledEvents().get(enteredNum - 1);
            System.out.println("1.confirm fixing " + selectedGame.getName() + "'s error");
            System.out.println("2.decline fixing " + selectedGame.getName() + "'s error");
            int enteredNum2 = sc.nextInt();
            if (enteredNum2 == 1) {
                currentDeveloper.getScheduledEvents().remove(selectedGame);
                currentData.getAllGames().add(selectedGame);
                System.out.println("ERROR FIXED!");
                currentDeveloper.developerMenu(currentData, currentDeveloper);
            } else if (enteredNum2 == 2) {
                Collections.sort(currentData.getAllDevelopers());
                if (indexOfDeveloper(currentData, currentDeveloper) < currentData.getAllDevelopers().size() - 1) {
                    currentData.getAllDevelopers().get(indexOfDeveloper(currentData, currentDeveloper) + 1).
                            scheduledEvents.add(selectedGame);
                    currentDeveloper.scheduledEvents.remove(selectedGame);
                } else {
                    currentData.getAllDevelopers().get(0).getScheduledEvents().add(selectedGame);
                    currentDeveloper.scheduledEvents.remove(selectedGame);
                }
                System.out.println("ERROR REPORTED TO ANOTHER DEVELOPER!");
                System.out.println("1.back to developer menu");
                int enteredNum3 = sc.nextInt();
                developerMenu(currentData, currentDeveloper);
            }
        } else {
            System.out.println("YOU DON'T HAVE ANY REPORTS!");
            currentDeveloper.developerMenu(currentData, currentDeveloper);
        }
    }

    public int indexOfDeveloper(Data currentData, Developer developer) {
        for (int i = 0; i < currentData.getAllDevelopers().size(); i++) {
            if (currentData.getAllDevelopers().get(i).equals(developer)) {
                return i;
            }
        }
        return 0;
    }
}