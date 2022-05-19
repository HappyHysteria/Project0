package Jordan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static Jordan.Logs.*;


public class Employee {
    private static List<String> list;
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
    public static void readTransactions() throws IOException, InterruptedException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt"))){
            list = bufferedReader.lines().collect(Collectors.toList());
            list.forEach(System.out::println);
            System.out.println("****************************");
            Thread.sleep(2000);
        }
    }
}
