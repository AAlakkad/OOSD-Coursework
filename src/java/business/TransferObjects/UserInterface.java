package business.TransferObjects;

/**
 *
 * @author ammar
 */
public interface UserInterface {

    String getUsername();

    void setUsername(String username);

    String getType();

    // Check if UserInterface instance has username/type fields filled
    Boolean exists();

    Boolean isAdministrator();

    Boolean isContestant();

    void setType(String type);

    int getScore(int topicId);
}
