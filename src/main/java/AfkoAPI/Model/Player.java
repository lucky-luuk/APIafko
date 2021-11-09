package AfkoAPI.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Player {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private int score;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Organisation.class)
    private Set<Organisation> Organisations = new HashSet<>();

    protected Player() {}

    public Player(String name, int score, ArrayList<Organisation> organisations) {
        this.name = name;
        this.score = score;
        if (organisations != null) Organisations = new HashSet<Organisation>(organisations);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Set<Organisation> getOrganisations() {
        return Organisations;
    }
}
