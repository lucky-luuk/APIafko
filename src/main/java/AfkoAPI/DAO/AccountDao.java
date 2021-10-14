package AfkoAPI.DAO;

import AfkoAPI.Controller.AbbreviationController;
import AfkoAPI.Controller.AccountController;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Account;
import AfkoAPI.Repository.AccountRepository;
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


@Component
public class AccountDao {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountRepository accountRepository;


    public Account save(Account account) {
        return accountRepository.save(account);
    }
    public Account findByEmail(String email) { return accountRepository.findByemail(email); }

    public HTTPResponse registerAccount(String firstName, String lastName, String email, String password){
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

