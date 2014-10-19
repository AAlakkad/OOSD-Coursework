package integration;

import business.TransferObjects.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ammar
 */
public interface DAO_Interface {

    /**
     * @param username Username
     * @param password Passowrd
     * @return User object
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    User checkLogin(String username, String password) throws ClassNotFoundException, SQLException;

    /**
     *
     * @return Difficulties names
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    HashMap<Integer, String> getDifficultiesNames() throws ClassNotFoundException, SQLException;

    /**
     *
     * @return Topics names
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    HashMap<Integer, String> getTopicsNames() throws ClassNotFoundException, SQLException;

    /**
     *
     * @return Topics
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    ArrayList<Topic> getTopics() throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id topic id
     * @return Topic
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    Topic getTopic(Integer id) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param name topic name
     * @param description topic description
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    void insertTopic(String name, String description) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id topic id
     * @param name topic name
     * @param description topic description
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    void updateTopic(Integer id, String name, String description) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id topic id
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    void deleteTopic(Integer id) throws ClassNotFoundException, SQLException;

    /**
     *
     * @return Questions Array
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    ArrayList<Question> getQuestions() throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id question id
     * @return Question
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    Question getQuestion(Integer id) throws ClassNotFoundException, SQLException;

    /**
     *
     * @return random question
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    Question getRandomQuestion() throws ClassNotFoundException, SQLException;

    /**
     *
     * @param topicId topic id
     * @param difficultyId difficulty id
     * @return Question
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    Question getRandomQuestion(Integer topicId, Integer difficultyId) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param title question title
     * @param topicId topic id
     * @param difficultyId difficulty id
     * @param correctAnswer correct answer
     * @param answer_1 answer 1
     * @param answer_2 answer 2
     * @param answer_3 answer 3
     * @param answer_4 answer 4
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    void insertQuestion(String title, Integer topicId, Integer difficultyId, Integer correctAnswer, String answer_1, String answer_2, String answer_3, String answer_4) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id question id
     * @param title question title
     * @param topicId topic id
     * @param difficultyId difficulty id
     * @param correctAnswer correct answer
     * @param answer_1 answer 1
     * @param answer_2 answer 2
     * @param answer_3 answer 3
     * @param answer_4 answer 4
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    void updateQuestion(Integer id, String title, Integer topicId, Integer difficultyId, Integer correctAnswer, String answer_1, String answer_2, String answer_3, String answer_4) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id question id
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    void deleteQuestion(Integer id) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param userId user id
     * @param topicId topic id
     * @param difficulty_id difficulty id
     * @param score score
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if SQL error happends
     */
    void addScore(Integer userId, Integer topicId, Integer difficulty_id, Double score) throws ClassNotFoundException, SQLException;
}
