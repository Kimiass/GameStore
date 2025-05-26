package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import ir.ac.kntu.game.Game;
import ir.ac.kntu.game.GameAccessories;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Scanner;

public class UserViews {
    public UserViews() {
    }

    public void userRateOrReviewOnGame(Data currentData, User currentUser, Game selectedGame, StopWatch stopWatch) {
        System.out.println("choose a number:");
        System.out.println("1.add your own rate");
        System.out.println("2.add your own review");
        System.out.println("3.back to user menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1 && selectedGame.getUserRates().containsKey(currentUser)) {
            System.out.println("enter your rate:");
            double userRate = sc.nextDouble();
            selectedGame.setAverageRating((selectedGame.getAverageRating() * selectedGame.getNumberOfRates()
                    - selectedGame.getUserRates().get(currentUser) + userRate) / (selectedGame.getNumberOfRates()));
            selectedGame.getUserRates().put(currentUser, userRate);
            System.out.println("THANKS FOR RATING!");
            currentUser.userMenu(currentData, currentUser, stopWatch);
        } else if (enteredNum == 1) {
            System.out.println("enter your rate:");
            double userRate = sc.nextDouble();
            selectedGame.setAverageRating((selectedGame.getAverageRating() * selectedGame.getNumberOfRates()
                    + userRate) / (selectedGame.getNumberOfRates() + 1));
            selectedGame.setNumberOfRates(selectedGame.getNumberOfRates() + 1);
            selectedGame.getUserRates().put(currentUser, userRate);
            System.out.println("THANKS FOR RATING!");
            currentUser.userMenu(currentData, currentUser, stopWatch);
        } else if (enteredNum == 2) {
            System.out.println("enter your review:");
            String userReview = sc.nextLine();
            userReview = sc.nextLine();
            selectedGame.getGameReviews().add(userReview);
            System.out.println("THANKS FOR ADDING A REVIEW!");
            currentUser.userMenu(currentData, currentUser, stopWatch);
        } else if (enteredNum == 3) {
            currentUser.userMenu(currentData, currentUser, stopWatch);
        }
    }

    public void userRateOrReviewOnAccessory(Data currentData, User currentUser, GameAccessories gameAccessories,
                                            StopWatch stopWatch) {
        System.out.println("choose a number:");
        System.out.println("1.add your own rate");
        System.out.println("2.add your own review");
        System.out.println("3.back to user menu");
        Scanner sc = new Scanner(System.in);
        int enteredNum = sc.nextInt();
        if (enteredNum == 1 && gameAccessories.getUserRates().containsKey(currentUser)) {
            System.out.println("enter your rate:");
            double userRate = sc.nextDouble();
            gameAccessories.setAverageRating((gameAccessories.getAverageRating() * gameAccessories.getNumberOfRates() -
                    gameAccessories.getUserRates().get(currentUser) + userRate) / (gameAccessories.getNumberOfRates()));
            gameAccessories.getUserRates().put(currentUser, userRate);
            System.out.println("THANKS FOR RATING!");
            currentUser.userMenu(currentData, currentUser, stopWatch);
        } else if (enteredNum == 1) {
            System.out.println("enter your rate:");
            double userRate = sc.nextDouble();
            gameAccessories.setAverageRating((gameAccessories.getAverageRating() * gameAccessories.getNumberOfRates()
                    + userRate) / (gameAccessories.getNumberOfRates() + 1));
            gameAccessories.setNumberOfRates(gameAccessories.getNumberOfRates() + 1);
            gameAccessories.getUserRates().put(currentUser, userRate);
            System.out.println("THANKS FOR RATING!");
            currentUser.userMenu(currentData, currentUser, stopWatch);
        } else if (enteredNum == 2) {
            System.out.println("enter your review:");
            String userReview = sc.nextLine();
            userReview = sc.nextLine();
            gameAccessories.getGameReviews().add(userReview);
            System.out.println("THANKS FOR ADDING A REVIEW!");
            currentUser.userMenu(currentData, currentUser, stopWatch);
        } else if (enteredNum == 3) {
            currentUser.userMenu(currentData, currentUser, stopWatch);
        }
    }

    public void userAddsFeedback(Data currentData, User currentUser, Game selectedGame, StopWatch stopWatch) {
        System.out.println("enter your feedback:");
        Scanner sc = new Scanner(System.in);
        String feedback = sc.nextLine();
        selectedGame.getGameFeedbacks().add(feedback);
        System.out.println("THANKS FOR ADDING A FEEDBACK!");
        currentUser.userMenu(currentData, currentUser, stopWatch);
    }
}
