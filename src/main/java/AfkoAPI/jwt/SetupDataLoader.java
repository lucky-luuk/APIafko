package AfkoAPI.jwt;

import AfkoAPI.DAO.AccountDao;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Role;
import AfkoAPI.Repository.AccountRepository;
import AfkoAPI.Repository.RoleRepository;
import AfkoAPI.jwt.RoleNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

// source: https://www.baeldung.com/role-and-privilege-for-spring-security-registration
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean hasAlreadyBeenSetup = false;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountDao accountDao;

    @Autowired
    JwtUserDetailsService userDetailsService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (hasAlreadyBeenSetup)
            return;
        if (accountRepository.findByEmail("admin@admin.com").isPresent())
            return;

        createRoleIfNotFound(RoleNames.ADMIN);
        createRoleIfNotFound(RoleNames.MOD);

        Account a = new Account();
        a.setId(UUID.randomUUID().toString());
        a.setFirstLogin(false);
        a.setEmail("admin@admin.com");
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(RoleNames.ADMIN.getValue()).get());
        a.setRoles(roles);
        // 098f6bcd4621d373cade4e832627b4f6 = test
        a.setPassword(this.userDetailsService.getHashedPassword("098f6bcd4621d373cade4e832627b4f6"));
        accountDao.addAccount(a);

        Account b = new Account();
        roles = new HashSet<>();
        roles.add(roleRepository.findById(RoleNames.MOD.getValue()).get());

        b.setRoles(roles);
        b.setId(UUID.randomUUID().toString());
        b.setFirstLogin(false);
        b.setEmail("mod@mod.com");
        // 098f6bcd4621d373cade4e832627b4f6 = test
        b.setPassword(this.userDetailsService.getHashedPassword("098f6bcd4621d373cade4e832627b4f6"));
        accountDao.addAccount(b);

        hasAlreadyBeenSetup = true;
    }

    @Transactional
    void createRoleIfNotFound(RoleNames name) {
        Optional<Role> role = roleRepository.findById(name.getValue());
        if (role.isEmpty()) {
            Role r = new Role(name.getValue());
            roleRepository.save(r);
        }
    }
}