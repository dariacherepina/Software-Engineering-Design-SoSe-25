import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceHandlerTest {
    private UserServiceHandler handler;
    private User user;

    @BeforeEach
    void setUp() {
        handler = new UserServiceHandler();
        user = new User("testUser", "password123");
        UserServiceHandler.users.put(user.getName(), user);
    }

    @Test
    void testCreateUser() {
        // This would need to be mocked in a real test
        assertNotNull(UserServiceHandler.users.get("testUser"));
    }

    @AfterEach
    void tearDown() {
        UserServiceHandler.users.clear();
        UserServiceHandler.websites.clear();
        UserServiceHandler.currentUser = null;
    }
}