package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.OrganisationRepository;
import AfkoAPI.RequestObjects.OrganisationRequestObject;
import AfkoAPI.services.OrganisationService;
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

    public HTTPResponse addOrganisations(OrganisationRequestObject[] orgs) {
        return OrganisationService.addOrganisations(orgRep, orgs);
    }

    public HTTPResponse addOrganisationsGenerateId(OrganisationRequestObject[] orgs) {
        return OrganisationService.addOrganisationsGenerateId(orgRep, orgs);
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
