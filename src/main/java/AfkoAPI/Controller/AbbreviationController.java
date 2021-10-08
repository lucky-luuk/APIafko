package AfkoAPI.Controller;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AbbreviationController {

    @Autowired
    AbbreviationRepository abbrRep;
    @Autowired
    OrganisationRepository orgRep;

    @GetMapping("/")
    public String index() {
        return "nothing to see here for now";
    }

    // todo just temporary to test, replace with post later
    @GetMapping("/add")
    public HTTPResponse addAbbreviation() {
        List<Organisation> orgs = orgRep.findByName("org 1");
        Abbreviation abbr = new Abbreviation("etc", "et cetera", orgs.get(0));
        abbrRep.save(abbr);
        return HTTPResponse.<Abbreviation>returnSuccess(abbr);
    }
    @GetMapping("/abbreviation")
    public HTTPResponse getAbbreviation(@RequestParam(name="id", defaultValue="") String id,
                                        @RequestParam(name="name", defaultValue="") String name,
                                        @RequestParam(name="org_id", defaultValue="") String orgId) {
        // todo remove this if else chain somehow
        if (!id.equals("")) return getAbbreviationByID(id);
        else if (!name.equals("")) return getAbbreviationByName(name);
        else if (!orgId.equals("")) return getAbbreviationByOrgId(orgId);
        return null;
    }
    private HTTPResponse getAbbreviationByID(String id) {
        Optional<Abbreviation> data = abbrRep.findById(id);
        if (data.isEmpty())
            return HTTPResponse.<Abbreviation>returnFailure("could not find id: " + id);
        return HTTPResponse.<Abbreviation>returnSuccess(data.get());
    }
    private HTTPResponse getAbbreviationByName(String name) {
        List<Abbreviation> data = abbrRep.findByName(name);
        if (data.isEmpty()) return HTTPResponse.<List<Abbreviation>>returnFailure("could not find name: " + name);
        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }
    private HTTPResponse getAbbreviationByOrgId(String orgId) {
        List<Abbreviation> data = abbrRep.findByOrganisation_id(orgId);
        if (data.isEmpty()) return HTTPResponse.<List<Abbreviation>>returnFailure("could not find organisation id: " + orgId);
        return HTTPResponse.<List<Abbreviation>>returnSuccess(data);
    }

//    @GetMapping("/persoon/{fname}")
//    @ResponseBody
//    public ResponseEntity<Abbreviation> getItem(@PathVariable String fname){
//        Optional<Abbreviation> persoon = abbrRep.findById(fname);
//        return new ResponseEntity<Abbreviation>(persoon.get(), HttpStatus.OK);
}



