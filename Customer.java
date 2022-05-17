package Jordan;

public class Customer {
    private String name;
    private String username;
    private String password;
    private int checkings;
    private int savings;

    public Customer() {

    }

    public Customer(String name, String username, String password, int checkings, int savings) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.checkings = checkings;
        this.savings = savings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCheckings() {
        return checkings;
    }

    public void setCheckings(int checkings) {
        this.checkings = checkings;
    }

    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", checkings=" + checkings +
                ", savings=" + savings +
                '}';
    }
}
