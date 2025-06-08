import Comparison.ComparisonStrategy;
import Comparison.ExactHtmlComparisonStrategy;
import Comparison.SizeComparisonStrategy;
import Comparison.TextOnlyComparisonStrategy;

import java.util.*;

public class UserServiceHandler {
    static HashMap<String, ISubject> websites = new HashMap<String, ISubject>();
    private static HashMap<String, IObserver> users = new HashMap<String, IObserver>();
    private static IObserver currentUser = null;


    public static IObserver createUser(){
        Scanner myObj = new Scanner(System.in);
        while (true){
            System.out.println("Enter your username: ");
            String username = myObj.nextLine();
            if(users.containsKey(username)){
                System.out.println("That username is already in use! Try again...");
                continue;
            }
            System.out.println("Enter your password: ");
            String password = myObj.nextLine();
            System.out.println("Repeat your password: ");
            String passwordRepeat = myObj.nextLine();
            if(password.equals(passwordRepeat)){
                IObserver person = new User(username, password);
                users.put(person.getName(), person);
                person.getName();
                return person;
            }else{
                System.out.println("Passwords do not match\n Try again ....");
            }
        }
    }

    private static IObserver loginUser(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = myObj.nextLine();
        IObserver currentUser = users.get(username);
        while (true){
            System.out.println("Enter your password: ");
            String password = myObj.nextLine();
            if( currentUser.getPassword().equals(password)){
                return currentUser;
            }else{
                System.out.println("Passwords do not match\n Try again ....");
            }
        }

    }

    private static Notification getNotificationFromInput(){
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
        return new Notification(frequency, notificationChannel);
    }

    private static Website getWebsiteFromInput(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("What is the name of the Website?");
        String websiteName = myObj.nextLine();
        System.out.println("What is the URL  for the Website?");
        String url = myObj.nextLine();
        Notification notification = getNotificationFromInput();
        System.out.println("\nSelect comparison strategy:");
        System.out.println("1. Content size comparison");
        System.out.println("2. Exact HTML comparison");
        System.out.println("3. Text-only comparison");
        int strategyChoice = myObj.nextInt();

        ComparisonStrategy strategy;
        switch (strategyChoice) {
            case 1 -> strategy = new SizeComparisonStrategy();
            case 2 -> strategy = new ExactHtmlComparisonStrategy();
            case 3 -> strategy = new TextOnlyComparisonStrategy();
            default -> strategy = new ExactHtmlComparisonStrategy();
        }
        return new Website(websiteName, url, notification, strategy);
    }

    private static void addSubscription(){
        Website site = getWebsiteFromInput();
        if (websites.containsKey(site.getWebsiteName())){
            // If the object Subscription was already created then we just add User
            websites.get(site.getWebsiteName()).attach(currentUser);
        }else{
            // If not, add the User to it
            site.attach(currentUser);
            // We add the Website to the list
            websites.put(site.getWebsiteName(), site);
        }
        currentUser.addSubscriptionToUser(site.getWebsiteName(), site.getURL());
        System.out.println("Subscription added successfully.");
        System.out.println(websites.toString());
        System.out.println(users.toString());
    }

    private static void deleteSubscription(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("What subscription do you want to delete");
        String subscriptionNameToDelete = myObj.nextLine();
        for (String nameOfSubscription : websites.keySet()){
            if(Objects.equals(nameOfSubscription, subscriptionNameToDelete)){
                websites.get(nameOfSubscription).detach(currentUser);
                currentUser.deleteSubscriptionFromUser(subscriptionNameToDelete);
            }
        }
        System.out.println(websites.toString());
        System.out.println(users.toString());
        System.out.println(currentUser.getSubscriptionList().toString());
    }

    private static void listSubscriptions() {
        System.out.println("\n=== Your Subscriptions ===");
        HashMap<String, String> subs = currentUser.getSubscriptionList();

        if (subs.isEmpty()) {
            System.out.println("You have no subscriptions.");
        } else {
            subs.forEach((name, url) -> {
                System.out.println("- " + name + " (" + url + ")");
                System.out.println("  Notification: " + websites.get(name).getNotification() );
                System.out.println("  Strategy: " + websites.get(name).getComparisonStrategy());
            });
        }
    }

    private static void checkAllWebsites() {
        System.out.println("\n=== Checking Websites for Updates ===");
        HashMap<String, String> subs = currentUser.getSubscriptionList();

        if (subs.isEmpty()) {
            System.out.println("You have no subscriptions to check.");
            return;
        }

        subs.forEach((name, url) -> {
            for (ISubject website : websites.values()) {
                if (website.getWebsiteName().equals(name)) {
                    String result = website.checkContent();
                    website.notifyObservers(result);
                    break;
                }
            }
        });
    }

    private static void logout() {
        System.out.println("Goodbye, " + currentUser.getName() + "!");
        currentUser = null;
    }

    private static void showMainMenu() {
        System.out.println("\n=== Website Monitoring System ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    private static void showUserMenu() {
        System.out.println("\n=== Welcome, " + currentUser.getName() + " ===");
        System.out.println("1. List my subscriptions");
        System.out.println("2. Add subscription");
        System.out.println("3. Remove subscription");
        System.out.println("4. Check all websites for updates");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        //Create User Object
        boolean runningLogin = true;
        while (runningLogin) {
            showMainMenu();
            String userActionStart = myObj.nextLine();
            System.out.println("Action is: " + userActionStart);
            switch (userActionStart) {
                case "1" -> currentUser = createUser();
                case "2" -> currentUser = loginUser();
                case "3" -> runningLogin = false;
                default -> {
                    System.out.println("Invalid option. Try again.");
                    continue;
                }
            }

            boolean runningSubscription = true;
            while (runningSubscription) {
                Set<String> currentSubscriptions =
                        (currentUser.getSubscriptionList() != null)
                                ? currentUser.getSubscriptionList().keySet()
                                : new HashSet<>();
                if (currentSubscriptions.isEmpty()){
                    System.out.println("You have no subscriptions yet. Let's add one.");
                    addSubscription();
                }else{
                    showUserMenu();
                    int choice = myObj.nextInt();
                    System.out.println("\n###Your subscriptions are " + String.join(", ", currentSubscriptions) + "###");
                    switch (choice) {
                        case 1 -> listSubscriptions();
                        case 2 -> addSubscription();
                        case 3 -> deleteSubscription();
                        case 4 -> checkAllWebsites();
                        case 5 -> logout();
                        case 6 -> runningSubscription = false;
                    }
                }
            }
        }
    }
}

