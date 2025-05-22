package Subscription;

import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Website {
    private String websiteName;
    private String urlString;
    private LocalDate  lastCheckedDate;
    private StringBuilder content = new StringBuilder();

    public Website(String websiteName, String urlString) {
        this.websiteName = websiteName;
        this.urlString = urlString;
    }

    @Override
    public String toString() {
        return "Subscription.Subscription.Website{" +
                "websiteName='" + websiteName + '\'' +
                ", urlString='" + urlString + '\'' +
                ", lastCheckedDate=" + lastCheckedDate +
                ", content='" + content + '\'' +
                '}';
    }

    public boolean checkWebsite(){
        try {
            URL url = new URL(urlString);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);  // 3 seconds timeout
            connection.connect();

            int responseCode = connection.getResponseCode();
            //System.out.println(responseCode);
            // 200–399 are usually considered OK or redirected
            return (responseCode >= 200 && responseCode < 400);
        } catch (IOException e) {
            return false;
        }
    }
    public String checkContent(){
        try {
            lastCheckedDate = LocalDate.now();
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  // milliseconds
            connection.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            if(content.isEmpty()){
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine).append("\n");
                }
                in.close();
                //System.out.println(content.toString());
                return "First upload of website content was successful";
            }else {
                StringBuilder compareContent = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    compareContent.append(inputLine).append("\n");
                }
                if(compareContent.toString().equals(content.toString())){
                    return "Subscription.Subscription.Website didn't change. No updates :)";
                }else{
                    return "Subscription.Subscription.Website changed! Go ahead and check the updates";
                }
            }
        } catch (IOException e) {
            return "Error fetching content: " + e.getMessage();
        }
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
