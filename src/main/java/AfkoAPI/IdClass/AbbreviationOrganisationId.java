package AfkoAPI.IdClass;

import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

// must be created to use composite keys https://stackoverflow.com/questions/60414409/spring-jpa-composite-key-this-class-does-not-define-an-idclass
public class AbbreviationOrganisationId implements Serializable {
    private String abbreviation;
    private String organisation;

    protected AbbreviationOrganisationId() {}
    public AbbreviationOrganisationId(Abbreviation abbreviation, Organisation organisation) {
        this.abbreviation = abbreviation.getId();
        this.organisation = organisation.getId();
    }
    public String getAbbreviation() {
        return abbreviation;
    }

    public String getOrganisation() {
        return organisation;
    }
}
