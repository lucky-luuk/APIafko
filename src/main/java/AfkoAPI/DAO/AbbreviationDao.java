package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AbbreviationDao {
    @Autowired
    AbbreviationRepository abbrRep;
    @Autowired
    OrganisationRepository orgRep;

    public AbbreviationDao() {}

    public HTTPResponse addAbbreviation(String name, String description, String orgId) {
        Optional<Organisation> org = orgRep.findById(orgId);
        if (org.isEmpty()) return HTTPResponse.<Abbreviation>returnFailure("organisation with id: " + orgId + " does not exist");

        Abbreviation abbr = new Abbreviation("etc", "et cetera", org.get(), null);
        abbrRep.save(abbr);
        return HTTPResponse.<Abbreviation>returnSuccess(abbr);
    }
    public HTTPResponse getAbbreviationByID(String id) {
        Optional<Abbreviation> data = abbrRep.findById(id);

        if (data.isEmpty())
            return HTTPResponse.<Abbreviation>returnFailure("could not find id: " + id);
        return HTTPResponse.<Abbreviation>returnSuccess(data.get());
    }
    public HTTPResponse getAbbreviationByName(String name) {
        List<Abbreviation> data = abbrRep.findByName(name);

        if (data.isEmpty()) return HTTPResponse.<List<Abbreviation>>returnFailure("could not find name: " + name);
        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }
    public HTTPResponse getAbbreviationByOrgId(String orgId) {
        List<Abbreviation> data = abbrRep.findByOrganisations_id(orgId);

        if (data.isEmpty()) return HTTPResponse.<List<Abbreviation>>returnFailure("could not find organisation id: " + orgId);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

}
