package Questiondb.demo.Config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // ✅ PUBLIC
                        .requestMatchers("/questions/random/**", "/questions/number/**").permitAll()

                        // 🔐 ADMIN
                        .requestMatchers("/questions/**").authenticated()



                        // 🔥 IMPORTANT
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});

        return http.build();
    }
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//
//                        // ✅ PUBLIC (students)
//                        .requestMatchers("/questions/random").permitAll()
//
//                        // 🔐 ADMIN (CRUD)
//                        .requestMatchers("/questions/**").authenticated()
//                )
//                .httpBasic(httpBasic -> {});
//
//        return http.build();
//    }
}