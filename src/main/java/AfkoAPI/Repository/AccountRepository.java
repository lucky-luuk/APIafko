package AfkoAPI.Repository;

import AfkoAPI.Model.Account;
import AfkoAPI.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByemail(String email);
}
