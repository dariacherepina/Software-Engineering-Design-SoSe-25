import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


public class User {
    private static int idCounter = 0;
    private int userID;
    private String name;
    private String password;
    private HashMap<String, Subscribtion> subscriptionList;

    public User(String name, String password) {
        this.userID = ++idCounter;
        this.name = name;
        this.password = password;
        this.subscriptionList = new HashMap<>();
    }

    public void addSubscription(Website website, Notification note){
        Subscribtion subscription = new Subscribtion(website, note);
        subscriptionList.put(website.getWebsiteName(), subscription);
        //System.out.println(subscriptionList);
    }
    public void cancelSubscription(String websiteName){
        subscriptionList.remove(websiteName);
        //System.out.println(subscriptionList);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Password: " + password;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String, Subscribtion> getSubscriptionList() {
        return subscriptionList;
    }
    public Subscribtion getSubscription(String websiteName) {
        return subscriptionList.get(websiteName);
    }

}
