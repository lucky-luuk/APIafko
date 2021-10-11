package AfkoAPI.Controller;

import AfkoAPI.DAO.AccountDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Account;
import AfkoAPI.jwt.JwtRequest;
import AfkoAPI.jwt.JwtTokenUtil;
import AfkoAPI.jwt.JwtResponse;
import AfkoAPI.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AccountDao accountDao;

    /** create a jwt token and authenticates using a given username and password, does not require a token
     * @param authenticationRequest json:  {"username": "", "password": ""}
     * @return success or failure
     */
    @GetMapping("/authenticate")
    public HTTPResponse createAuthToken(@RequestBody JwtRequest authenticationRequest){
        return authenticate(authenticationRequest);
    }

    /**
     * creates a new account
     */
    // todo should this use params or a body?
    @PostMapping("/register")
    public HTTPResponse registerAccount(@RequestParam(name="first_name") String firstName,
                                        @RequestParam(name="last_name") String lastName,
                                        @RequestParam(name="email") String email,
                                        @RequestParam(name="password") String password) {

        if (firstName.equals("") || lastName.equals("") || email.equals("") || password.equals(""))
            return HTTPResponse.<Account>returnFailure("one ore more required parameters were empty");
        if (accountDao.findByEmail(email) != null)
            return HTTPResponse.<Account>returnFailure("that email already exists: " + email);

        Account a = new Account(firstName, lastName, email, password);
        userDetailsService.saveAccount(a);
        return HTTPResponse.<Account>returnSuccess(a);
    }

    /** authenticates an account
     * @param authenticationRequest the data to authenticate with
     * @return failure or success
     */
    private HTTPResponse authenticate(JwtRequest authenticationRequest) {
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
