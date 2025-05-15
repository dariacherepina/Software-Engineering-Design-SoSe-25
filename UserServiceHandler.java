import java.util.HashMap;
import java.util.Scanner;

public class UserServiceHandler {
    private static HashMap<String, User> userList = new HashMap();

    public static String addUser(User user){
        userList.put(user.getName(), user);
        return user.getName();
    }

    public static User createUser(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        while (true){
            System.out.println("Enter your username: ");
            String username = myObj.nextLine();
            if(userList.containsKey(username)){
                System.out.println("That username is already in use! Try again...");
                continue;
            }
            System.out.println("Enter your password: ");
            String password = myObj.nextLine();
            System.out.println("Repeat your password: ");
            String passwordRepeat = myObj.nextLine();
            if(password.equals(passwordRepeat)){
                User person = new User(username, password);
                addUser(person);
                return person;
            }else{
                System.out.println("Passwords do not match\n Try again ....");
            }
        }
    }

    public static User logIn(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = myObj.nextLine();
        User currentUser = userList.get(username);
        while (true){
            System.out.println("Enter your password: ");
            String password = myObj.nextLine();
            if(currentUser.getPassword().equals(password)){
                return currentUser;
            }else{
                System.out.println("Passwords do not match\n Try again ....");
            }
        }

    }

    public static Website getWebsiteFromInput(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("What is the name of the Website?");
        String websiteName = myObj.nextLine();
        System.out.println("What is the URL  for the Website?");
        String url = myObj.nextLine();
        return new Website(websiteName, url);
    }
    public static Notification getNotificationFromInput(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("What is the frequency you'd like to receive notifications?( in min )");
        int frequency = myObj.nextInt();
        myObj.nextLine();
        System.out.println("What is your preferred notification chanel?\n1. Email(not available)\n2. Terminal");
        String notificationType = myObj.nextLine();
        String notificationChannel = "";
        switch (notificationType){
            case "1" ->{
                System.out.println("What is your email?(Not available yet)");
                notificationChannel = myObj.nextLine();
            }
            case "2"-> {
                System.out.println("Thank you for making my life easier and choosing Terminal :)");
                notificationChannel = "terminal";
            }
        }
        return new Notification(frequency, notificationChannel) {
        };
    }

    public static void followSubscriptions(User currentUser){
        HashMap<String, Subscription> currentSubscriptions = currentUser.getSubscriptionList();
        System.out.println("Starting notifications for your subscriptions:");
        for (String websiteName : currentSubscriptions.keySet()) {
            Website currentWebsite = currentSubscriptions.get(websiteName).getWebsite();
            int currentFrequency = currentSubscriptions.get(websiteName).getNotification().getFrequency();
            System.out.println("▶ Starting notifier for: " + websiteName + " every " + currentFrequency + " minutes.");

            Notifier notifier = new Notifier(currentWebsite, currentFrequency);
            notifier.start(); // Correct usage because Notifier extends Thread
        }
        }


    public static void main(String[] args) {
        //First level of program
        loginLoop:
        while (true) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Choose action: \n" +
                    "1. Register\n" +
                    "2. Log in\n" +
                    "3. Exit\n" +
                    "Your action:");
            String userActionStart = myObj.nextLine();

            System.out.println("Action is: " + userActionStart);
            User currentUser = null;
            boolean createdAccount = false;
            switch (userActionStart) {
                case "1" -> {
                    currentUser = createUser();
                }
                case "2" -> {
                    currentUser = logIn();
                }
                case "3" -> {
                    System.out.println("Goodbye!");
                    break loginLoop;
                }
                default -> {
                    System.out.println("Invalid option. Try again.");
                    continue;
                }
            }

            subscriptionLoop:
            while (true) {
                if (!currentUser.getSubscriptionList().isEmpty()) {
                    followSubscriptions(currentUser);
                }else {
                    System.out.println("You have no subscriptions yet. Let's add one.");
                    Website site = getWebsiteFromInput();
                    Notification note = getNotificationFromInput();
                    currentUser.addSubscription(site, note);
                    System.out.println("Subscription added successfully.");
                    followSubscriptions(currentUser);
                }
                System.out.println("Hey " + currentUser.getName());
                System.out.println("What do you want to do today?\n" +
                        "1. Add Subscriptions?\n" +
                        "2. Cancel Subscription?\n" +
                        "3. List Subscriptions?\n" +
                        "4. Log out\n");
                String userActionSubscription = myObj.nextLine();
                switch (userActionSubscription) {
                    case "1" -> {
                        Website site = UserServiceHandler.getWebsiteFromInput();
                        Notification note = UserServiceHandler.getNotificationFromInput();
                        currentUser.addSubscription(site, note);
                        System.out.println("Subscription was added successfully");
                    }
                    case "2" -> {
                        System.out.println("What is the name of the website you want to stop following?");
                        String websiteName = myObj.nextLine();
                        currentUser.cancelSubscription(websiteName);
                        System.out.println("Subscription was canceled successfully");
                    }
                    case "3" -> {
                        HashMap<String, Subscription> currentSubscriptions = currentUser.getSubscriptionList();
                        System.out.println(currentSubscriptions.keySet());
                    }
                    case "4" -> {
                        break subscriptionLoop;
                                         }
                    default -> {
                        System.out.println("Invalid option. Please enter 1 or 2.");
                    }
                }
            }

        }
    }
}
