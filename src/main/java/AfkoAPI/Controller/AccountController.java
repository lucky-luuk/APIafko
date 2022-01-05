package AfkoAPI.Controller;

import AfkoAPI.DAO.AccountDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Role;
import AfkoAPI.RequestObjects.AccountRequestObject;
import AfkoAPI.jwt.JwtRequest;
import AfkoAPI.jwt.JwtTokenUtil;
import AfkoAPI.jwt.JwtResponse;
import AfkoAPI.jwt.JwtUserDetailsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    @PostMapping("/authenticate")
    public HTTPResponse createAuthToken(@RequestBody JwtRequest authenticationRequest) {
        return accountDao.authenticate(authenticationRequest);
    }

    /**
     * creates a new account
     */
    @PostMapping("/register")
    public HTTPResponse registerAccount(@RequestBody AccountRequestObject o) {
        return accountDao.registerAccount(o.getFirstName(), o.getLastName(), o.getEmail(), o.getPassword());
    }

    @PostMapping("/role/save")
    public HTTPResponse saveRole(@RequestBody Role role) {
        return accountDao.saveRole(role);
    }

    @PostMapping("/role/addtouser")
    public HTTPResponse addRoleRoUser(@RequestBody RoleToUserForm form) {
        return accountDao.addRoleToUser(form.getUsername(), form.getRoleName());
    }

    @GetMapping("/account")
    public HTTPResponse getAccountDetails(@RequestParam(name="id", defaultValue="") String id, @RequestParam(name="email", defaultValue = "") String email) {
        // todo make sure top check if this is the account thats logged in!!!
        if (id.equals(""))
            return accountDao.getIdBelongingToEmail(email);
        return accountDao.getAccountDetails(id);
    }


}


@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}


