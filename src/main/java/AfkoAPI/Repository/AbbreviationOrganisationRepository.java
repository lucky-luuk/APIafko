package AfkoAPI.Repository;

import AfkoAPI.IdClass.AbbreviationOrganisationId;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.AbbreviationOrganisation;
import AfkoAPI.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbbreviationOrganisationRepository extends JpaRepository<AbbreviationOrganisation, AbbreviationOrganisationId> {
    List<AbbreviationOrganisation> findByOrganisation_id(String id);
    List<AbbreviationOrganisation> findByAbbreviation_id(String id);
}
