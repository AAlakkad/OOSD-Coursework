package business.TransferObjects;

/**
 * @author ammar
 */
public interface UserInterface {

    String getUsername();

    void setUsername(String username);

    String getType();

    void setType(String type);

    // Check if UserInterface instance has username/type fields filled
    Boolean exists();

    Boolean isAdministrator();

    Boolean isContestant();

    int getScore(int topicId);
}
