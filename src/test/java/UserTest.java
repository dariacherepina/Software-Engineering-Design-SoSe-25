import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "password123");
    }

    @Test
    void testValidUserCreation() {
        assertEquals("testUser", user.getName());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testAddSubscription() {
        user.addSubscriptionToUser("TestSite", "http://test.com");
        HashMap<String, String> subs = user.getSubscriptionList();
        assertTrue(subs.containsKey("TestSite"));
        assertEquals("http://test.com", subs.get("TestSite"));
    }

    @Test
    void testDeleteSubscription() {
        user.addSubscriptionToUser("TestSite", "http://test.com");
        user.deleteSubscriptionFromUser("TestSite");
        assertFalse(user.getSubscriptionList().containsKey("TestSite"));
    }
}
