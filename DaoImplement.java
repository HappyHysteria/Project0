package Jordan;

import java.sql.*;

public class DaoImplement implements Dao{
    Connection connection;
    public DaoImplement() {this.connection = ConnectionFactory.getConnection();}

    @Override
    public void addUser(Customer customer) throws SQLException {
        String sql = "insert into login (name, username, password, checkings, savings) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getUsername());
        preparedStatement.setString(3, customer.getPassword());
        preparedStatement.setInt(4, customer.getCheckings());
        preparedStatement.setInt(5, customer.getSavings());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account created");
        }
    }

    @Override
    public boolean login(String username, String password, boolean isLoggedIn) throws SQLException {
        String sql = "select * from login where username = ? && password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Welcome " + resultSet.getString(2));
            System.out.println("Your account number: " + resultSet.getInt(1));
            System.out.println("******************");
            return true;
        } else {
            System.out.println("Error logging in");
            return false;
        }
    }
    @Override
    public void updateChecking(Customer customer, boolean deposit) throws SQLException{
        int update = 0;
        String sql = "select checkings from login where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer.getUsername());
        ResultSet resultSet = statement.executeQuery();
        if (deposit && resultSet.next()){
            update = resultSet.getInt(1) + customer.getCheckings();
        } else if (!deposit && resultSet.next() && resultSet.getInt(1) >= customer.getCheckings()){
            update = resultSet.getInt(1) - customer.getCheckings();
        } else {
            System.out.println("Invalid transaction");
            return;
        }

        String sql2 = "update login set checkings = ? where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, update);
        preparedStatement.setString(2, customer.getUsername());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Checking account updated");
        } else {
            System.out.println("Error");
        }
    }

    @Override
    public void updateSaving(Customer customer, boolean deposit) throws SQLException {
        int update = 0;
        String sql = "select savings from login where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer.getUsername());
        ResultSet resultSet = statement.executeQuery();
        if (deposit && resultSet.next()){
            update = resultSet.getInt(1) + customer.getSavings();
        } else if (!deposit && resultSet.next() && resultSet.getInt(1) >= customer.getSavings()){
            update = resultSet.getInt(1) - customer.getSavings();
        } else {
            System.out.println("Invalid transaction");
            return;
        }
        String sql2 = "update login set savings = ? where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1,update);
        preparedStatement.setString(2, customer.getUsername());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Savings account updated");
        } else {
            System.out.println("Error");
        }
    }

    @Override
    public void checkBalance(String currentUser) throws SQLException {
        CallableStatement sql = connection.prepareCall("{call Balances(?)}"); //Stored procedure for > String sql = "select checkings,savings from login where username = ?";
        sql.setString(1, currentUser);
        ResultSet resultSet = sql.executeQuery();
        if (resultSet.next()){
            System.out.println("Checkings balance: " + resultSet.getInt(1));
            System.out.println("Savings balance: " + resultSet.getInt(2));
            System.out.println("*****************");
        } else{
            System.out.println("Error");
        }
    }

    @Override
    public void transferMoney(String currentUser, String toUser, int withdraw) throws SQLException, InterruptedException {
        String sql5 = "select name from login where name = ?";
        PreparedStatement statement = connection.prepareStatement(sql5);
        statement.setString(1,toUser);
        ResultSet resultSet1 = statement.executeQuery();
        if (resultSet1.next()) {
            String sql2 = "select checkings, TransferOut from login where username = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setString(1, currentUser);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next() && resultSet2.getInt(1) >= withdraw){
                int updateCheckings = resultSet2.getInt(1) - withdraw;
                int updateTransfer = resultSet2.getInt(2) + withdraw;
                String sql3 = "update login set checkings = ?, TransferOut = ?  where username = ?";
                PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
                preparedStatement3.setInt(1, updateCheckings);
                preparedStatement3.setInt(2, updateTransfer);
                preparedStatement3.setString(3, currentUser);
                int count1 = preparedStatement3.executeUpdate();
                if (count1 > 0) {
                    System.out.println("Processing. Please wait");
                } else {
                    System.out.println("Could not process");
                }
            } else {
                System.out.println("Amount to send is invalid");
                Thread.sleep(1000);
                return;
            }
        } else {
            System.out.println("Invalid recipient");
            Thread.sleep(1000);
            return;
        }

        Thread.sleep(1000);
        String sql = "select TransferIn from login where name = ?";
        PreparedStatement statement1 = connection.prepareStatement(sql);
        statement1.setString(1, toUser);
        ResultSet resultSet = statement1.executeQuery();
        if (resultSet.next()){
            int update = resultSet.getInt(1) + withdraw;
            String sql4 = "update login set TransferIn = ? where name = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql4);
            preparedStatement1.setInt(1,update);
            preparedStatement1.setString(2, toUser);
            int count2 = preparedStatement1.executeUpdate();
            if (count2 > 0) {
                System.out.println("Transfer completed successfully");
            } else {
                System.out.println("Transfer failed");
            }
        } else {
            System.out.println("Error");
        }
        Thread.sleep(1000);
    }

    @Override
    public void receiveMoney(String currentUser) throws SQLException, InterruptedException {
        String sql = "select checkings, TransferIn from login where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, currentUser);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next() && resultSet.getInt(2) > 0){
            System.out.println("Money available to transfer in: " + resultSet.getInt(2));
            Thread.sleep(1000);
            int update = resultSet.getInt(1) + resultSet.getInt(2);
            String sql2 = "update login set checkings = ?, transferin = ? where username = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, update);
            preparedStatement2.setInt(2, 0);
            preparedStatement2.setString(3, currentUser);
            int count = preparedStatement2.executeUpdate();
            if (count > 0) {
                System.out.println("Incoming wire transfer has successfully been added to your checkings.");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("You have no incoming wire transfers.");
        }
        Thread.sleep(1000);
    }
}
