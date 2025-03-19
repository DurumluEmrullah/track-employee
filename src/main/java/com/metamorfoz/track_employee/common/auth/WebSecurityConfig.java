package com.metamorfoz.track_employee.common.auth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/api/v1/users/register",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/api/v1/users/create-super-manager",
                                "/api/v1/users/login").permitAll()
                        .requestMatchers("/api/v1/users/create-manager").hasAnyAuthority("SUPER_MANAGER")
                        .requestMatchers("/api/v1/users/create-employee").hasAnyAuthority("MANAGER")
                        .requestMatchers("/api/v1/manager/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/api/v1/employee/**").hasAnyAuthority("EMPLOYEE")
                        .requestMatchers("/api/v1/manager/**").hasAnyAuthority("MANAGER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/login"))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }


}

