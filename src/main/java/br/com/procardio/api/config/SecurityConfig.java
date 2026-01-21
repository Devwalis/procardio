package br.com.procardio.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    

 @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.csrf(csrf -> csrf.disable())
                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(req -> {
                        req.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
                          req.requestMatchers(HttpMethod.GET, "/api/consultas").hasAnyRole("ADMIN", "PACIENTE", "MEDICO");
                    req.requestMatchers(HttpMethod.GET, "/api/consultas/**").hasAnyRole("ADMIN", "PACIENTE", "MEDICO");
                    req.requestMatchers(HttpMethod.POST, "/api/consultas").hasAnyRole("ADMIN", "PACIENTE");
                    req.requestMatchers(HttpMethod.PUT, "/api/consultas/**").hasAnyRole("ADMIN", "PACIENTE");
                    req.requestMatchers(HttpMethod.DELETE, "/api/consultas/**").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.GET, "/api/consultas/minhas-consultas").hasRole("PACIENTE");
                    req.requestMatchers(HttpMethod.GET, "/api/consultas/minha-agenda").hasRole("MEDICO");
                        req.anyRequest().authenticated();
                    })
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}