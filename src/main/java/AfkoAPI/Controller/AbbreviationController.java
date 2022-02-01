package AfkoAPI.Controller;

import AfkoAPI.DAO.AbbreviationDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;

import AfkoAPI.RequestObjects.AbbreviationRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AbbreviationController {
    @Autowired
    AbbreviationDao dao;

    @GetMapping("/")
    public String index() {
        return "";
    }

    @PostMapping("/abbreviation")
    public HTTPResponse addAbbreviation(@RequestBody AbbreviationRequestObject[] abbrs) {
        return dao.addAbbreviations(abbrs);
    }

    @PutMapping("/abbreviation")
    public HTTPResponse changeAbbreviation(@RequestBody Abbreviation[] abbrs){
        if (abbrs.length == 2) {
            return dao.changeAbbreviationById(abbrs);
        }
        return HTTPResponse.returnFailure("input length is not 2");
    }

    @DeleteMapping("/abbreviation")
    public HTTPResponse deleteAbbreviation(@RequestBody Abbreviation[] abbrs){
        return dao.deleteAbbreviations(abbrs);
    }

    @GetMapping("/abbreviation")
    public HTTPResponse getAbbreviation(@RequestParam(name="id", defaultValue="") String id,
                                        @RequestParam(name="under_review", defaultValue = "") String reported,
                                        @RequestParam(name="name", defaultValue="") String name,
                                        @RequestParam(name="org_id", defaultValue="") String orgId,
                                        @RequestParam(name="max_amount", defaultValue="") String amount) {


        if (!id.equals("")) return dao.getAbbreviationByID(id);
        else if (!name.equals("")) return dao.getAbbreviationByNameOrOrgId(name, orgId, amount);
        else if (!orgId.equals("")) return dao.getAbbreviationByOrgId(orgId, amount);
        else if (!reported.equals("")) return dao.getAbbreviationByReported(Boolean.parseBoolean(reported), amount);
        return HTTPResponse.returnFailure("all fields are empty");
    }

}



