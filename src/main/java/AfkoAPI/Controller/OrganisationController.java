package AfkoAPI.Controller;

import AfkoAPI.DAO.OrganisationDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.OrganisationRepository;
import AfkoAPI.RequestObjects.OrganisationRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// localhost:8080/add_org
@RestController
public class OrganisationController {

    @Autowired
    OrganisationRepository orgRep;

    @Autowired
    OrganisationDao dao;

    @PostMapping("/organisation")
    public HTTPResponse addOrganisation(@RequestBody OrganisationRequestObject[] o) {
        return dao.addOrganisations(o);
    }
    @PostMapping("/organisation_bulk")
    public HTTPResponse addOrganisationBulk(@RequestBody OrganisationRequestObject[] o) {
        return dao.addOrganisationsBulk(o);
    }

    // todo literally the same code as in AbbreviationController
    @GetMapping("/organisation")
    public HTTPResponse getOrganisation(@RequestParam(name="id", defaultValue="") String id,
                                        @RequestParam(name="name", defaultValue="") String name) {

        if (!id.equals("")) return dao.getOrganisationByID(id);
        else if (!name.equals("")) return dao.getOrganisationByName(name);
        else return dao.getAllOrganisationData();
    }


}








