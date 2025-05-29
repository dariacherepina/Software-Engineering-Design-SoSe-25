import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class  Notification{
    private int frequency;
    private String notificationChannel;
    private String notificationMessage;

    public Notification(int frequency, String notificationChannel) {
        this.frequency = frequency;
        this.notificationChannel = notificationChannel;
    }

    @Override
    public String toString() {
        return "Subscription.Notification{" +
                "frequency=" + frequency +
                ", notificationChannel='" + notificationChannel + '\'' +
                '}';
    }

    public void sendNotification() {
        //Timer timer = new Timer();
        //timer.scheduleAtFixedRate(new TimerTask() {
          //  @Override
          //  public void run() {
                System.out.println("\n##Notification##");
                System.out.println("Message: " + notificationMessage);
                System.out.println("Notification channel: " + notificationChannel);
            //}
    //    }, 0, TimeUnit.MINUTES.toMillis(frequency));
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
