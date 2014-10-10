package integration;

import business.TransferObjects.UserInterface;
import java.sql.SQLException;

/**
 *
 * @author ammar
 */
public interface DAO_Interface {
    UserInterface checkLogin(String username, String password) throws ClassNotFoundException, SQLException ;
}
