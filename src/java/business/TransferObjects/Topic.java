package business.TransferObjects;

/**
 *
 * @author ammar
 */
public final class Topic implements TopicInterface {

    private Integer id;
    private String name = "", description = "";

    /**
     * Default constructor
     */
    public Topic() {

    }

    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public Topic(Integer id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
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
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @param description
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
