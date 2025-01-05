package dev.tugba.flight_ticket_platform.auth.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.UserService;
import dev.tugba.flight_ticket_platform.auth.config.components.JwtAuthenticationFilter;
import dev.tugba.flight_ticket_platform.auth.config.constants.Permission;
import dev.tugba.flight_ticket_platform.auth.config.constants.Role;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomLogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    
        http
            .cors(cors -> cors
                .configurationSource(request -> {
                    var corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
                    corsConfig.setAllowedMethods(List.of("GET", "POST"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    var source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", corsConfig);
                    return corsConfig;
                }))
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(t -> t.authenticationEntryPoint(customAuthenticationEntryPoint))
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers("/api/v1/user/deposit").hasAnyAuthority(Permission.VISITOR_UPDATE.getPermission())
                .requestMatchers("/api/v1/user/info").hasAnyAuthority(Permission.VISITOR_READ.getPermission(), Permission.ADMIN_READ.getPermission())
                .requestMatchers("/api/v1/flights/list").hasAnyAuthority(Permission.VISITOR_READ.getPermission(), Permission.ADMIN_READ.getPermission())
                .requestMatchers("/api/v1/flight/sell").hasAuthority(Permission.VISITOR_UPDATE.getPermission())
                .requestMatchers("/api/v1/flight/add").hasAuthority(Permission.ADMIN_CREATE.getPermission())
                .requestMatchers("/api/v1/flight/remove").hasAuthority(Permission.VISITOR_UPDATE.getPermission())
                .requestMatchers("/api/v1/flight/remove").hasRole(Role.VISITOR.name())
                .requestMatchers("/api/v1/user").hasRole(Role.VISITOR.name())
                .requestMatchers("/api/v1/auth/login").permitAll()
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/auth/reset-password").permitAll()
                .requestMatchers("/api/v1/flights/all").permitAll()
                .requestMatchers("/api/v1/flights/search").permitAll()
                .requestMatchers("/validator/validateEmail").permitAll()
                .requestMatchers("/validator/validateTurkishId").permitAll()
                .requestMatchers("/validator/validatePhoneNumber").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(logout -> 
                logout.logoutUrl("/api/v1/auth/logout")
                    .addLogoutHandler(logoutHandler)
                    .logoutSuccessHandler((request, response, authentication) -> {
                        SecurityContextHolder.clearContext();
                    }));

        return http.build();
    }

    @Bean
     public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
    
}
