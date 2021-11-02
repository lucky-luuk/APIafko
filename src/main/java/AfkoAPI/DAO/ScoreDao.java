package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Model.Player;
import AfkoAPI.Repository.OrganisationRepository;
import  AfkoAPI.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScoreDao {
    @Autowired
    ScoreRepository scoreRep;
    @Autowired
    OrganisationRepository orgRep;

    public ScoreDao(){}

    public HTTPResponse getScoreList(){
        List<Player> data = scoreRep.findTop5ByOrderByScoreDesc();

        if (data.isEmpty())
            return HTTPResponse.returnFailure("could not find any score");
        return HTTPResponse.returnSuccess(data);
    }

    public HTTPResponse addScore(String name,Integer score, String orgId) {
        Optional<Organisation> org = orgRep.findById(orgId);

        if (org.isEmpty()) return HTTPResponse.<Player>returnFailure("organisation with id: " + orgId + " does not exist");

        ArrayList<Organisation> orgs = new ArrayList<>();
        orgs.add(org.get());
        Player player = new Player(name, score, orgs);
        scoreRep.save(player);
        return HTTPResponse.<Player>returnSuccess(player);
    }
}
