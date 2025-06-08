import Comparison.ComparisonStrategy;

import java.util.ArrayList;

public interface ISubject {
    void attach(IObserver IObserver);
    void detach(IObserver IObserver);
    void notifyObservers(String message);
    String getState();
    ArrayList<IObserver> getObserversList();
    Notification getNotification();
    String getWebsiteName();
    String checkContent();
    ComparisonStrategy getComparisonStrategy();
}
