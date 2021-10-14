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
    private AccountDao accountDao;

    /**
     * create a jwt token and authenticates using a given username and password, does not require a token
     *
     * @param authenticationRequest json:  {"username": "", "password": ""}
     * @return success or failure
     */
    @GetMapping("/authenticate")
    public HTTPResponse createAuthToken(@RequestBody JwtRequest authenticationRequest) {
        return accountDao.authenticate(authenticationRequest);
    }

    /**
     * creates a new account
     */
    // todo should this use params or a body?
    @PostMapping("/register")
    public HTTPResponse registerAccount(@RequestParam(name = "first_name") String firstName,
                                        @RequestParam(name = "last_name") String lastName,
                                        @RequestParam(name = "email") String email,
                                        @RequestParam(name = "password") String password) {


        return accountDao.registerAccount(firstName, lastName, email, password);
    }




}

