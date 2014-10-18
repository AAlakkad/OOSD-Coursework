package integration;

import business.TransferObjects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class DAO implements DAO_Interface {

    // Quiz questions number to use app-wide
    public final static Integer quizQuestions = 10;
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
                    .getConnection("jdbc:mysql://localhost/coursework_db?"
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
    public User checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException {
        User user = new User();
        try {
            Statement myStatement = getConnection();
            String query = "SELECT id, username, type FROM users WHERE username like '" + username + "' AND password LIKE '" + password + "' LIMIT 1";
            myStatement.executeQuery(query);
            ResultSet result = myStatement.getResultSet();
            if (result.next()) {
                user.setId(result.getInt(1));
                user.setUsername(result.getString(2));
                user.setType(result.getString(3));
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
    public HashMap<Integer, String> getDifficultiesNames() throws ClassNotFoundException, SQLException {
        try {
            HashMap<Integer, String> topics = new HashMap<Integer, String>();
            Statement myStatement = getConnection();
            String query1 = "SELECT id, name FROM difficulties;";
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
    public ArrayList<Topic> getTopics() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<Topic> topics = new ArrayList<Topic>();
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
    public Topic getTopic(Integer id) throws ClassNotFoundException, SQLException {
        try {
            Topic topic = new Topic();
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

    /**
     * Execute a query that don't return anything, e.g. insert, update, or delete
     */
    private void executeQuery(String query) throws ClassNotFoundException, SQLException {
        try {
            Statement myStatement = getConnection();
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


    @Override
    public void updateTopic(Integer id, String name, String description) throws ClassNotFoundException, SQLException {
        String query1 = "UPDATE topics SET name='" + name + "', description='" + description + "' WHERE id = " + id + " LIMIT 1;";
        this.executeQuery(query1);
    }

    @Override
    public void insertTopic(String name, String description) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO topics (name, description, create_date) VALUES('" + name + "', '" + description + "', NOW());";
        this.executeQuery(query);
    }

    @Override
    public void deleteTopic(Integer id) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM topics WHERE id = " + id + " LIMIT 1;";
        this.executeQuery(query);
    }

    @Override
    public ArrayList<Question> getQuestions() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<Question> questions = new ArrayList<Question>();
            Statement myStatement = getConnection();
            String query1 = "SELECT * FROM questions;";
            ResultSet results = myStatement.executeQuery(query1);

            while (results.next()) {
                questions.add(populateQuestionObject(results));
            }
            closeConnection();
            return questions;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }

    private Question populateQuestionObject(ResultSet result) throws SQLException {
        Integer id = result.getInt("id"),
                topicId = result.getInt("topic_id"),
                difficultyId = result.getInt("difficulty_id"),
                correctAnswer = result.getInt("correct_answer");
        String title = result.getString("title"),
                answer_1 = result.getString("answer_1"),
                answer_2 = result.getString("answer_2"),
                answer_3 = result.getString("answer_3"),
                answer_4 = result.getString("answer_4");

        return new Question(id, topicId, difficultyId, correctAnswer, title, answer_1, answer_2, answer_3, answer_4);
    }

    @Override
    public Question getQuestion(Integer id) throws ClassNotFoundException, SQLException {
        try {
            Question question = new Question();
            Statement myStatement = getConnection();
            String query1 = "SELECT * FROM questions WHERE id = " + id + " LIMIT 1;";
            ResultSet result = myStatement.executeQuery(query1);
            if (result.next()) {
                question = populateQuestionObject(result);
            }

            closeConnection();
            return question;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }

    @Override
    public Question getRandomQuestion() throws ClassNotFoundException, SQLException {
        try {
            Question question = new Question();
            Statement myStatement = getConnection();
            String query1 = "SELECT * FROM questions ORDER BY RAND() LIMIT 1;";
            ResultSet result = myStatement.executeQuery(query1);
            if (result.next()) {
                question = populateQuestionObject(result);
            }
            closeConnection();
            return question;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }

    @Override
    public Question getRandomQuestion(Integer topicId) throws ClassNotFoundException, SQLException {
        try {
            Question question = new Question();
            Statement myStatement = getConnection();
            String query1 = "SELECT * FROM questions WHERE topic_id = " + topicId + " ORDER BY RAND() LIMIT 1;";
            ResultSet result = myStatement.executeQuery(query1);
            if (result.next()) {
                question = populateQuestionObject(result);
            }

            closeConnection();
            return question;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            throw cnfe;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            throw sqle;
        }
    }

    @Override
    public void insertQuestion(String title, Integer topicId, Integer difficultyId, Integer correctAnswer, String answer_1, String answer_2, String answer_3, String answer_4) throws ClassNotFoundException, SQLException {
        String query1 = "INSERT INTO questions VALUES(NULL, " + topicId + ", " + difficultyId + ", '" + title + "', " + correctAnswer + ", '" + answer_1 + "', '" + answer_2 + "', '" + answer_3 + "', '" + answer_4 + "', NOW());";
        this.executeQuery(query1);
    }

    @Override
    public void updateQuestion(Integer id, String title, Integer topicId, Integer difficultyId, Integer correctAnswer, String answer_1, String answer_2, String answer_3, String answer_4) throws ClassNotFoundException, SQLException {
        String query1 = "UPDATE questions SET title='" + title + "', "
                + "topic_id='" + topicId + "', "
                + "difficulty_id='" + difficultyId + "', "
                + "correct_answer='" + correctAnswer + "', "
                + "answer_1='" + answer_1 + "', "
                + "answer_2='" + answer_2 + "', "
                + "answer_3='" + answer_3 + "', "
                + "answer_4='" + answer_4 + "'"
                + " WHERE id = " + id + " LIMIT 1;";
        this.executeQuery(query1);
    }

    @Override
    public void deleteQuestion(Integer id) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM questions WHERE id = " + id + " LIMIT 1;";
        this.executeQuery(query);
    }

    @Override
    public void addScore(Integer userId, Integer topicId, Integer difficulty_id, Double score) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO scores VALUES (NULL, " + userId + ", " + topicId + " ," + difficulty_id + ", " + score + ", NOW());";
        this.executeQuery(query);
    }

}
