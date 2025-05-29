import Comparison.ComparisonStrategy;

import java.util.ArrayList;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(String message);
    String getState();
    ArrayList<Observer> getObserversList();
    Notification getNotification();
    String getWebsiteName();
    String checkContent();
    ComparisonStrategy getComparisonStrategy();
}
