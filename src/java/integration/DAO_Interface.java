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
     *
     * @param username
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    User checkLogin(String username, String password) throws ClassNotFoundException, SQLException;

    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    HashMap<Integer, String> getDifficultiesNames() throws ClassNotFoundException, SQLException;

    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    HashMap<Integer, String> getTopicsNames() throws ClassNotFoundException, SQLException;

    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    ArrayList<Topic> getTopics() throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    Topic getTopic(Integer id) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param name
     * @param description
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void insertTopic(String name, String description) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id
     * @param name
     * @param description
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void updateTopic(Integer id, String name, String description) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void deleteTopic(Integer id) throws ClassNotFoundException, SQLException;

    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    ArrayList<Question> getQuestions() throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    Question getQuestion(Integer id) throws ClassNotFoundException, SQLException;

    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    Question getRandomQuestion() throws ClassNotFoundException, SQLException;

    /**
     *
     * @param topicId
     * @param difficultyId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    Question getRandomQuestion(Integer topicId, Integer difficultyId) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param title
     * @param topicId
     * @param difficultyId
     * @param correctAnswer
     * @param answer_1
     * @param answer_2
     * @param answer_3
     * @param answer_4
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void insertQuestion(String title, Integer topicId, Integer difficultyId, Integer correctAnswer, String answer_1, String answer_2, String answer_3, String answer_4) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id
     * @param title
     * @param topicId
     * @param difficultyId
     * @param correctAnswer
     * @param answer_1
     * @param answer_2
     * @param answer_3
     * @param answer_4
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void updateQuestion(Integer id, String title, Integer topicId, Integer difficultyId, Integer correctAnswer, String answer_1, String answer_2, String answer_3, String answer_4) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void deleteQuestion(Integer id) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param userId
     * @param topicId
     * @param difficulty_id
     * @param score
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void addScore(Integer userId, Integer topicId, Integer difficulty_id, Double score) throws ClassNotFoundException, SQLException;
}
