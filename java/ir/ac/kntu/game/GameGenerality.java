package ir.ac.kntu.game;

import ir.ac.kntu.users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GameGenerality implements Comparable<GameGenerality> {
    private String name;

    private String description;

    private double cost;

    private int trendProduct;

    private double averageRating;

    private int numberOfRates;

    private ArrayList<String> gameReviews;

    private ArrayList<String> gameFeedbacks;

    private HashMap<User, Double> userRates;

    public GameGenerality(String name, String description, double cost, int trendProduct, double averageRating,
                          int numberOfRates, ArrayList<String> gameReviews, ArrayList<String> gameFeedbacks,
                          HashMap<User, Double> userRates) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.trendProduct = trendProduct;
        this.averageRating = averageRating;
        this.numberOfRates = numberOfRates;
        this.gameReviews = gameReviews;
        this.gameFeedbacks = gameFeedbacks;
        this.userRates = userRates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTrendProduct() {
        return trendProduct;
    }

    public void setTrendProduct(int trendProduct) {
        this.trendProduct = trendProduct;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumberOfRates() {
        return numberOfRates;
    }

    public void setNumberOfRates(int numberOfRates) {
        this.numberOfRates = numberOfRates;
    }

    public ArrayList<String> getGameReviews() {
        return gameReviews;
    }

    public void addReview(String review) {
        gameReviews.add(review);
    }

    public ArrayList<String> getGameFeedbacks() {
        return gameFeedbacks;
    }

    public void addFeedback(String feedback) {
        gameFeedbacks.add(feedback);
    }

    public HashMap<User, Double> getUserRates() {
        return userRates;
    }

    @Override
    public int compareTo(GameGenerality gameGenerality) {
        return gameGenerality.trendProduct > this.trendProduct ? 1 : -1;
    }
}
