package xmu.crms.view.vo;

public class TopicVO {

    private int id;
    private String name;

    public TopicVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TopicVO() {
    }

    @Override
    public String toString() {
        return "TopicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
