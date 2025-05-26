package ir.ac.kntu.game;

import ir.ac.kntu.users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends GameGenerality {
    private String genre;

    private int level;

    GameType gameType;

    public Game(String name, String description, double cost, int trendProduct, double averageRating,
                int numberOfRates, ArrayList<String> gameReviews, ArrayList<String> gameFeedbacks,
                HashMap<User, Double> userRates, String genre, int level, GameType gameType) {
        super(name, description, cost, trendProduct, averageRating, numberOfRates, gameReviews,
                gameFeedbacks, userRates);
        this.genre = genre;
        this.level = level;
        this.gameType = gameType;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
}
