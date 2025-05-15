public class Notifier extends Thread {
    private Website website;
    private int frequency;

    public Notifier(Website website, int frequency) {
        this.website = website;
        this.frequency = frequency;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (website.checkWebsite()) {
                    System.out.println(website.getWebsiteName() + " is active. Moving on to check the content of it.");
                    String currentContent = website.checkContent();
                    System.out.println("Current content of " + website.getWebsiteName() + ": " + currentContent);
                } else {
                    System.out.println("Website is not active. Choose another one");
                }
                Thread.sleep(frequency * 1000); // convert minutes to milliseconds
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}
