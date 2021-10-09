package AfkoAPI.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private int score;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Organisation.class)
    private Set<Organisation> Organisations = new HashSet<>();

    protected Player() {}
    public Player(String name, int score, Set<Organisation> organisations) {
        this.name = name;
        this.score = score;
        Organisations = organisations;
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
