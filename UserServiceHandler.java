import Subscription.Subscription;
import Subscription.Website;
import Subscription.Notification;
import Subscription.Observable;
import  Subscription.Observer;

import java.util.*;

public class UserServiceHandler {
    private static HashMap<String, Observable> subscriptionList = new HashMap<String, Observable>();
    private static HashMap<String, Observer> userList = new HashMap<String, Observer>();


    public static Observer createUser(){
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
                Observer person = new User(username, password);
                userList.put(person.getName(), person);
                person.getName();
                return person;
            }else{
                System.out.println("Passwords do not match\n Try again ....");
            }
        }
    }
    public static Observer logIn(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = myObj.nextLine();
        Observer currentUser = userList.get(username);
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
        return new Notification(frequency, notificationChannel);
    }
    public static void addSubscription(Observer currentUser){
        Website site = getWebsiteFromInput();
        Notification note = getNotificationFromInput();
        if (subscriptionList.containsKey(site.getWebsiteName())){
            // If the object Subscription was already created then we just add User
            subscriptionList.get(site.getWebsiteName()).attach(currentUser);
        }else{
            // If not, we create new Subscription
            Subscription currentSubscription = new Subscription(site, note);
            // Add it to the User
            currentSubscription.attach(currentUser);
            // Adding it to the list of all Subscriptions
            subscriptionList.put(site.getWebsiteName(), currentSubscription);
        }
        currentUser.addSubscriptionToUser(site.getWebsiteName(), site.getURL());
        System.out.println("Subscription.Subscription added successfully.");
        System.out.println(subscriptionList.toString());
        System.out.println(userList.toString());
    }
    public static void deleteSubscription(Observer currentUser){
        Scanner myObj = new Scanner(System.in);
        System.out.println("What subscription do you want to delete");
        String subscriptionNameToDelete = myObj.nextLine();
        for (String nameOfSubscription : subscriptionList.keySet()){
            if(Objects.equals(nameOfSubscription, subscriptionNameToDelete)){
                subscriptionList.get(nameOfSubscription).detach(currentUser);
                currentUser.deleteSubscriptionFromUser(subscriptionNameToDelete);
            }
        }
        System.out.println(subscriptionList.toString());
        System.out.println(userList.toString());
        System.out.println(currentUser.getSubscriptionList().toString());
    }

    public static void main(String[] args) {
        //Create User Object
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
            Observer currentUser = null;
            switch (userActionStart) {
                case "1" -> currentUser = createUser();
                case "2" -> currentUser = logIn();
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
                Set<String> currentSubscriptions =
                        (currentUser.getSubscriptionList() != null)
                                ? currentUser.getSubscriptionList().keySet()
                                : new HashSet<>();
                if (currentSubscriptions.isEmpty()){
                    System.out.println("You have no subscriptions yet. Let's add one.");
                    addSubscription(currentUser);
                }else{
                    System.out.println("Do you want to " +
                            "\n1. Add subscription" +
                            "\n2. Delete subscription" +
                            "\n3. Check the Subscriptions" +
                            "\n4. Go back");
                    int choice = myObj.nextInt();
                    System.out.println("\n###Your subscriptions are " + String.join(", ", currentSubscriptions) + "###");
                    switch (choice){
                        case 1 -> {
                            addSubscription(currentUser);
                        }
                        case 2 -> {
                            deleteSubscription(currentUser);
                        }
                        case 3 -> {
                            for (Observable subscription : subscriptionList.values()) {
                                    subscription.notifyObservers();
                            }
                        }
                        case 4 -> {
                            System.out.println("Going back...");
                            break subscriptionLoop;
                        }
                    }
                }
            }

        }
    }
}
