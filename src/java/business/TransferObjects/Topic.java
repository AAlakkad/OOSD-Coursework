package business.TransferObjects;


public final class Topic implements TopicInterface {

    private Integer id;
    private String name = "", description = "";

    public Topic() {

    }

    public Topic(Integer id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
