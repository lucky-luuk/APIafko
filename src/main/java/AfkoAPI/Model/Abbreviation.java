package AfkoAPI.Model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Abbreviation {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(targetEntity = Organisation.class, cascade = CascadeType.ALL)
    private Set<Organisation> organisations = new HashSet<>();

    @ManyToOne(targetEntity = Account.class)
    private Account createdBy;

    @Column(name = "isUnderReview")
    private boolean isUnderReview;


    // The default constructor exists only for the sake of JPA (https://spring.io/guides/gs/accessing-data-jpa/)
    protected Abbreviation() {}
    // creates a new id, do not use for already existing data!
    public Abbreviation(String name, String description, Organisation org, Account createdBy) {
        this.name = name;
        this.description = description;
        this.organisations.add(org);
        this.isUnderReview = true;
        // for the uuid: https://jivimberg.io/blog/2018/11/05/using-uuid-on-spring-data-jpa-entities/
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUnderReview() {
        return isUnderReview;
    }

    public Account getCreatedBy() { return createdBy; }

    public Set<Organisation> getOrganisations() {
        return organisations;
    }

    public void addOrganisation(Organisation org) {
        organisations.add(org);
    }


}
