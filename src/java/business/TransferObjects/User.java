package business.TransferObjects;

/**
 * @author ammar
 */
public class User implements UserInterface {

    private String username, type;

    public User() {
    }

    public User(String username, String type) {
        this.setUsername(username);
        this.setType(type);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getScore(int topicId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean exists() {
        if (this.username == null || this.type == null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean isAdministrator() {
        if (this.type != null) {
            return this.type.equals("administrator");
        }
        return false;
    }

    @Override
    public Boolean isContestant() {
        if (this.type != null) {
            return this.type.equals("contestant");
        }
        return false;
    }

}
