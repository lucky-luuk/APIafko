package AfkoAPI.Controller;

import AfkoAPI.DAO.AbbreviationDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.OrganisationRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AfkoAPI.RequestObjects.AbbreviationRequestObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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


    @PostMapping("/abbreviation")
    public HTTPResponse addAbbreviation(@RequestBody AbbreviationRequestObject[] abbrs) {
        return dao.addAbbreviations(abbrs);
    }

    @GetMapping("/abbreviation")
    public HTTPResponse getAbbreviation(@RequestParam(name="id", defaultValue="") String id,
                                        @RequestParam(name="under_review", defaultValue = "") String reported,
                                        @RequestParam(name="name", defaultValue="") String name,
                                        @RequestParam(name="org_id", defaultValue="") String orgId) {

        if (!id.equals("")) return dao.getAbbreviationByID(id);
        else if (!name.equals("")) return dao.getAbbreviationByName(name);
        else if (!orgId.equals("")) return dao.getAbbreviationByOrgId(orgId);
        else if (!reported.equals("")) return dao.getAbbreviationByReported(Boolean.parseBoolean(reported));
        return HTTPResponse.returnFailure("all fields are empty");


    }

}



