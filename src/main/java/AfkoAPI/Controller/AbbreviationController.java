package AfkoAPI.Controller;

import AfkoAPI.DAO.AbbreviationDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.OrganisationRepository;
import AfkoAPI.RequestObjects.AbbreviationRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AbbreviationController {
    @Autowired
    AbbreviationDao dao;

    @GetMapping("/")
    public String index() {
        return "nothing to see here for now";
    }

    @PostMapping("/dummy_abbreviation")
    public HTTPResponse addDummyAbbreviation() {
        return dao.addDummyAbbreviation();
    }

    // todo just temporary to test, replace with post later
    //{
    //    "name": "afko",
    //    "description": "een description",
    //    "organisation_id": "7e6f0d78-2d20-47dc-a3bf-db273d3e4a51",
    //    "account_id": "4107694e-eca9-4269-946b-4973460536b1"
    //}
    @PostMapping("/abbreviation")
    public HTTPResponse addAbbreviation(@RequestBody AbbreviationRequestObject abbr) {
        return dao.addAbbreviation(abbr.getName(), abbr.getDescription(), abbr.getOrganisation_id(), abbr.getAccount_id());
    }

    @GetMapping("/abbreviation")
    public HTTPResponse getAbbreviation(@RequestParam(name="id", defaultValue="") String id,
                                        @RequestParam(name="name", defaultValue="") String name,
                                        @RequestParam(name="org_id", defaultValue="") String orgId) {
        // todo remove this if else chain somehow
        if (!id.equals("")) return dao.getAbbreviationByID(id);
        else if (!name.equals("")) return dao.getAbbreviationByName(name);
        else if (!orgId.equals("")) return dao.getAbbreviationByOrgId(orgId);
        return null;
    }

}



