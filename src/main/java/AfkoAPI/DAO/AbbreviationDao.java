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
    public HTTPResponse addAbbreviation(String name, String description, String[] orgIds, String accountId) {
        ArrayList<Organisation> organisations = new ArrayList<>();
        for (String id : orgIds)
        {
            Optional<Organisation> org = orgRep.findById(id);
            if (org.isEmpty()) return HTTPResponse.<Abbreviation>returnFailure("organisation with id: " + id + " does not exist");
            organisations.add(org.get());
        }
        // make it possible to not add an account
        Account acc = null;
        if (!accountId.equals("null")) {
            Optional<Account> optAcc = accRep.findById(accountId);
            if (optAcc.isEmpty()) return HTTPResponse.<Abbreviation>returnFailure("account with id: " + accountId + " does not exist");
            else acc = optAcc.get();
        }

        Abbreviation abbr = new Abbreviation(name, description, organisations, acc);
        abbrRep.save(abbr);
        return HTTPResponse.<Abbreviation>returnSuccess(abbr);
    }

    public Iterable<Abbreviation> save(List<Abbreviation> abbreviations) {
        return abbrRep.saveAll(abbreviations);
    }

    public HTTPResponse getAbbreviationByID(String id) {
        Optional<Abbreviation> data = abbrRep.findById(id);

        if (data.isEmpty())
            return HTTPResponse.<Abbreviation>returnFailure("could not find id: " + id);
        return HTTPResponse.<Abbreviation>returnSuccess(data.get());
    }

    public HTTPResponse getAbbreviationByName(String name) {
        List<Abbreviation> data = abbrRep.findByNameStartsWith(name);
        if (data.isEmpty()) return HTTPResponse.<List<Abbreviation>>returnFailure("could not find name: " + name);
        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }
    public HTTPResponse getAbbreviationByOrgId(String orgId) {
        List<Abbreviation> data = abbrRep.findByOrganisations_id(orgId);

        if (data.isEmpty()) return HTTPResponse.<List<Abbreviation>>returnFailure("could not find organisation id: " + orgId);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    public HTTPResponse getAbbreviationByReported(boolean reported) {
        List<Abbreviation> data = abbrRep.findByIsUnderReview(reported);

        if (data.isEmpty()) return HTTPResponse.<List<Abbreviation>>returnFailure("could not find reported abbreviations: " + reported);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

}
