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
     * @param username
     * @param type
     */
    public User(String username, String type) {
        this.setUsername(username);
        this.setType(type);
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @param username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     *
     * @param type
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param topicId
     * @return
     */
    @Override
    public int getScore(int topicId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
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
     * @return
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
     * @return
     */
    @Override
    public Boolean isContestant() {
        if (this.type != null) {
            return this.type.equals("contestant");
        }
        return false;
    }

}
