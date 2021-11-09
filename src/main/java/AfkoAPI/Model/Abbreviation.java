package AfkoAPI.Model;

import AfkoAPI.RequestObjects.AbbreviationRequestObject;

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

    @ManyToMany(targetEntity = Organisation.class)
    private Set<Organisation> organisations = new HashSet<>();

    @ManyToOne(targetEntity = Account.class)
    private Account createdBy;

    @Column(name = "isUnderReview")
    private boolean isUnderReview;


    // The default constructor exists only for the sake of JPA (https://spring.io/guides/gs/accessing-data-jpa/)
    public Abbreviation() {}
    // creates a new id, do not use for already existing data!
    public Abbreviation(String name, String description, Organisation[] orgs, Account createdBy) {
        this.name = name;
        this.description = description;
        if (orgs != null) this.organisations = new HashSet<Organisation>(List.of(orgs));
        // always true, a new abbreviation must always be checked by an admin
        this.isUnderReview = true;
        this.createdBy = createdBy;
        // for the uuid: https://jivimberg.io/blog/2018/11/05/using-uuid-on-spring-data-jpa-entities/
        this.id = UUID.randomUUID().toString();
    }

    public Abbreviation(String name, String description, Set<Organisation> orgs, Account createdBy) {
        this.name = name;
        this.description = description;
        if (orgs != null) this.organisations = orgs;
        // always true, a new abbreviation must always be checked by an admin
        this.isUnderReview = true;
        this.createdBy = createdBy;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrganisations(Set<Organisation> organisations) {
        this.organisations = organisations;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public void setUnderReview(boolean underReview) {
        isUnderReview = underReview;
    }
}
