public class User {
    private final String name;
    private final String email;

    // Constructor
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }
}