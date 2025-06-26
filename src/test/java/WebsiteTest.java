import Comparison.ExactHtmlComparisonStrategy;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

class WebsiteTest {
    private Website website;
    private Notification notification;
    private User user;

    @BeforeEach
    void setUp() {
        notification = new Notification(60, "terminal");
        website = new Website("TestSite", "http://example.com", notification,
                new ExactHtmlComparisonStrategy());
        user = new User("testUser", "password123");
    }

    @Test
    void testAttachDetachObserver() {
        website.attach(user);
        assertTrue(website.getObserversList().contains(user));

        website.detach(user);
        assertFalse(website.getObserversList().contains(user));
    }

    @Test
    void testWebsiteCreation() {
        assertEquals("TestSite", website.getWebsiteName());
        assertEquals("http://example.com", website.getURL());
        assertEquals(notification, website.getNotification());
    }
}