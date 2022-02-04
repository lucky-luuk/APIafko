package AfkoAPI.jwt;

import AfkoAPI.DAO.AccountDao;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    /** username means email in this context
     * @param email the users email
     * @return details on the user
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountDao.getByEmail(email);
        if (account == null)
            throw new UsernameNotFoundException("User not found with email: " + email);

        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(),
                true, true, true, true,
                getAuthorities(account.getRoles()));
    }

    public boolean doPasswordsMatch(String password, String encryptedPassword) {
        return bcryptEncoder.matches(password, encryptedPassword);
    }

    public String getHashedPassword(String password) {
        return bcryptEncoder.encode(password);
    }

    public void saveAccount(Account account) {
        accountDao.addAccount(account);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        for (Role r : roles) {
            privileges.add(r.getName());
        }
        privileges.add("PRIVILEGE_READ");
        privileges.add("PRIVILEGE_WRITE");
        return privileges;
    }
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
