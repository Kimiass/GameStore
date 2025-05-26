package ir.ac.kntu.users;

import ir.ac.kntu.Data;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.Scanner;

public class UserConnection {
    public UserConnection() {
    }

    public void viewAllFriends(Data data, User currentUser, StopWatch stopWatch) {
        System.out.println("--view my friends--");
        int j = 1;
        for (int i = 0; i < currentUser.getMyFriends().size(); i++) {
            System.out.println(j + "." + currentUser.getMyFriends().get(i).getUserProfile().getUsername());
            j++;
        }
        if (j > 1) {
            System.out.println("choose a number:");
            Scanner sc = new Scanner(System.in);
            int enteredNum = sc.nextInt();
            User selectedFriend = currentUser.getMyFriends().get(enteredNum - 1);
            System.out.println(selectedFriend.getUserProfile().getUsername() + "'s games:");
            if (selectedFriend.getMyGames().size() + selectedFriend.getMyMonitors().size() +
                    selectedFriend.getMyControllers().size() == 0) {
                System.out.println("SELECTED FRIEND DOESN'T HAVE ANY GAMES OR ACCESSORIES!");
            } else {
                selectedFriend.getUserGameShop().viewMyProducts(selectedFriend);
            }
            System.out.println("1.back to friends page");
            int num = sc.nextInt();
            currentUser.friendsPage(data, currentUser, stopWatch);
        } else {
            System.out.println("SORRY BUT YOU DON'T HAVE ANY FRIENDS!");
            currentUser.friendsPage(data, currentUser, stopWatch);
        }
    }

    public void searchFriends(Data data, User currentUser, StopWatch stopWatch) {
        ArrayList<Integer> indexOfMatchedFriends = new ArrayList<>();
        System.out.println("--friend search--");
        System.out.println("search a friend by username:");
        Scanner sc = new Scanner(System.in);
        String enteredUser = sc.nextLine();
        int j = 1;
        for (int i = 0; i < currentUser.getMyFriends().size(); i++) {
            if (currentUser.getMyFriends().get(i).getUserProfile().getUsername().toLowerCase().
                    startsWith(enteredUser.toLowerCase())) {
                System.out.println(j + "." + currentUser.getMyFriends().get(i).getUserProfile().getUsername());
                indexOfMatchedFriends.add(i);
                j++;
            }
        }
        if (j > 1) {
            int enteredNum = sc.nextInt();
            User selectedFriend = currentUser.getMyFriends().get(indexOfMatchedFriends.get(enteredNum - 1));
            System.out.println(selectedFriend.getUserProfile().getUsername() + "'s games:");
            if (selectedFriend.getMyGames().size() + selectedFriend.getMyMonitors().size() +
                    selectedFriend.getMyControllers().size() == 0) {
                selectedFriend.getUserGameShop().viewMyProducts(selectedFriend);
            } else {
                System.out.println("SELECTED FRIEND DOESN'T HAVE ANY GAMES OR ACCESSORIES!");
            }
            System.out.println("1.back to friends page");
            int num = sc.nextInt();
            currentUser.friendsPage(data, currentUser, stopWatch);
        } else {
            System.out.println("NO FRIENDS WITH ENTERED USERNAME FOUND!");
            currentUser.friendsPage(data, currentUser, stopWatch);
        }
    }

    public void requestFriends(Data data, User currentUser, StopWatch stopWatch) {
        System.out.println("--request friends--");
        System.out.println("search users by username");
        Scanner sc = new Scanner(System.in);
        String enteredUsername = sc.nextLine();
        int indexOfMatchedUser = -1;
        for (int i = 0; i < data.getAllUsers().size(); i++) {
            if (data.getAllUsers().get(i).getUserProfile().getUsername().equals(enteredUsername)) {
                indexOfMatchedUser = i;
                break;
            }
        }
        if (indexOfMatchedUser == -1) {
            System.out.println("NO USERS WITH ENTERED USERNAME FOUND!");
            currentUser.friendsPage(data, currentUser, stopWatch);
        } else {
            if (currentUser.getMyFriends().contains(data.getAllUsers().get(indexOfMatchedUser))) {
                System.out.println("THIS USER IS ALREADY YOUR FRIEND!");
                currentUser.friendsPage(data, currentUser, stopWatch);
            } else {
                System.out.println("choose a number:");
                System.out.println("1.request this user");
                System.out.println("2.back to friends page");
                int enteredNum = sc.nextInt();
                if (enteredNum == 1) {
                    data.getAllUsers().get(indexOfMatchedUser).getRequests().add(currentUser);
                    System.out.println("REQUEST WAS SENT SUCCESSFULLY!");
                    currentUser.friendsPage(data, currentUser, stopWatch);
                } else if (enteredNum == 2) {
                    currentUser.friendsPage(data, currentUser, stopWatch);
                }
            }
        }
    }

    public void viewRequests(Data data, User currentUser, StopWatch stopWatch) {
        System.out.println("--my requests--");
        if (currentUser.getRequests().size() == 0) {
            System.out.println("YOU DON'T HAVE ANY REQUESTS AT THE TIME!");
            currentUser.friendsPage(data, currentUser, stopWatch);
        } else {
            System.out.println("choose a number:");
            for (int i = 0; i < currentUser.getRequests().size(); i++) {
                System.out.println(i + 1 + "." + currentUser.getRequests().get(i).getUserProfile().getUsername());
            }
            Scanner sc = new Scanner(System.in);
            int enteredNum = sc.nextInt();
            System.out.println("choose a number:");
            System.out.println("1.confirm request");
            System.out.println("2.delete request");
            int requestConfirmation = sc.nextInt();
            if (requestConfirmation == 1) {
                currentUser.getMyFriends().add(currentUser.getRequests().get(enteredNum - 1));
                currentUser.getRequests().get(enteredNum - 1).getMyFriends().add(currentUser);
                System.out.println(currentUser.getRequests().get(enteredNum - 1).getUserProfile().getUsername()
                        + " WAS ADDED TO YOUR FRIENDS SUCCESSFULLY!");
                currentUser.getRequests().get(enteredNum - 1).getRequests().remove(currentUser);
                currentUser.getRequests().remove(enteredNum - 1);
                currentUser.friendsPage(data, currentUser, stopWatch);
            } else if (requestConfirmation == 2) {
                currentUser.getRequests().remove(enteredNum - 1);
                System.out.println("REQUEST WAS DELETED SUCCESSFULLY!");
                currentUser.friendsPage(data, currentUser, stopWatch);
            }
        }
    }
}
