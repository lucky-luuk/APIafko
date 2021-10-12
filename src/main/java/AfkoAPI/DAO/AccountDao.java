package AfkoAPI.DAO;

import AfkoAPI.Model.Account;
import AfkoAPI.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {
    @Autowired
    AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }
    public Account findByEmail(String email) { return accountRepository.findByemail(email); }
}
