package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AccountRepository;
import AfkoAPI.Repository.OrganisationRepository;
import AfkoAPI.RequestObjects.OrganisationRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrganisationDao {

    @Autowired
    OrganisationRepository orgRep;

    public OrganisationDao() {}

    public HTTPResponse getAllOrganisationData() {
        List<Organisation> data = orgRep.findAll();

        if (data.isEmpty()) return HTTPResponse.<List<Organisation>>returnFailure("could not find organisation data");
        return HTTPResponse.<List<Organisation>>returnSuccess(data);
    }

    public HTTPResponse addOrganisationsWithIds(OrganisationRequestObject[] orgs) {
        Organisation[] organisations = new Organisation[orgs.length];
        for (OrganisationRequestObject o : orgs) {
            addOrganisation(o.getName(), o.getId());
        }
        return HTTPResponse.<Organisation[]>returnSuccess(organisations);
    }

    public HTTPResponse addOrganisations(OrganisationRequestObject[] orgs) {
        Organisation[] organisations = new Organisation[orgs.length];
        for (OrganisationRequestObject o : orgs) {
            addOrganisation(o.getName());
        }
        return HTTPResponse.<Organisation[]>returnSuccess(organisations);
    }

    public HTTPResponse addOrganisation(String name, String id) {
        Organisation org = new Organisation(name, id);
        orgRep.save(org);
        return HTTPResponse.<Organisation>returnSuccess(org);
    }

    public HTTPResponse addOrganisation(String name) {
        Organisation org = new Organisation(name);
        orgRep.save(org);
        return HTTPResponse.<Organisation>returnSuccess(org);
    }


    public HTTPResponse getOrganisationByID(String id) {
        Optional<Organisation> data = orgRep.findById(id);
        if (data.isEmpty())
            return HTTPResponse.<Organisation>returnFailure("could not find id");
        return HTTPResponse.<Organisation>returnSuccess(data.get());
    }

    public HTTPResponse getOrganisationByName(String name) {
        List<Organisation> data = orgRep.findByName(name);
        if (data.isEmpty()) return HTTPResponse.<List<Organisation>>returnFailure("could not find name");
        return HTTPResponse.<List<Organisation>>returnSuccess(data);
    }


}
