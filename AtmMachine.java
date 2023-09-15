import java.util.*;

class User {
    private int accountNumber;
    private String username;
    private String password;
    private double balance;

    public User(int accountNumber, String username, String password, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        }
    }

    public void checkBalance() {
        System.out.println("Account Balance: Rs. " + balance);
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Username: " + username);
        System.out.println("Balance: Rs. " + balance);
    }
}

public class AtmMachine {
    private static final int MAX_USERS = 10;
    private User[] users;
    private int userCount;

    public AtmMachine() {
        users = new User[MAX_USERS];
        userCount = 0;
    }

    public void createUser(int accountNumber, String username, String password, double balance) {
        if (userCount < MAX_USERS) {
            users[userCount++] = new User(accountNumber, username, password, balance);
            System.out.println("User created successfully.");
        } else {
            System.out.println("Maximum user limit reached.");
        }
    }

    public User login(int accountNumber, String password) {
        for (int i = 0; i < userCount; i++) {
            User user = users[i];
            if (user.getAccountNumber() == accountNumber && user.getPassword().equals(password)) {
                System.out.println("Login successful.");
                return user;
            }
        }
        System.out.println("Invalid account number or password.");
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AtmMachine bankingSystem = new AtmMachine();

        // Create some example users
        bankingSystem.createUser(123456, "user1", "password1", 1000.0);
        bankingSystem.createUser(789012, "user2", "password2", 500.0);

        // User login
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter password: ");
        String password = scanner.next();

        User loggedInUser = bankingSystem.login(accountNumber, password);
        if (loggedInUser != null) {
            int choice;
            do {
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Account Details");
                System.out.println("5. Logout");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        loggedInUser.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        loggedInUser.withdraw(withdrawAmount);
                        break;
                    case 3:
                        loggedInUser.checkBalance();
                        break;
                    case 4:
                        loggedInUser.displayAccountDetails();
                        break;
                    case 5:
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 5);
        }

        scanner.close();
    }
}
