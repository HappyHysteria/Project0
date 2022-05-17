package Jordan;

import java.io.IOException;
import static Jordan.Logs.*;


public class Employee {
    static Customer customer = new Customer();

    public static void employeeDenies() throws IOException {
        System.out.println("Employee says, 'You cannot create an account unless you deposit a cumulative minimum of $1000.' ");
        customer.setUsername(null);
        writeFile(customer);
    }
    public static void employeeAccepts() throws IOException {
        System.out.println("Employee says, 'Thank you for choosing Big Bank for your banking needs. Your account is now active' ");
        customer.setUsername("exists");
        writeFile(customer);
    }
    public static void employeeLogin(String user) throws IOException {
        if (user == null) {
            System.out.println("Employee says, 'Please come back with a valid username' ");
        } else {
            writeFile();
        }
    }
}
