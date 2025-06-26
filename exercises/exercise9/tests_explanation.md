1. UserTest Class
   Theory: Unit Testing and Test Fixtures
   java
   class UserTest {
   private User user;

   @BeforeEach
   void setUp() {
   user = new User("testUser", "password123");
   }
   @BeforeEach: A JUnit annotation that runs before each test method, creating a fresh test fixture

Test Fixture: The set of objects needed for testing (here, a User instance)

Purpose: Ensures each test starts with a clean, predictable state

Test Case 1: User Creation Validation
java
@Test
void testValidUserCreation() {
assertEquals("testUser", user.getName());
assertEquals("password123", user.getPassword());
}
@Test: Marks this as a test method

assertEquals(): Asserts that two values are equal

Testing: Verifies the constructor properly sets user properties

Test Case 2: Subscription Management
java
@Test
void testAddSubscription() {
user.addSubscriptionToUser("TestSite", "http://test.com");
HashMap<String, String> subs = user.getSubscriptionList();
assertTrue(subs.containsKey("TestSite"));
assertEquals("http://test.com", subs.get("TestSite"));
}
Behavior Verification: Tests if addSubscriptionToUser() correctly updates the subscription list

assertTrue(): Verifies a boolean condition is true

Map Verification: Checks both key existence and value correctness

Test Case 3: Subscription Removal
java
@Test
void testDeleteSubscription() {
user.addSubscriptionToUser("TestSite", "http://test.com");
user.deleteSubscriptionFromUser("TestSite");
assertFalse(user.getSubscriptionList().containsKey("TestSite"));
}
State Change Testing: Verifies the removal operation

assertFalse(): Ensures the subscription was actually removed

2. NotificationTest Class
   Test Case 1: Object Initialization
   java
   @Test
   void testNotificationCreation() {
   assertEquals(60, notification.frequency);
   assertEquals("terminal", notification.notificationChannel);
   }
   Constructor Testing: Verifies field initialization

Direct Field Access: Note this requires package visibility or getters

Test Case 2: Notification Sending
java
@Test
void testSendNotification() {
notification.setNotificationMessage("Test message");
assertDoesNotThrow(() -> notification.sendNotification());
}
Exception Testing: assertDoesNotThrow verifies no exceptions occur

Side Effect Verification: Tests the method runs without errors (output verification would need mocking)

3. WebsiteTest Class
   Theory: Observer Pattern Testing
   java
   @Test
   void testAttachDetachObserver() {
   website.attach(user);
   assertTrue(website.getObserversList().contains(user));

        website.detach(user);
        assertFalse(website.getObserversList().contains(user));
   }
   Observer Pattern: Tests the Subject's ability to manage observers

Lifecycle Verification: Checks both attachment and detachment

List State Verification: Uses direct collection inspection

Website Construction Test
java
@Test
void testWebsiteCreation() {
assertEquals("TestSite", website.getWebsiteName());
assertEquals("http://example.com", website.getURL());
assertEquals(notification, website.getNotification());
}
Object Composition: Verifies the website holds references to its dependencies

Identity Comparison: Uses assertEquals for object equality

4. ComparisonStrategiesTest Class
   Theory: Strategy Pattern Testing
   java


   @Test
   void testExactHtmlComparison() {
   StringBuilder oldContent = new StringBuilder("<html><body>Test</body></html>");
   StringBuilder sameContent = new StringBuilder("<html><body>Test</body></html>");
   StringBuilder diffContent = new StringBuilder("<html><body>Changed</body></html>");

        assertFalse(exactStrategy.isContentChanged(oldContent, sameContent));
        assertTrue(exactStrategy.isContentChanged(oldContent, diffContent));
   }
   Strategy Pattern: Tests different comparison algorithms

Equivalence Partitioning:

Same content → false (no change)

Different content → true (change detected)

String Comparison: Exact match testing

Size Comparison Test
java
@Test
void testSizeComparison() {
StringBuilder oldContent = new StringBuilder("12345");
StringBuilder sameSize = new StringBuilder("67890");
StringBuilder diffSize = new StringBuilder("123");

        assertFalse(sizeStrategy.isContentChanged(oldContent, sameSize));
        assertTrue(sizeStrategy.isContentChanged(oldContent, diffSize));
    }
Length-Based Comparison: Only checks content size

Boundary Testing: Different lengths trigger different outcomes

Text-Only Comparison Test
java
@Test
void testTextOnlyComparison() {
StringBuilder oldContent = new StringBuilder("<html><body>Test</body></html>");
StringBuilder sameText = new StringBuilder("<div><p>Test</p></div>");
StringBuilder diffText = new StringBuilder("<html><body>Changed</body></html>");

        assertFalse(textStrategy.isContentChanged(oldContent, sameText));
        assertTrue(textStrategy.isContentChanged(oldContent, diffText));
    }
HTML-Agnostic Comparison: Uses regex to strip tags

Semantic Testing: Verifies only text content matters

5. UserServiceHandlerTest Class
   Theory: Static State Management
   java
   @BeforeEach
   void setUp() {
   handler = new UserServiceHandler();
   user = new User("testUser", "password123");
   UserServiceHandler.users.put(user.getName(), user);
   }
   Static State: Tests a class with static fields

Test Isolation: Requires manual cleanup (see @AfterEach)

Test Case: User Creation
java
@Test
void testCreateUser() {
assertNotNull(UserServiceHandler.users.get("testUser"));
}
Existence Testing: Verifies the user was added to the static map

Limitation: Would need mocking for complete testing

Cleanup Method
java
@AfterEach
void tearDown() {
UserServiceHandler.users.clear();
UserServiceHandler.websites.clear();
UserServiceHandler.currentUser = null;
}
@AfterEach: Runs after each test

Test Isolation: Crucial for static state to prevent test pollution