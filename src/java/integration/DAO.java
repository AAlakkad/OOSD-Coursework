package integration;

import business.TransferObjects.*;

import java.sql.*;

/**
 * @author ammar
 */
public class DAO implements DAO_Interface {

    // Singleton object
    private static DAO theQuizDAO;
    private Connection databaseConnection;

    private DAO() {
    }

    public static DAO getQuizDAO() {
        if (theQuizDAO == null) {
            theQuizDAO = new DAO();
        }
        return theQuizDAO;
    }

    private Statement getConnection()
            throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // setup the connection with the DB.
            databaseConnection = DriverManager
                    .getConnection("jdbc:mysql://192.168.22.10/coursework_db?"
                            + "user=root&password=root");
            return databaseConnection.createStatement();
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException se) {
            System.out.println(se);
            throw se;
        }
    }

    private void closeConnection() throws SQLException {
        try {
            databaseConnection.close();
        } catch (SQLException se) {
            System.out.println(se);
            throw se;
        }
    }

    /**
     * @param username
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public UserInterface checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException {
        UserInterface user = new User();
        try {
            Statement myStatement = getConnection();
            String query = "SELECT username, type FROM users WHERE username like '" + username + "' AND password LIKE '" + password + "' LIMIT 1";
            myStatement.executeQuery(query);
            ResultSet result = myStatement.getResultSet();
            if (result.next()) {
                user.setUsername(result.getString(1));
                user.setType(result.getString(2));
            }
            closeConnection();
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
        return user;

    }

}
