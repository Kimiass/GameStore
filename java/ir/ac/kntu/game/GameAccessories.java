package ir.ac.kntu.game;

import ir.ac.kntu.users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GameAccessories extends GameGenerality {
    int stock;

    public GameAccessories(String name, String description, double cost, int trendProduct, double averageRating,
                           int numberOfRates, ArrayList<String> gameReviews, ArrayList<String> gameFeedbacks,
                           HashMap<User, Double> userRates, int stock) {
        super(name, description, cost, trendProduct, averageRating, numberOfRates, gameReviews,
                gameFeedbacks, userRates);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void accessoryBought() {
        setStock(getStock() - 1);
    }
}
