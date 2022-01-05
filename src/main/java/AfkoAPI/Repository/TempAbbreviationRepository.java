package AfkoAPI.Repository;

import AfkoAPI.Model.TempAbbreviation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempAbbreviationRepository extends JpaRepository<TempAbbreviation, String> {
}
