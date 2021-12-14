package AfkoAPI.Controller;

import AfkoAPI.DAO.OrganisationDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Repository.OrganisationRepository;
import AfkoAPI.RequestObjects.OrganisationRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// localhost:8080/add_org
@RestController
public class OrganisationController {

    @Autowired
    OrganisationDao dao;

    @PostMapping("/organisation")
    public HTTPResponse addOrganisation(@RequestBody OrganisationRequestObject[] o) {
        return dao.addOrganisationsGenerateId(o);
    }

    /** adds organisations but does not generate ids for them, can be used to add organisations with known ids that should
     * not change
     * @param o the organisations
     * @return HTTPResponse
     */
    @PostMapping("/organisation_with_id")
    public HTTPResponse addOrganisationBulk(@RequestBody OrganisationRequestObject[] o) {
        return dao.addOrganisations(o);
    }

    @GetMapping("/organisation")
    public HTTPResponse getOrganisation(@RequestParam(name="id", defaultValue="") String id,
                                        @RequestParam(name="name", defaultValue="") String name) {

        if (!id.equals("")) return dao.getOrganisationByID(id);
        else if (!name.equals("")) return dao.getOrganisationByName(name);
        else return dao.getAllOrganisationData();
    }


}








