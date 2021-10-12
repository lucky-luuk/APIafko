package AfkoAPI.Repository;

import AfkoAPI.Model.Account;
import AfkoAPI.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByemail(String email);
}
