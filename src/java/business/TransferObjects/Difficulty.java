
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

}
