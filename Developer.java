import java.util.ArrayList;
import java.util.List;

public class Developer {
    private String firstName;
    private String lastName;
    private String email;
    private double yearsOfExperience;
    private List<String> programmingLanguages;

    public Developer(String firstName, String lastName, String email, double yearsOfExperience, List<String> programmingLanguages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
        this.programmingLanguages = programmingLanguages;
    }

    public void addProgrammingLanguage(String programmingLanguage){
        programmingLanguages.add(programmingLanguage);
    }

    public String generateProfileSummary(){
        return  String.format("""
                        Developer Profile:
                        Name: %s %s
                        Email: %s
                        Experience: %.1f years
                        Languages: %s""",
                firstName, lastName, email, yearsOfExperience, programmingLanguages);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public static void main(String[] args) {
        String firstName = "Daria";
        String lastName = "Cherepina";
        String email = "daria.cherepina@gmail.com";
        double yearsOfExperience = 1.5;
        List<String> programmingLanguages = new ArrayList<>(List.of("Python", "Java"));
        Developer daria = new Developer(firstName, lastName, email, yearsOfExperience, programmingLanguages);
        System.out.println(daria.generateProfileSummary());
    }
}
