package Jordan;

import java.io.*;

public class Logs {

    public static void createFile() {
        File directory = new File("Logs");
        directory.mkdir();
        File file = new File("Logs", "Transactions.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(Customer customer) throws IOException {
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        if (customer.getUsername() == null){
            fw.write("Account creation denied by employee");
        } else {
            fw.write("New account created. Pending approval");
        }
        fw.write("\n");
        fw.flush();
        fw.close();
    }
    public static void writeFile(boolean isLoggedIn) throws IOException {
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        if (!isLoggedIn) {
            fw.write("Failed user login");
        } else {
            fw.write("User successfully logged in");
        }
        fw.write("\n");
        fw.flush();
        fw.close();
    }

    public static void writeFile(int deposit) throws IOException {
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        if (deposit < 0) {
            fw.write("Invalid deposit amount");
        } else {
            fw.write("User successfully deposited");
        }
        fw.write("\n");
        fw.flush();
        fw.close();
    }
    public static void writeFile(Customer customer, int withdraw) throws IOException {
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        if (customer.getCheckings() < withdraw){
            fw.write("Over-withdraw attempted from checkings");
        } else {
            fw.write("User successfully withdrew funds from checkings");
        }
        fw.write("\n");
        fw.flush();
        fw.close();
    }
    public static void Write(Customer customer, int withdraw) throws IOException {
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        if (customer.getSavings() < withdraw){
            fw.write("Over-withdraw attempted from savings");
        } else {
            fw.write("User successfully withdrew funds from savings");
        }
        fw.write("\n");
        fw.flush();
        fw.close();
    }
    public static void writeFile() throws IOException {
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        fw.write("Statement balance requested from user");
        fw.write("\n");
        fw.flush();
        fw.close();
    }
    public static void writeFile(boolean mainMenu, boolean isLoggedIn) throws IOException {
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        if(mainMenu){
            fw.write("User has logged out");
        } else {
            fw.write("User has exited");
        }
        fw.write("\n");
        fw.flush();
        fw.close();
    }

    public static void write() throws IOException{
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        fw.write("User transferred money to another user successfully");
        fw.write("\n");
        fw.flush();
        fw.close();
    }

    public static void writing() throws IOException{
        FileWriter fw = new FileWriter("D:\\Revature\\Java\\Project0\\Logs\\Transactions.txt", true);
        fw.write("User accepted money transfer");
        fw.write("\n");
        fw.flush();
        fw.close();
    }
}
