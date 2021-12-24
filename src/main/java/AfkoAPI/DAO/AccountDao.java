package AfkoAPI.DAO;

import AfkoAPI.Controller.AbbreviationController;
import AfkoAPI.Controller.AccountController;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Role;
import AfkoAPI.Repository.AccountRepository;
import AfkoAPI.Repository.RoleRepo;
import AfkoAPI.jwt.JwtRequest;
import AfkoAPI.jwt.JwtResponse;
import AfkoAPI.jwt.JwtTokenUtil;
import AfkoAPI.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class AccountDao {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AccountRepository accountRepository;
    private RoleRepo roleRepo;


    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByemail(email).get();
    }

    public HTTPResponse<String> getIdBelongingToEmail(String email) {
        Optional<Account> account = accountRepository.findByemail(email);
        if (account.isEmpty())
            return HTTPResponse.<String>returnFailure("could not find account with that email");
        return HTTPResponse.<String>returnSuccess(account.get().getId());
    }

    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    public void addRoleToUser(String email, String roleName) {
        Account user = accountRepository.findByemail(email);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    /** register a new accoutn with the following information
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email of the account, used to log in
     * @param password the encrypted password used by the account
     * @return an HTTPResponse containing the created account
     */
    public HTTPResponse registerAccount(String firstName, String lastName, String email, String password) {

        if (firstName.equals("") || lastName.equals("") || email.equals("") || password.equals(""))
            return HTTPResponse.<Account>returnFailure("one ore more required parameters were empty");
        else if (findByEmail(email) != null)
            return HTTPResponse.<Account>returnFailure("that email already exists: " + email);

        Account a = new Account(firstName, lastName, email, password);
        userDetailsService.saveAccount(a);
        return HTTPResponse.<Account>returnSuccess(a);
    }

    /** authenticates an account
     * @param authenticationRequest the data to authenticate with
     * @return failure or success
     */
    public HTTPResponse authenticate(JwtRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            return HTTPResponse.<JwtResponse>returnUserDisabled("user: " + authenticationRequest.getUsername() + " is disabled");

        } catch (BadCredentialsException e) {
            return HTTPResponse.<JwtResponse>returnInvalidCredentials("");

        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return HTTPResponse.<JwtResponse>returnSuccess(new JwtResponse(token));
    }
}

