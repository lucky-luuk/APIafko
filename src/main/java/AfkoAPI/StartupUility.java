package AfkoAPI;

import AfkoAPI.DAO.AbbreviationDao;
import AfkoAPI.DAO.OrganisationDao;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class StartupUility implements CommandLineRunner {

    @Autowired
    private AbbreviationDao AbbrDao;

    @Autowired
    private OrganisationDao OrgDao;

    public void run(String... args) throws Exception {
        addAbbr();
//        addOrg();

        
    }
    public void addOrg() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Organisation>> typeReference = new TypeReference<List<Organisation>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/org.json");
        try{
            List<Organisation> users = mapper.readValue(inputStream,typeReference);
            OrgDao.save(users);
        }catch (IOException e){
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }

    public void addAbbr() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Abbreviation>> typeReference = new TypeReference<List<Abbreviation>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/abbr_drie_4.json");
        try{
            List<Abbreviation> users = mapper.readValue(inputStream,typeReference);
            AbbrDao.save(users);
        }catch (IOException e){
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }

}
