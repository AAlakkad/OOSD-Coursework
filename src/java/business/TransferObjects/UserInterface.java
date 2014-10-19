package business.TransferObjects;

/**
 *
 * @author ammar
 */
public interface UserInterface {

    /**
     *
     * @return id
     */
    Integer getId();

    /**
     *
     * @param id id
     */
    void setId(Integer id);

    /**
     *
     * @return username
     */
    String getUsername();

    /**
     *
     * @param username username
     */
    void setUsername(String username);

    /**
     *
     * @return type
     */
    String getType();

    /**
     *
     * @param type type
     */
    void setType(String type);

    // Check if UserInterface instance has username/type fields filled
    /**
     *
     * @return boolean
     */
    Boolean exists();

    /**
     *
     * @return boolean
     */
    Boolean isAdministrator();

    /**
     *
     * @return boolean
     */
    Boolean isContestant();

    /**
     *
     * @param topicId topic id
     * @return score
     */
    int getScore(int topicId);
}
