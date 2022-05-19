package Jordan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import static Jordan.Logs.*;
import static Jordan.Employee.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        Dao dao = DaoFactory.getDao();
        Scanner scan = new Scanner(System.in);
        createFile();

        String currentUser = null;
        int deposit;
        int withdraw;
        boolean mainMenu = true;
        boolean isLoggedIn = false;
        boolean employeeLoggedIn = false;

        while (mainMenu) {
            System.out.println("Welcome to Big Bank");
            System.out.println("Press 1: Create an account");
            System.out.println("Press 2: Login to existing account");
            System.out.println("Press 3: View your account");
            System.out.println("Press 4: Login as Employee");
            System.out.println("Press 5: Exit");

            int input = scan.nextInt();
            switch (input) {
                case 1: {
                    System.out.println("Enter name for new account");
                    String name = scan.next();
                    System.out.println("Enter new username for the account");
                    String username = scan.next();
                    System.out.println("Enter new password for the account");
                    String password = scan.next();
                    System.out.println("Enter amount to deposit into checking account");
                    int checkings = scan.nextInt();
                    System.out.println("Enter amount to deposit into saving account");
                    int savings = scan.nextInt();
                    if (checkings < 0 || savings < 0){
                        System.out.println("You cannot create an account with a negative balance");
                    }
                    else if ((checkings + savings) < 100) {
                            employeeDenies();
                    } else {
                            Customer newCustomer = new Customer(name, username, password, checkings, savings);
                            dao.addUser(newCustomer);
                            writeFile(newCustomer);
                        }
                    Thread.sleep(1000);
                    break;
                    }
                case 2: {
                    if (!isLoggedIn) {
                        System.out.println("Enter your username");
                        String username = scan.next();
                        System.out.println("Enter your password");
                        String password = scan.next();
                        isLoggedIn = dao.login(username,password, isLoggedIn); //sets local boolean to receive boolean value from dao
                        if (isLoggedIn) {
                            currentUser = username;
                            writeFile(true);
                        } else {
                            writeFile(false);
                        }
                    } else {
                        System.out.println("You are already logged in.");
                    }
                    Thread.sleep(2000);
                    break;
                }
                case 3: {
                    if (!isLoggedIn) {
                        System.out.println("You are not logged in. Please log in first");
                        System.out.println("*************************");
                        writeFile(false);
                        Thread.sleep(1000);
                    } else {
                        while (isLoggedIn) {
                            mainMenu = false;
                            System.out.println("Welcome");
                            System.out.println("Press 1: Deposit into checking");
                            System.out.println("Press 2: Deposit into savings");
                            System.out.println("Press 3: Withdraw from checking");
                            System.out.println("Press 4: Withdraw from savings");
                            System.out.println("Press 5: Wire transfer money");
                            System.out.println("Press 6: Accept wire transfer");
                            System.out.println("Press 7: View Balances");
                            System.out.println("Press 8: Log out");
                            int newInput = scan.nextInt();
                            switch (newInput) {
                                case 1: {
                                    System.out.println("Enter the amount to deposit to checkings:");
                                    deposit = scan.nextInt();
                                    if (deposit <= 0){
                                        System.out.println("Invalid amount to deposit");
                                    } else {
                                        Customer customerChecking = new Customer();
                                        customerChecking.setCheckings(deposit);
                                        customerChecking.setUsername(currentUser);
                                        dao.updateChecking(customerChecking, true);
                                    }
                                    writeFile(deposit);
                                    Thread.sleep(1000);
                                    break;
                                }
                                case 2: {
                                    System.out.println("Enter the amount to deposit to savings:");
                                    deposit = scan.nextInt();
                                    if (deposit <= 0){
                                        System.out.println("Invalid amount to deposit");
                                    } else {
                                        Customer customerSaving = new Customer();
                                        customerSaving.setSavings(deposit);
                                        customerSaving.setUsername(currentUser);
                                        dao.updateSaving(customerSaving, true);
                                    }
                                    writeFile(deposit);
                                    Thread.sleep(1000);
                                    break;
                                }
                                case 3: {
                                    System.out.println("Enter the amount to withdraw from checkings:");
                                    withdraw = scan.nextInt();
                                    if (withdraw <= 0){
                                        System.out.println("Invalid amount to withdraw");
                                    } else {
                                        Customer checkWithdraw = new Customer();
                                        checkWithdraw.setCheckings(withdraw);
                                        checkWithdraw.setUsername(currentUser);
                                        dao.updateChecking(checkWithdraw, false);
                                        writeFile(checkWithdraw, withdraw);
                                    }
                                    Thread.sleep(1000);
                                    break;
                                }
                                case 4: {
                                    System.out.println("Enter the amount to withdraw from savings:");
                                    withdraw = scan.nextInt();
                                    if (withdraw <= 0){
                                        System.out.println("Invalid amount to withdraw");
                                    } else {
                                        Customer saveWithdraw = new Customer();
                                        saveWithdraw.setSavings(withdraw);
                                        saveWithdraw.setUsername(currentUser);
                                        dao.updateSaving(saveWithdraw,false);
                                        Write(saveWithdraw, withdraw);
                                    }
                                    Thread.sleep(1000);
                                    break;
                                }
                                case 5: {
                                    System.out.println("Enter the name on the account that you would like to transfer money to");
                                    String toUser = scan.next();
                                    System.out.println("Enter the amount to send to " + toUser);
                                    withdraw = scan.nextInt();
                                    if (withdraw >= 0) {
                                        dao.transferMoney(currentUser, toUser, withdraw);
                                        write();
                                    } else {
                                        System.out.println("Invalid amount to transfer");
                                    }
                                    break;
                                }
                                case 6: {
                                    dao.receiveMoney(currentUser);
                                    writing();
                                    break;
                                }
                                case 7: {
                                    dao.checkBalance(currentUser);
                                    writeFile();
                                    Thread.sleep(2000);
                                    break;
                                }
                                case 8: {
                                    isLoggedIn = false;
                                    System.out.println("You have logged out");
                                    mainMenu = true;
                                    writeFile(true,false);
                                    Thread.sleep(1000);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    if (!employeeLoggedIn) {
                        System.out.println("Please login to your employee account");
                        System.out.println("Enter your username");
                        String username = scan.next();
                        System.out.println("Enter your password");
                        String password = scan.next();
                        employeeLoggedIn = dao.employeeLogin(username, password, employeeLoggedIn);
                    } else {
                        System.out.println("You have logged in");
                        while (employeeLoggedIn) {
                            mainMenu = false;
                            System.out.println("Press 1 to view pending customer approvals");
                            System.out.println("Press 2 to view all customer accounts");
                            System.out.println("Press 3 to view all transactions");
                            System.out.println("Press 4 to log out");
                            int employeeInput = scan.nextInt();
                            switch (employeeInput) {
                                case 1: {
                                    dao.accountPending();
                                    System.out.println("Enter the account number you would like to approve");
                                    int account = scan.nextInt();
                                    dao.employeeApproval(account);
                                    employeeAccepts();
                                    break;
                                }
                                case 2: {
                                    dao.employeeOverview();
                                    break;
                                }
                                case 3: {
                                    System.out.println("All Transactions");
                                    readTransactions();
                                    break;
                                }
                                case 4: {
                                    employeeLoggedIn = false;
                                    mainMenu = true;
                                    System.out.println("You have logged out");
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("Goodbye");
                    mainMenu = false;
                    writeFile(false,false);
                    break;
                }
            }
        }
    }
}
