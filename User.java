import java.time.LocalTime;
import java.util.HashMap;


//ConcreteObserver
public class User implements Observer {
    private static int idCounter = 0;
    private int userID;
    private String name;
    private String password;
    private HashMap<String, String> subscriptionList = new HashMap<>();


    public User(String name, String password) {
        this.userID = ++idCounter;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", Password: " + password;
    }

    @Override
    public void update(Notification notification) {
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
