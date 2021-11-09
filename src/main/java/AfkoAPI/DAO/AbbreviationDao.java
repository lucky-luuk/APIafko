package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.BlacklistRepository;
import AfkoAPI.services.BlacklistService;
import AfkoAPI.services.TrimListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class AbbreviationDao {

    @Autowired
    AbbreviationRepository abbrRep;
    @Autowired
    BlacklistRepository blacklistRepository;

    public AbbreviationDao() {
    }

    /** add multiple abbreviations to the database
     * @param abbreviationRequestObjects the abbreviations to add
     * @return HTTPResponse
     */
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

    /** add a single abbreviation
     * @param abbr the abbreviation to add
     * @return HTTPResponse
     */
    public HTTPResponse addAbbreviation(Abbreviation abbr) {
        Abbreviation a = new Abbreviation(abbr.getName(), abbr.getDescription(), abbr.getOrganisations(), abbr.getCreatedBy());
        return BlacklistService.filterAbbreviationAndSaveToRepository(blacklistRepository, abbrRep, a);
    }

    /**
     * @param id the id to filter with
     * @return an HTTPResponse containing a single abbreviation
     */
    public HTTPResponse getAbbreviationByID(String id) {
        Optional<Abbreviation> data = abbrRep.findById(id);

        if (data.isEmpty())
            return HTTPResponse.<Abbreviation>returnFailure("could not find id: " + id);

        return HTTPResponse.<Abbreviation>returnSuccess(data.get());
    }

    /**
     * @param name the name to filter with
     * @param amount the maximum amount of abbreviations to return
     * @return an HTTPResponse containing a list of abbreviations
     */
    public HTTPResponse getAbbreviationByNameOrOrgId(String name, String orgId, String amount) {
        List<Abbreviation> data = null;
        if (orgId.equals(""))
            data = abbrRep.findByNameStartsWith(name);
        else
             data = abbrRep.findByNameStartsWithAndOrganisations_id(name, orgId);

        TrimListService<Abbreviation> service = new TrimListService<>();
        data = service.trimListByAmount(data, amount);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find name: " + name);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    /**
     * @param orgId organisation id to filter with
     * @param amount the maximum amount of abbreviations to return
     * @return an HTTPResponse containing a list of abbreviations
     */
    public HTTPResponse getAbbreviationByOrgId(String orgId, String amount) {
        List<Abbreviation> data = abbrRep.findByOrganisations_id(orgId);
        TrimListService<Abbreviation> service = new TrimListService<>();
        data = service.trimListByAmount(data, amount);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find organisation id: " + orgId);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    /**
     * @param reported the value of reported to filter with
     * @param amount the maximum amount of abbreviations to return
     * @return an HTTPResponse containing a list of abbreviations
     */
    public HTTPResponse getAbbreviationByReported(boolean reported, String amount) {
        List<Abbreviation> data = abbrRep.findByIsUnderReview(reported);
        TrimListService<Abbreviation> service = new TrimListService<>();
        data = service.trimListByAmount(data, amount);

        if (data.isEmpty())
            return HTTPResponse.<List<Abbreviation>>returnFailure("could not find reported abbreviations: " + reported);

        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

    /** changes an abbreviation
     * @param abbrs an array with the Abbreviation to change at position 0, and the Abbreviation to change to at position 1
     * @return an HTTPResponse containing a the given abbreviations
     */
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


    /** removes abbreviations from the database
     * @param abbrs the abbreviations to remove
     * @return an HTTPResponse containing the given abbreviations
     */
    public HTTPResponse deleteAbbreviations(Abbreviation[] abbrs){
        for (Abbreviation abbr: abbrs){
            abbrRep.deleteById(abbr.getId());
        }
        return HTTPResponse.returnSuccess(abbrs);
    }
}

