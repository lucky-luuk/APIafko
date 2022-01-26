package AfkoAPI.jwt;

import AfkoAPI.Model.Account;
import AfkoAPI.Model.Role;
import AfkoAPI.Repository.AccountRepository;
import AfkoAPI.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtAuthenticationManager implements AuthenticationManager {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // convert to strings
        String email = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            throw new BadCredentialsException("");
        }

        if (!userDetailsService.doPasswordsMatch(password, account.get().getPassword())) {
            throw new BadCredentialsException("");
        }

        // check roles
        Set<Role> roles = account.get().getRoles();
        return new UsernamePasswordAuthenticationToken(email, null, roles.stream()
                .map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList()));
    }
}