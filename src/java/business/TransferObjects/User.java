package business.TransferObjects;

/**
 *
 * @author ammar
 */
public class User implements UserInterface {

    private Integer id;
    private String username, type;

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     *
     * @param username username
     * @param type type
     */
    public User(String username, String type) {
        this.setUsername(username);
        this.setType(type);
    }

    /**
     *
     * @return id
     */
    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     *
     * @param id id
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @param username username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return type
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     *
     * @param type type
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param topicId topic id
     * @return score
     */
    @Override
    public int getScore(int topicId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return boolean
     */
    @Override
    public Boolean exists() {
        if (this.username == null || this.type == null) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return boolean
     */
    @Override
    public Boolean isAdministrator() {
        if (this.type != null) {
            return this.type.equals("administrator");
        }
        return false;
    }

    /**
     *
     * @return boolean
     */
    @Override
    public Boolean isContestant() {
        if (this.type != null) {
            return this.type.equals("contestant");
        }
        return false;
    }

}
