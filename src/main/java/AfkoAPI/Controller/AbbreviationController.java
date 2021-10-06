package AfkoAPI.Controller;

import AfkoAPI.Repository.AbbreviationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import java.util.List;
import java.util.Optional;

@RestController
public class AbbreviationController {

    @Autowired
    AbbreviationRepository abbrRep;

    @GetMapping("/")
    public String index() {
        return "This is a test";
    }

//    @GetMapping("/persoon/{fname}")
//    @ResponseBody
//    public ResponseEntity<Abbreviation> getItem(@PathVariable String fname){
//        Optional<Abbreviation> persoon = abbrRep.findById(fname);
//        return new ResponseEntity<Abbreviation>(persoon.get(), HttpStatus.OK);
}



