public class Subscription {
    private static int idCounter = 0;
    private int subscriptionID;
    private Website website;
    private Notification notification;

    public Subscription(Website website, Notification notification) {
        this.subscriptionID = ++idCounter;
        this.website = website;
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "Subscribtion{" +
                "subscriptionID=" + subscriptionID +
                ", website=" + website +
                ", notification=" + notification +
                '}';
    }


    public int getSubscriptionID() {
        return subscriptionID;
    }

    public Website getWebsite() {
        return website;
    }

    public Notification getNotification() {
        return notification;
    }
}
