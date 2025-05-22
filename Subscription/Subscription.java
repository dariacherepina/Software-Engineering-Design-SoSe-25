package Subscription;

import java.util.ArrayList;

public class Subscription implements Observable {
    private static int idCounter = 0;
    private int subscriptionID;
    private Website website;
    private Notification notification;
    private ArrayList<Observer> observersList = new ArrayList<>();

    public Subscription(Website website, Notification notification) {
        this.subscriptionID = ++idCounter;
        this.website = website;
        this.notification = notification;
    }

    @Override
    public void attach(Observer observer) {
        observersList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observersList.remove(observer);
    }


    @Override
    public void notifyObservers() {
        for (Observer observer : observersList) {
            notification.sendNotification();
        }

    }

    @Override
    public String toString() {
        return "Subscribtion{" +
                "subscriptionID=" + subscriptionID +
                ", website=" + website +
                ", notification=" + notification +
                '}';
    }

    @Override
    public ArrayList<Observer> getObserversList() {
        return observersList;
    }

    @Override
    public Website getWebsite() {
        return null;
    }

    @Override
    public Notification getNotification() {
        return null;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }


}
