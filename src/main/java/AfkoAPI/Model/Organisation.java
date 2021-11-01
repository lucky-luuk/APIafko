package AfkoAPI.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Organisation {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    protected Organisation() {}
    public Organisation(String name, String id) {
        this.id = id;
        this.name = name;
    }

    public Organisation(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
