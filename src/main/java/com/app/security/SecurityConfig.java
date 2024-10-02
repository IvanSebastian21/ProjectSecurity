package com.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /* CONFIG WITH ADVANCE*/
/*
       RequestMatcher csrfExcludedMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher("/public/**"),
                new AntPathRequestMatcher("/api/**"),
                new AntPathRequestMatcher("/oauth/**")
        );
*/

        /* BASIC CONFIG */
/*
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
*/

        /* MID CONFIG */
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers(HttpMethod.GET,"/qr/generate").permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.GET,"/auth/public").permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.GET,"/auth/user").hasAuthority("CREATE");
                    authorizeRequests.requestMatchers(HttpMethod.GET,"/auth/admin").hasRole("ADMIN");
                    authorizeRequests.anyRequest().denyAll();
                });

        /* ADVANCE CONFIG*/
/*
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(csrfExcludedMatcher)
                )
                .httpBasic(Customizer.withDefaults())
//                .httpBasic(withDefaults())
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
//                            .requestMatchers(HttpMethod.GET,"auth/user/**").hasAuthority("READ")
                            .requestMatchers(HttpMethod.GET,"auth/admin/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET,"auth/manager/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.GET,"auth/public/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"auth/private/**").authenticated()
                            .anyRequest().denyAll();
                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(true)
                );
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Configuración para permitir iframes desde la misma origen
                        .contentSecurityPolicy(csp -> csp.policyDirectives("script-src 'self'")) // Política de seguridad de contenido
                )
//                .formLogin(withDefaults());
                .formLogin(form -> form
                        .defaultSuccessUrl("/public", true)

                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/public")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
*/
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfig) throws Exception {
        return authenticationConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}