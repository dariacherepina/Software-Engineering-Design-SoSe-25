import java.util.Date;

public class  Notification{
    private int frequency;
    private String notificationChannel;
    private boolean status;

    public Notification(int frequency, String notificationChannel) {
        this.frequency = frequency;
        this.notificationChannel = notificationChannel;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "frequency=" + frequency +
                ", notificationChannel='" + notificationChannel + '\'' +
                ", status=" + status +
                '}';
    }

    public void sendNotification(String message) {

        System.out.println("Sending notification ...");
    }

    public int getFrequency() {
        return frequency;
    }

    public String getNotificationChannel() {
        return notificationChannel;
    }

    public boolean isStatus() {
        return status;
    }
}
