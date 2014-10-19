package business.TransferObjects;

/**
 *
 * @author ammar
 */
public interface TopicInterface {

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
    String getName();

    /**
     *
     * @param name
     */
    void setName(String name);

    /**
     *
     * @return
     */
    String getDescription();

    /**
     *
     * @param description
     */
    void setDescription(String description);
}
