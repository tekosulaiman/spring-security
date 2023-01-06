package co.id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Main {
    public static void main(String[] args){
        /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("admin");
        System.out.println(result);*/

        SpringApplication.run(Main.class, args);
    }
}