package business.TransferObjects;

/**
 *
 * @author ammar
 */
public class Difficulty implements DifficultyInterface {

    private Integer id;
    private String name;

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
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

}
