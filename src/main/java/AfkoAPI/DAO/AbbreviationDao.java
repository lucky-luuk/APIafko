package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.AccountRepository;
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
    @Autowired
    AccountRepository accRep;

    public AbbreviationDao() {}

    public HTTPResponse addDummyAbbreviation() {
        Abbreviation abbr = new Abbreviation("dummy", "dit is een fake abbreviation", null, null);
        abbrRep.save(abbr);
        return HTTPResponse.<Abbreviation>returnSuccess(abbr);
    }
    public HTTPResponse addAbbreviation(String name, String description, String orgId, String accountId) {
        Optional<Organisation> org = orgRep.findById(orgId);
        Optional<Account> acc = accRep.findById(accountId);

        if (org.isEmpty()) return HTTPResponse.<Abbreviation>returnFailure("organisation with id: " + orgId + " does not exist");
        if (acc.isEmpty()) return HTTPResponse.<Abbreviation>returnFailure("account with id: " + accountId + " does not exist");

        Abbreviation abbr = new Abbreviation(name, description, org.get(), acc.get());
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
