package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.AccountRepository;
import AfkoAPI.Repository.OrganisationRepository;
import AfkoAPI.RequestObjects.AbbreviationRequestObject;
import AfkoAPI.services.AccountService;
import AfkoAPI.services.OrganisationService;
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

    public HTTPResponse addAbbreviations(AbbreviationRequestObject[] abbreviationRequestObjects) {
        Abbreviation[] abbrs = new Abbreviation[abbreviationRequestObjects.length];

        for (int i = 0; i < abbreviationRequestObjects.length; i++) {
            HTTPResponse<Abbreviation> response = addAbbreviation(abbreviationRequestObjects[i]);

            if (response.isSuccess())
                abbrs[i] = response.getData();
            else
                return HTTPResponse.<Abbreviation[]>returnFailure(response.getError());
        }

        return HTTPResponse.<Abbreviation[]>returnSuccess(abbrs);
    }

    public HTTPResponse addAbbreviation(AbbreviationRequestObject abbr) {

        HTTPResponse<Organisation[]> response = OrganisationService.getOrganisationsByIds(orgRep, abbr.getOrganisations());
        if (!response.isSuccess())
            return HTTPResponse.<Abbreviation>returnFailure(response.getError());

        // make it possible to not add an account
        HTTPResponse<Account> accountResponse = AccountService.getAccountIfIdNotNull(accRep, abbr.getCreatedBy());
        if (!accountResponse.isSuccess())
            return HTTPResponse.<Abbreviation>returnFailure(accountResponse.getError());

        Abbreviation a = new Abbreviation(abbr.getName(), abbr.getDescription(), new ArrayList<Organisation>(List.of(response.getData())), accountResponse.getData());
        abbrRep.save(a);
        return HTTPResponse.<Abbreviation>returnSuccess(a);
    }

    public HTTPResponse getAbbreviationByID(String id) {
        Optional<Abbreviation> data = abbrRep.findById(id);

        if (data.isEmpty())
            return HTTPResponse.<Abbreviation>returnFailure("could not find id: " + id);

        return HTTPResponse.<Abbreviation>returnSuccess(data.get());
    }

    public HTTPResponse getAbbreviationByName(String name) {
        List<Abbreviation> data = abbrRep.findByNameStartsWith(name);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find name: " + name);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    public HTTPResponse getAbbreviationByOrgId(String orgId) {
        List<Abbreviation> data = abbrRep.findByOrganisations_id(orgId);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find organisation id: " + orgId);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    public HTTPResponse getAbbreviationByReported(boolean reported) {
        List<Abbreviation> data = abbrRep.findByIsUnderReview(reported);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find reported abbreviations: " + reported);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }
}
