package AfkoAPI;

import AfkoAPI.jwt.JwtUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtUserDetailsServiceTest {
    @Autowired
    JwtUserDetailsService userDetailsService;

    @Test
    public void passwordSaltAndHashShouldWork() throws Exception {
        String password = "password123";
        String hashedPassword = userDetailsService.getHashedPassword(password);
        boolean match = userDetailsService.doPasswordsMatch(password, hashedPassword);
        assertThat(match).isTrue();
    }
}
