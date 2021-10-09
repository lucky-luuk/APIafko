package AfkoAPI.Model;

import AfkoAPI.IdClass.AbbreviationOrganisationId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(AbbreviationOrganisationId.class)
public class AbbreviationOrganisation implements Serializable {
    @Id
    @OneToOne(targetEntity = Abbreviation.class)
    private Abbreviation abbreviation;

    @Id
    @OneToOne(targetEntity = Organisation.class)
    private Organisation organisation;

    protected AbbreviationOrganisation() {}
    public AbbreviationOrganisation(Abbreviation abbreviation, Organisation organisation) {
        this.abbreviation = abbreviation;
        this.organisation = organisation;
    }


    public Abbreviation getAbbreviation() {
        return abbreviation;
    }

    public Organisation getOrganisation() {
        return organisation;
    }
}
