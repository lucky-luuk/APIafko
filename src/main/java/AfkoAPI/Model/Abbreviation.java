package AfkoAPI.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
    @JoinColumn(name = "created_by")
    private Account createdBy;

    @Column(name = "isUnderReview")
    private boolean isUnderReview;



    // The default constructor exists only for the sake of JPA (https://spring.io/guides/gs/accessing-data-jpa/)
    protected Abbreviation() {}
    // creates a new id, do not use for already existing data!
    public Abbreviation(String name, String description, Organisation org, Account createdBy) {
        this.name = name;
        this.description = description;
        //this.abbreviationOrganisations.add(new AbbreviationOrganisation(this, org));
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

    //public Set<AbbreviationOrganisation> getAbbreviationOrganisations() { return abbreviationOrganisations; }
    // todo maybe simplify a bit later somehow
//    public Set<Organisation> getOrganisations() {
//        Set<Organisation> orgs = new HashSet<>();
//        for (Iterator<AbbreviationOrganisation> it = abbreviationOrganisations.iterator(); it.hasNext();) {
//            AbbreviationOrganisation abbrorg = it.next();
//            if (abbrorg.getAbbreviation().getId().equals(this.id)) {
//                orgs.add(abbrorg.getOrganisation());
//            }
//        }
//        return orgs;
//    }
    public boolean isUnderReview() {
        return isUnderReview;
    }
}
