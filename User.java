import Subscription.Notification;
import Subscription.Observer;
import Subscription.Observable;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;

public class User implements Observer {
    private static int idCounter = 0;
    private int userID;
    private String name;
    private String password;
    private Notification notification;
    private HashMap<String, String> subscriptionList = new HashMap<>();
    private LocalTime lastUpdateTime;


    public User(String name, String password, Notification notification) {
        this.userID = ++idCounter;
        this.name = name;
        this.password = password;
        this.notification = notification;
        //this.subscriptionList = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", Password: " + password +
                ", notification=" + notification;
    }

    @Override
    public void update() {
        lastUpdateTime = LocalTime.now();
        notification.sendNotification();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HashMap<String, String> getSubscriptionList() {
        return subscriptionList;
    }

    @Override
    public void addSubscriptionToUser(String subscriptionName, String subscriptionUrl) {
        subscriptionList.put(subscriptionName, subscriptionUrl);
    }
    @Override
    public void deleteSubscriptionFromUser(String subscriptionName) {
        subscriptionList.remove(subscriptionName);
    }

    @Override
    public String getPassword() {
        return password;
    }

}
