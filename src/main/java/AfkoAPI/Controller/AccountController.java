package AfkoAPI.Controller;

import AfkoAPI.DAO.AccountDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Role;
import AfkoAPI.RequestObjects.AccountPasswordRequestObject;
import AfkoAPI.RequestObjects.AccountRequestObject;
import AfkoAPI.RequestObjects.AccountReturnObject;
import AfkoAPI.RequestObjects.RoleUserRequestObject;
import AfkoAPI.jwt.JwtRequest;
import AfkoAPI.jwt.JwtTokenUtil;
import AfkoAPI.jwt.JwtUserDetailsService;
import AfkoAPI.jwt.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * create a jwt token and authenticates using a given username and password, does not require a token
     *
     * @param authenticationRequest json:  {"username": "", "password": ""}
     * @return success or failure
     */
    @PostMapping("/authenticate")
    public HTTPResponse<UserResponse> createAuthToken(@RequestBody JwtRequest authenticationRequest) {
        return accountDao.authenticate(authenticationRequest);
    }

    /**
     * creates a new account
     */
    @PostMapping("/register")
    public HTTPResponse<AccountReturnObject> registerAccount(@RequestBody AccountRequestObject o) {
        return accountDao.registerAccount(o.getFirstName(), o.getLastName(), o.getEmail(), o.getPassword());
    }

    @PostMapping("/role/save")
    public HTTPResponse<Role> saveRole(@RequestBody Role role) {
        return accountDao.saveRole(role);
    }

    @PostMapping("/role/addtouser")
    public HTTPResponse<String> addRoleRoUser(@RequestBody RoleUserRequestObject form) {
        return accountDao.addRoleToUser(form.getEmail(), form.getRoleName());
    }

    @PostMapping("/role/removefromuser")
    public HTTPResponse<String> removeRoleFromUser(@RequestBody RoleUserRequestObject form) {
        return accountDao.removeRoleFromUser(form.getEmail(), form.getRoleName());
    }

    @GetMapping("/account")
    public HTTPResponse<AccountReturnObject> getAccountDetails(@RequestParam(name="id", defaultValue="") String id, @RequestParam(name="email", defaultValue = "") String email) {
        // todo make sure top check if this is the account thats logged in!!!
        if (id.equals(""))
            return accountDao.getIdBelongingToEmail(email);
        return accountDao.getAccountDetails(id);
    }

    @PutMapping("/account/mod")
    public HTTPResponse<Account[]> changeAccount(@RequestBody Account[] accounts) {
        if (accounts.length == 2) {
            return accountDao.changeAccount(accounts);
        }
        return HTTPResponse.<AccountReturnObject>returnFailure("input length is not 2");
    }

    @GetMapping("/account/mod")
    public HTTPResponse<List<AccountReturnObject>> getAllMods(){
        return accountDao.getAllMods();
    }

    @PostMapping("/account/mod")
    public HTTPResponse<AccountReturnObject> createMod(@RequestBody AccountRequestObject acc){
        return accountDao.createMod(acc);
    }

    @PutMapping("/account/mod/password")
    public HTTPResponse<Account> changeAccountPassword(@RequestBody AccountPasswordRequestObject accountPasswordRequestObject, @RequestHeader (name = "Authorization", defaultValue = "") String token){
        System.out.println(token);
        String email = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        return accountDao.changeAccountPassword(accountPasswordRequestObject.getNewPassword(), email);
    }
}



