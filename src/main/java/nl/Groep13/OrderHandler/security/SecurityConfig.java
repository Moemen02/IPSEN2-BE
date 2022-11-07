package nl.Groep13.OrderHandler.security;

import nl.Groep13.OrderHandler.DAO.UserDAO;
import nl.Groep13.OrderHandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private JWTFilter filter;
    @Autowired private UserService userService;

    /**
     * configure endpoints security, set role by witch a user can access an end point
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/auth/register").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasAnyRole("MEDEWERKER", "ADMIN")
                .antMatchers("/api/auth/user").hasAnyRole("MEDEWERKER", "ADMIN")
                .antMatchers("/api/article/**").hasAnyRole("MEDEWERKER", "ADMIN")
                .antMatchers("/api/customer/**").hasAnyRole("MEDEWERKER", "ADMIN")
                .antMatchers("/api/location/**").hasAnyRole("MEDEWERKER", "ADMIN")
                .antMatchers("/api/orders/**").hasAnyRole("MEDEWERKER", "ADMIN")
                .and()
                .userDetailsService(userService)
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }
}