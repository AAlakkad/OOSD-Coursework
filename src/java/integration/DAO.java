package integration;

import business.TransferObjects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    @Override
    public HashMap<Integer, String> getTopicsNames() throws ClassNotFoundException, SQLException {
        try {
            HashMap<Integer, String> topics = new HashMap<Integer, String>();
            Statement myStatement = getConnection();
            String query1 = "SELECT id, name FROM topics;";
            ResultSet results = myStatement.executeQuery(query1);

            while (results.next()) {
                topics.put(results.getInt("id"), results.getString("name"));

            }
            closeConnection();
            return topics;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }

    @Override
    public ArrayList<TopicInterface> getTopics() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<TopicInterface> topics = new ArrayList<TopicInterface>();
            Statement myStatement = getConnection();
            String query1 = "SELECT * FROM topics;";
            ResultSet results = myStatement.executeQuery(query1);

            while (results.next()) {
                topics.add(populateTopicObject(results));
            }
            closeConnection();
            return topics;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }

    private Topic populateTopicObject(ResultSet result) throws SQLException {
        Integer topicId = Integer.parseInt(result.getString("id"));
        String topicName = result.getString("name");
        String topicDescription = result.getString("description");

        return new Topic(topicId, topicName, topicDescription);
    }

    @Override
    public TopicInterface getTopic(Integer id) throws ClassNotFoundException, SQLException {
        try {
            TopicInterface topic = new Topic();
            Statement myStatement = getConnection();
            String query1 = "SELECT * FROM topics WHERE id = " + id + " LIMIT 1;";
            ResultSet result = myStatement.executeQuery(query1);
            if (result.next()) {
                topic = populateTopicObject(result);
            }

            closeConnection();
            return topic;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }

    @Override
    public void updateTopic(Integer id, String name, String description) throws ClassNotFoundException, SQLException {
        try {
            Statement myStatement = getConnection();
            String query1 = "UPDATE topics SET name='" + name + "', description='" + description + "' WHERE id = " + id + " LIMIT 1;";
            myStatement.executeUpdate(query1);

            closeConnection();
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }

    @Override
    public void insertTopic(String name, String description) throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Start inserting");
            Statement myStatement = getConnection();
            String query = "INSERT INTO topics (name, description, create_date) VALUES('" + name + "', '" + description + "', NOW());";
            myStatement.executeUpdate(query);

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
