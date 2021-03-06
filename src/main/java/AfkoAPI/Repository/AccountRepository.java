package AfkoAPI.Repository;

import AfkoAPI.Model.Account;
import AfkoAPI.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);

    List<Account> findByRoles_name(String name);
}
