import java.util.Scanner;

public class Main extends AuthenticateCredentials {
    
    @Override
    void inputCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        this.username = scanner.nextLine();

        System.out.print("Enter password: ");
        this.password = scanner.nextLine();
    }

    public static void main(String[] args) {
        Main authSystem = new Main();

        // Input credentials
        authSystem.inputCredentials();

        // Attempt to authenticate
        boolean isAuthenticated = authSystem.logStatus(authSystem.username, authSystem.password);

        // Display role based on authentication
        if (isAuthenticated) {
            if (authSystem.isAdmin) {
                System.out.println("Welcome, Admin!");
            } else {
                System.out.println("Welcome, Customer!");
            }
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }
}
