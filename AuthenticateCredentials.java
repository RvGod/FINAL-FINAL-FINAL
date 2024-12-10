import java.util.HashMap;
import java.io.*;

public abstract class AuthenticateCredentials {
    
    protected HashMap<String, String> userDatabase;

    // Constructor to initialize the user database
    public AuthenticateCredentials() {
        this.userDatabase = loadUsers("users.txt"); // Load users from a file
    }

    // Abstract method to be implemented by subclasses to validate credentials
    public abstract boolean validateCredentials(String username, String password);

    // Utility method to load users from a file into a HashMap
    protected HashMap<String, String> loadUsers(String filePath) {
        HashMap<String, String> userMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (!line.isEmpty() && line.contains(":")) { // Ensure proper format
                    String[] parts = line.split(":", 2); // Split into exactly 2 parts
                    userMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the user database from " + filePath + ": " + e.getMessage());
        }
        return userMap;
    }
}
