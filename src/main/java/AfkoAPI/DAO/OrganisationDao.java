package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AccountRepository;
import AfkoAPI.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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


}
