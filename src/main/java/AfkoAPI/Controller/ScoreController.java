package AfkoAPI.Controller;

import AfkoAPI.DAO.ScoreDao;
import AfkoAPI.HTTPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import AfkoAPI.RequestObjects.ScoreRequestObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ScoreController {

    @Autowired
    ScoreDao dao;

    @GetMapping("/score")
    public HTTPResponse getScoreFromScoreBoard() {return dao.getScoreList();}

    @PostMapping("/score")
    public HTTPResponse addScore(@RequestBody ScoreRequestObject[] scores) {
        ScoreRequestObject score = scores[0];
        return dao.addScore(score.getName(),score.getScore(),score.getOrganisation_id());}
}
