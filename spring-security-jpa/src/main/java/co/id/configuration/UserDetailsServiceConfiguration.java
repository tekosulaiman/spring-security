package co.id.configuration;

import co.id.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceConfiguration implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        List<co.id.model.User> users = userRepository.findByUsername(username);

        if (users.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        } else{
            userName = users.get(0).getUsername();
            password = users.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
        }
        return new org.springframework.security.core.userdetails.User(username,password,authorities);

        //Test Manual

        /*List<String> users = new ArrayList<>();
        users.add("ROLE_USER");

        List<GrantedAuthority> authorities = Arrays.stream(users.get(0).split(""))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new User("admin","admin", authorities);*/
    }
}
