package co.id.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/accounts","/api/v1/balances","/api/v1/loans","/api/v1/cards").hasRole("ADMIN")
                .requestMatchers("/api/v1/notices","/api/v1/contacts").hasAnyRole("ADMIN","USER")
                .and().formLogin()
                .and().httpBasic();

        return httpSecurity.build();

        /**
         *  Configuration to deny all the requests
         */
        /*http.authorizeHttpRequests().anyRequest().denyAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();*/

        /**
         *  Configuration to permit all the requests
         */
        /*http.authorizeHttpRequests().anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // set up the list of supported encoders and their prefixes
        PasswordEncoder defaultEncoder = new StandardPasswordEncoder();
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder(2,1,1,1,1));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("noop", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);

        return passwordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        //Approach 1 where we use withDefaultPasswordEncoder() method while creating the user details
        /*UserDetails admin = User
                .withUsername("admin")
                .password("{bcrypt}$2a$16$K2fe4kL9YOaTX3vBEJppAufz5tPkA.14eoQLRGjlViXQQ2.rQJTnS")
                .roles("ADMIN")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("{bcrypt}$2a$16$APE7zremkSgzvkZks4Bb2eCXo5bJmoEyUSMNbG1klefhDgsZwKbo.")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);*/

        //Approach 2 where we use NoOpPasswordEncoder Bean while creating the user details
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("user")
                .password("{noop}user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

}