package Subscription;

import java.util.ArrayList;

public interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
    ArrayList<Observer> getObserversList();
    Website getWebsite();
    Notification getNotification();
}
