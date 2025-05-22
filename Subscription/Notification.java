package Subscription;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class  Notification{
    private int frequency;
    private String notificationChannel;

    public Notification(int frequency, String notificationChannel) {
        this.frequency = frequency;
        this.notificationChannel = notificationChannel;
    }

    @Override
    public String toString() {
        return "Subscription.Subscription.Subscription.Subscription.Notification{" +
                "frequency=" + frequency +
                ", notificationChannel='" + notificationChannel + '\'' +
                '}';
    }

    public void sendNotification() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Notification sent to " + notificationChannel);
            }
        }, 0, TimeUnit.MINUTES.toMillis(frequency));
    }

    public int getFrequency() {
        return frequency;
    }

    public String getNotificationChannel() {
        return notificationChannel;
    }
}
