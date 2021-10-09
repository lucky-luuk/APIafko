package AfkoAPI.Controller;

import AfkoAPI.DAO.AbbreviationDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.OrganisationRepository;
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

    // todo just temporary to test, replace with post later
    @GetMapping("/add")
    public HTTPResponse addAbbreviation() {
        return dao.addAbbreviation("etc", "et cetera", "69fe9a5e-26fc-46de-96a4-855bde790bfe");
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



