package AfkoAPI.Model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Abbreviation {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "organisation")
    private Organisation organisation;

    @Column(name = "isUnderReview")
    private boolean isUnderReview;



    // The default constructor exists only for the sake of JPA (https://spring.io/guides/gs/accessing-data-jpa/)
    protected Abbreviation() {}
    // creates a new id, do not use for already existing data!
    public Abbreviation(String name, String description, Organisation org) {
        this.name = name;
        this.description = description;
        this.organisation = org;
        this.isUnderReview = true;
        // for the uuid: https://jivimberg.io/blog/2018/11/05/using-uuid-on-spring-data-jpa-entities/
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id.toString();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Organisation getOrganisation() { return organisation; }

    public void setOrganisation(Organisation org) { this.organisation = org; }

    public boolean isUnderReview() {
        return isUnderReview;
    }

    public void setIsUnderReview(boolean review) {
        this.isUnderReview = review;
    }
}
