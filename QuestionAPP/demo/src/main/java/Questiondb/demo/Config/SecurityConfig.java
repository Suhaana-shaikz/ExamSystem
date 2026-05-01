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

                        // ✅ PUBLIC (students)
                        .requestMatchers(
                                "/student/submit",
                                "/student/questions",
                                "/student/check/**",
                                "/student/test"   // ✅ add this also
                        ).permitAll()

                        // 🔐 ADMIN
                        .requestMatchers(
                                "/student/dashboard",
                                "/student/download",
                                "/student/all",
                                "/student/answers"
                        ).authenticated()

                        // 🔥 VERY IMPORTANT
                        .anyRequest().authenticated()
                )

                // ✅ ENABLE BASIC AUTH (FIX)
                .httpBasic(httpBasic -> {});


        return http.build();
    }
}