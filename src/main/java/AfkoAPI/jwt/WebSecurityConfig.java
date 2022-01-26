package AfkoAPI.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

//TODO Remove tutorial commants

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // define urls that don't need jwt token
    public static final String[] UNSECURED_URLS = {
            "/authenticate"
    };

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new JwtAuthenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // configure cors
        httpSecurity.csrf().disable().cors().configurationSource(this.corsConfigurationSource()).and()
                // don't authenticate these requests
                .authorizeRequests().antMatchers(UNSECURED_URLS).permitAll().and().authorizeRequests()
                // abbreviation controller
                .antMatchers(HttpMethod.POST, "/abbreviation" ).hasAnyAuthority(RoleNames.MOD.getValue(), RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.DELETE, "/abbreviation" ).hasAnyAuthority(RoleNames.MOD.getValue(), RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.PUT, "/abbreviation" ).hasAnyAuthority(RoleNames.MOD.getValue(), RoleNames.ADMIN.getValue())
                // account controller
                .antMatchers(HttpMethod.POST, "/role/save" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.POST, "/role/addtouser" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.POST, "/role/removefromuser" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.GET, "/account" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.PUT, "/account/mod" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.GET, "/account/mod" ).hasAnyAuthority(RoleNames.ADMIN.getValue(), RoleNames.MOD.getValue())
                .antMatchers(HttpMethod.POST, "/account/mod" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.DELETE, "/account/mod" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.PUT, "/account/mod/password" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                // blacklist controller
                .antMatchers(HttpMethod.GET, "/blacklist" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.POST, "/blacklist" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.PUT, "/blacklist" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.DELETE, "/blacklist" ).hasAnyAuthority(RoleNames.ADMIN.getValue())
                // organisation controller
                .antMatchers(HttpMethod.POST, "/organisation" ).hasAnyAuthority(RoleNames.MOD.getValue(), RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.POST, "/organisation_with_id" ).hasAnyAuthority(RoleNames.MOD.getValue(), RoleNames.ADMIN.getValue())
                // ticket controller
                .antMatchers(HttpMethod.PUT, "/abbreviation" ).hasAnyAuthority(RoleNames.MOD.getValue(), RoleNames.ADMIN.getValue())
                .antMatchers(HttpMethod.DELETE, "/abbreviation" ).hasAnyAuthority(RoleNames.MOD.getValue(), RoleNames.ADMIN.getValue())

                .antMatchers(HttpMethod.GET, "/abbreviation","/organisation", "/score" ).permitAll()
                .antMatchers(HttpMethod.POST, "/score", "/ticket" ).permitAll()

                .anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
