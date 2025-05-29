import Comparison.ComparisonStrategy;

import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//ConcreteSubject
public class Website implements Subject {
    private String websiteName;
    private String urlString;
    private Notification notification;
    private String state;
    private LocalDate  lastCheckedDate;
    private StringBuilder content = new StringBuilder();
    private ArrayList<Observer> observersList = new ArrayList<>();
    private ComparisonStrategy comparisonStrategy;

    public ComparisonStrategy getComparisonStrategy() {
        return comparisonStrategy;
    }

    public Website(String websiteName, String urlString, Notification notification, ComparisonStrategy comparisonStrategy) {
        this.websiteName = websiteName;
        this.urlString = urlString;
        this.notification = notification;
        this.comparisonStrategy = comparisonStrategy;
    }

    @Override
    public String toString() {
        return "Website{" +
                "websiteName='" + websiteName + '\'' +
                ", urlString='" + urlString + '\'' +
                ", lastCheckedDate=" + lastCheckedDate +
                ", content='" + content + '\'' +
                ", observersList=" + observersList +
                ", notification=" + notification.toString() +
                '}';
    }
    @Override
    public void attach(Observer observer) {
        observersList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observersList.remove(observer);
    }


    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observersList) {
            observer.update(notification);
        }
    }

    @Override
    public String getState() {
        return this.state;
    }

    public String checkContent(){
        try {
            lastCheckedDate = LocalDate.now();
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  // milliseconds
            connection.setReadTimeout(5000);

            connection.connect();

            int responseCode = connection.getResponseCode();
            // 200–399 are usually considered OK or redirected
            if (responseCode >= 200 && responseCode < 400) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                if (content.isEmpty()) {
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine).append("\n");
                    }
                    in.close();
                    state = "First upload of website " + websiteName + " content was successful";
                } else {
                    StringBuilder compareContent = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        compareContent.append(inputLine).append("\n");
                    }
                    if(comparisonStrategy.isContentChanged(content, compareContent)) {
                        content = compareContent;
                        state = "Website " + websiteName +  " changed! (" + comparisonStrategy.getStrategyName() + ")";;
                    } else {
                        state = "Website " + websiteName +  " didn't change. (" + comparisonStrategy.getStrategyName() + ")";
                    }
                }
            }else{
                state = "Couldn't connect to website";
            }
            notification.setNotificationMessage(state);
            return state;
        } catch (IOException e) {
            return "Error fetching content: " + e.getMessage();
        }
    }


    public ArrayList<Observer> getObserversList() {
        return observersList;
    }


    public Notification getNotification() {
        return notification;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public String getURL() {
        return urlString;
    }

    public LocalDate getLastCheckedDate() {
        return lastCheckedDate;
    }

    public StringBuilder getContent() {
        return content;
    }
}
