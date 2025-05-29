import java.util.HashMap;

public interface Observer {
    void update(Notification notification);
    Object getPassword();
    String getName();
    HashMap<String, String> getSubscriptionList();
    void addSubscriptionToUser(String subscriptionName, String subscriptionUrl);
    void deleteSubscriptionFromUser(String subscriptionName);
}
