package AfkoAPI.Repository;

import AfkoAPI.Model.Abbreviation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbbreviationRepository extends JpaRepository<Abbreviation, String> {
    // this is some magic https://spring.io/guides/gs/accessing-data-jpa/
    List<Abbreviation> findByName(String name);
    // to get id of organisation use organisation_id = organisation.id https://stackoverflow.com/questions/44566760/spring-boot-using-foreign-key-in-crudrepository
    List<Abbreviation> findByOrganisation_id(String org);
}
