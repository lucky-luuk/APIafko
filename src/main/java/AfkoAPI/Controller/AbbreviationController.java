package AfkoAPI.Controller;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.AbbreviationOrganisation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationOrganisationRepository;
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
    AbbreviationRepository abbrRep;
    @Autowired
    AbbreviationOrganisationRepository abbrOrgRep;
    @Autowired
    OrganisationRepository orgRep;

    @GetMapping("/")
    public String index() {
        return "nothing to see here for now";
    }

    // todo just temporary to test, replace with post later
    @GetMapping("/add")
    public HTTPResponse addAbbreviation() {
        Organisation org = orgRep.findByName("org 1").get(0);
        Abbreviation abbr = new Abbreviation("etc", "et cetera", org, null);
        abbrRep.save(abbr);
        // DONT FORGET TO ALSO ADD TO ABBRORGREP!!!
        abbrOrgRep.save(new AbbreviationOrganisation(abbr, org));
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
        List<AbbreviationOrganisation> data = abbrOrgRep.findByOrganisation_id(orgId);

        if (data.isEmpty()) return HTTPResponse.<List<Abbreviation>>returnFailure("could not find organisation id: " + orgId);

        List<Abbreviation> abbrs = new ArrayList<>();
        for (AbbreviationOrganisation d : data) abbrs.add(d.getAbbreviation());

        return HTTPResponse.<List<Abbreviation>>returnSuccess(abbrs);
    }
}



