package AfkoAPI.Repository;

import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganisationRepository extends JpaRepository<Organisation, String> {
    List<Organisation> findByName(String name);

}
