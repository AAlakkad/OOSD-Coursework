package integration;

import business.TransferObjects.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ammar
 */
public interface DAO_Interface {

    UserInterface checkLogin(String username, String password) throws ClassNotFoundException, SQLException;

    HashMap<Integer, String> getTopicsNames() throws ClassNotFoundException, SQLException;

    ArrayList<TopicInterface> getTopics() throws ClassNotFoundException, SQLException;

    TopicInterface getTopic(Integer id) throws ClassNotFoundException, SQLException;

    void insertTopic(String name, String description) throws ClassNotFoundException, SQLException;

    void updateTopic(Integer id, String name, String description) throws ClassNotFoundException, SQLException;

    void deleteTopic(Integer id) throws ClassNotFoundException, SQLException;

}
