package business.TransferObjects;

/**
 *
 * @author ammar
 */
public interface UserInterface {

    /**
     *
     * @return
     */
    Integer getId();

    /**
     *
     * @param id
     */
    void setId(Integer id);

    /**
     *
     * @return
     */
    String getUsername();

    /**
     *
     * @param username
     */
    void setUsername(String username);

    /**
     *
     * @return
     */
    String getType();

    /**
     *
     * @param type
     */
    void setType(String type);

    // Check if UserInterface instance has username/type fields filled

    /**
     *
     * @return
     */
        Boolean exists();

    /**
     *
     * @return
     */
    Boolean isAdministrator();

    /**
     *
     * @return
     */
    Boolean isContestant();

    /**
     *
     * @param topicId
     * @return
     */
    int getScore(int topicId);
}
