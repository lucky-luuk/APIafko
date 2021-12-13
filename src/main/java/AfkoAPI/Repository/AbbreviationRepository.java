package AfkoAPI.Repository;

import AfkoAPI.Model.Abbreviation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface AbbreviationRepository extends JpaRepository<Abbreviation, String> {
    // https://spring.io/guides/gs/accessing-data-jpa/
    List<Abbreviation> findByName(String name);
    // organisation_id = organisation.id https://stackoverflow.com/questions/44566760/spring-boot-using-foreign-key-in-crudrepository
    List<Abbreviation> findByOrganisations_id(String id);

    List<Abbreviation> findByNameStartsWithIgnoreCase(String name);

    List<Abbreviation> findByIsUnderReview(boolean isUnderReview);

    List<Abbreviation> findByNameStartsWithIgnoreCaseAndOrganisations_id(String name, String id);

}
