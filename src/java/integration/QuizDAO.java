/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import java.sql.*;

/**
 *
 * @author ammar
 */
public class QuizDAO implements DAO_Interface {

    // Singleton object
    private static QuizDAO theQuizDAO;
    private Connection databaseConnection;

    private QuizDAO() {
    }

    public static QuizDAO getQuizDAO() {
        if (theQuizDAO == null) {
            theQuizDAO = new QuizDAO();
        }
        return theQuizDAO;
    }

    private Statement getConnection()
            throws ClassNotFoundException, SQLException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String sourceURL = new String("jdbc:odbc:olympicDB");
            databaseConnection = DriverManager.getConnection(sourceURL, "admin", "");
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
     * @TODO replace this with appropriate method, and do the rest of required ones
     *
     * @param eventName
     * @param sportKey
     * @param venueKey
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void insertEvent(String eventName, String sportKey, String venueKey)
            throws ClassNotFoundException, SQLException {
        try {
            Statement myStatement = getConnection();
            String writeString = "INSERT INTO Event (EventName,SportKey, VenueKey) VALUES('" + eventName + "'  , " + sportKey + ", " + venueKey + ")";
            myStatement.executeUpdate(writeString);
            closeConnection();
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }
}
