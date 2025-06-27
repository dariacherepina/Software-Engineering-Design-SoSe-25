import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {
    private Notification notification;

    @BeforeEach
    void setUp() {
        notification = new Notification(60, "terminal");
    }

    @Test
    void testNotificationCreation() {
        assertEquals(60, notification.frequency);
        assertEquals("terminal", notification.notificationChannel);
    }

    @Test
    void testSendNotification() {
        notification.setNotificationMessage("Test message");
        assertDoesNotThrow(() -> notification.sendNotification());
    }
}