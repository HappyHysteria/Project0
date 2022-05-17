package Jordan;

import java.sql.SQLException;

public interface Dao {
    void addUser(Customer customer) throws SQLException;
    boolean login (String username, String password, boolean isLoggedIn) throws SQLException;
    void updateChecking (Customer customer, boolean deposit) throws SQLException, InterruptedException;
    void updateSaving (Customer customer, boolean deposit) throws SQLException, InterruptedException;
    void checkBalance (String currentUser) throws SQLException;
    void transferMoney (String currentUser, String toUser, int withdraw) throws SQLException, InterruptedException;
    void receiveMoney (String currentUser) throws SQLException, InterruptedException;
}
