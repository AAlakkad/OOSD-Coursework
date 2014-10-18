package integration;

import business.TransferObjects.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public interface DAO_Interface {

    User checkLogin(String username, String password) throws ClassNotFoundException, SQLException;

    HashMap<Integer, String> getDifficultiesNames() throws ClassNotFoundException, SQLException;

    HashMap<Integer, String> getTopicsNames() throws ClassNotFoundException, SQLException;

    ArrayList<Topic> getTopics() throws ClassNotFoundException, SQLException;

    Topic getTopic(Integer id) throws ClassNotFoundException, SQLException;

    void insertTopic(String name, String description) throws ClassNotFoundException, SQLException;

    void updateTopic(Integer id, String name, String description) throws ClassNotFoundException, SQLException;

    void deleteTopic(Integer id) throws ClassNotFoundException, SQLException;

    ArrayList<Question> getQuestions() throws ClassNotFoundException, SQLException;

    Question getQuestion(Integer id) throws ClassNotFoundException, SQLException;

    Question getRandomQuestion() throws ClassNotFoundException, SQLException;

    Question getRandomQuestion(Integer topicId, Integer difficultyId) throws ClassNotFoundException, SQLException;

    void insertQuestion(String title, Integer topicId, Integer difficultyId, Integer correctAnswer, String answer_1, String answer_2, String answer_3, String answer_4) throws ClassNotFoundException, SQLException;

    void updateQuestion(Integer id, String title, Integer topicId, Integer difficultyId, Integer correctAnswer, String answer_1, String answer_2, String answer_3, String answer_4) throws ClassNotFoundException, SQLException;

    void deleteQuestion(Integer id) throws ClassNotFoundException, SQLException;

    void addScore(Integer userId, Integer topicId, Integer difficulty_id, Double score) throws ClassNotFoundException, SQLException;
}
