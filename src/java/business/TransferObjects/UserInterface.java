package business.TransferObjects;


public interface UserInterface {

    Integer getId();

    void setId(Integer id);

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
