package AfkoAPI.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Player {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private int score;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Abbreviation.class)
    private Set abbreviations = new HashSet<>();

}
