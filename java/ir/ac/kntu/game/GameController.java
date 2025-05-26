package ir.ac.kntu.game;

import ir.ac.kntu.users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController extends GameAccessories {

    String pairedDevice;

    ControllerType controllerType;

    public GameController(String name, String description, double cost, int trendProduct, double averageRating,
                          int numberOfRates, ArrayList<String> gameReviews, ArrayList<String> gameFeedbacks,
                          HashMap<User, Double> userRates, int stock, String pairedDevice,
                          ControllerType controllerType) {
        super(name, description, cost, trendProduct, averageRating, numberOfRates, gameReviews,
                gameFeedbacks, userRates, stock);
        this.pairedDevice = pairedDevice;
        this.controllerType = controllerType;
    }

    public String getPairedDevice() {
        return pairedDevice;
    }

    public void setPairedDevice(String pairedDevice) {
        this.pairedDevice = pairedDevice;
    }

    public ControllerType getControllerType() {
        return controllerType;
    }

    public void setControllerType(ControllerType controllerType) {
        this.controllerType = controllerType;
    }
}
