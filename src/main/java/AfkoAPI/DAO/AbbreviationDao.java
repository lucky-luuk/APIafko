package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.AccountRepository;
import AfkoAPI.Repository.OrganisationRepository;
import AfkoAPI.RequestObjects.AbbreviationRequestObject;
import AfkoAPI.services.AbbreviationService;
import AfkoAPI.services.AccountService;
import AfkoAPI.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class AbbreviationDao {
    @Autowired
    AbbreviationRepository abbrRep;
    @Autowired
    OrganisationRepository orgRep;
    @Autowired
    AccountRepository accRep;

    public AbbreviationDao() {
    }

    public HTTPResponse addAbbreviations(Abbreviation[] abbreviationRequestObjects) {
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

    public HTTPResponse addAbbreviation(Abbreviation abbr) {

        Abbreviation a = new Abbreviation(abbr.getName(), abbr.getDescription(), abbr.getOrganisations(), abbr.getCreatedBy());
        abbrRep.save(a);
        return HTTPResponse.<Abbreviation>returnSuccess(a);
    }

    public HTTPResponse getAbbreviationByID(String id) {
        Optional<Abbreviation> data = abbrRep.findById(id);

        if (data.isEmpty())
            return HTTPResponse.<Abbreviation>returnFailure("could not find id: " + id);

        return HTTPResponse.<Abbreviation>returnSuccess(data.get());
    }

    public HTTPResponse getAbbreviationByName(String name, String amount) {
        List<Abbreviation> data = abbrRep.findByNameStartsWith(name);
        data = AbbreviationService.trimListByAmount(data, amount);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find name: " + name);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    public HTTPResponse getAbbreviationByOrgId(String orgId, String amount) {
        List<Abbreviation> data = abbrRep.findByOrganisations_id(orgId);
        data = AbbreviationService.trimListByAmount(data, amount);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find organisation id: " + orgId);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    public HTTPResponse getAbbreviationByReported(boolean reported, String amount) {
        List<Abbreviation> data = abbrRep.findByIsUnderReview(reported);
        data = AbbreviationService.trimListByAmount(data, amount);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find reported abbreviations: " + reported);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    public HTTPResponse changeAbbreviationById(Abbreviation[] abbrs) { //neemt 2 abbreviation vervang de 1e voor de ander
        Abbreviation old = abbrs[0];
        Abbreviation newObject = abbrs[1];
        Optional<Abbreviation> abbr = abbrRep.findById(old.getId());

        if (abbr.isEmpty()){
            return HTTPResponse.<Abbreviation[]>returnFailure("could not find abbreviation with id: " + old.getId());
        }

        abbr.get().setName(newObject.getName());
        abbr.get().setCreatedBy(newObject.getCreatedBy());
        abbr.get().setDescription(newObject.getDescription());
        abbr.get().setOrganisations(newObject.getOrganisations());
        abbr.get().setUnderReview(newObject.isUnderReview());
        abbrRep.save(abbr.get());
        return HTTPResponse.<Abbreviation[]>returnSuccess(abbrs);
    }


    public HTTPResponse deleteAbbreviations(Abbreviation[] abbrs){
        for (Abbreviation abbr: abbrs){
            abbrRep.deleteById(abbr.getId());
        }
        return HTTPResponse.returnSuccess(abbrs);
    }
}

