package AfkoAPI.Controller;

import AfkoAPI.DAO.OrganisationDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
// localhost:8080/add_org
@RestController
public class OrganisationController {

    @Autowired
    OrganisationRepository orgRep;

    @Autowired
    OrganisationDao dau;

    // todo just a temp method to add an organisation, should be replaced with a post later
    @GetMapping("/add_org")
    public HTTPResponse addAbbreviation(@RequestParam(name="name", defaultValue="") String name, String id) {
        Organisation org = new Organisation(name, id);
        orgRep.save(org);
        return HTTPResponse.<Organisation>returnSuccess(org);
    }

    // todo literally the same code as in AbbreviationController
    @GetMapping("/organisation")
    public HTTPResponse getOrganisation(@RequestParam(name="id", defaultValue="") String id,
                                        @RequestParam(name="name", defaultValue="") String name,
                                        @RequestParam(name="all", defaultValue="") String all){
        if (!id.equals("")) return getOrganisationByID(id);
        else if (!name.equals("")) return getOrganisationByName(name);
        else if (!all.equals("")) return getOrganisationAll();
        return null;
    }

    private HTTPResponse getOrganisationAll() {
        return dau.getAllOrganisationData();
    }
    private HTTPResponse getOrganisationByID(String id) {
        Optional<Organisation> data = orgRep.findById(id);
        if (data.isEmpty())
            return HTTPResponse.<Organisation>returnFailure("could not find id");
        return HTTPResponse.<Organisation>returnSuccess(data.get());
    }
    private HTTPResponse getOrganisationByName(String name) {
        List<Organisation> data = orgRep.findByName(name);
        if (data.isEmpty()) return HTTPResponse.<List<Organisation>>returnFailure("could not find name");
        return HTTPResponse.<List<Organisation>>returnSuccess(data);
    }
}








