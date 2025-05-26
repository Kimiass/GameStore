package ir.ac.kntu.game;

import ir.ac.kntu.users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMonitor extends GameAccessories {

    double pictureUpdate;

    double size;

    double responseTime;

    public GameMonitor(String name, String description, double cost, int trendProduct, double averageRating,
                       int numberOfRates, ArrayList<String> gameReviews, ArrayList<String> gameFeedbacks,
                       HashMap<User, Double> userRates, int stock, double pictureUpdate, double size,
                       double responseTime) {
        super(name, description, cost, trendProduct, averageRating, numberOfRates, gameReviews, gameFeedbacks,
                userRates, stock);
        this.pictureUpdate = pictureUpdate;
        this.size = size;
        this.responseTime = responseTime;
    }

    public double getPictureUpdate() {
        return pictureUpdate;
    }

    public void setPictureUpdate(double pictureUpdate) {
        this.pictureUpdate = pictureUpdate;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }
}