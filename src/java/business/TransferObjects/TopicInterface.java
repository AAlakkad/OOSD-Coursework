package business.TransferObjects;

/**
 *
 * @author ammar
 */
public interface TopicInterface {

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
     * @return name
     */
    String getName();

    /**
     *
     * @param name name
     */
    void setName(String name);

    /**
     *
     * @return description
     */
    String getDescription();

    /**
     *
     * @param description description
     */
    void setDescription(String description);
}
