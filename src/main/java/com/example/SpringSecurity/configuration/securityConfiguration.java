package com.example.SpringSecurity.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class securityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        1.CSRF Disable
//        -Same site strict
//        -Session - Stateless
//        2.Functional Interface and Lambda Expression


//      To disable CSRF
        return http.csrf(customizer -> customizer.disable())

//      To restrict session in cookie -> changes were made in application.properties

//      Without formLogin user can't authenticate the below, so we have given login form to enter details
            .authorizeHttpRequests(request -> request
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                    .requestMatchers("/public/**").permitAll()
                    .anyRequest().authenticated())
//        http.formLogin(Customizer.withDefaults()); //This form can be rendered only through browser
//        We comment the above as using creating new session Id for every session, we will go to the login page.
//      In-order to render through postman
            .httpBasic(Customizer.withDefaults())

//      To make it as stateless (i.e) New session Id will be created for every request
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

/*    @Bean
    public UserDetailsService userDetailsService(){

        List<UserDetails> users = new ArrayList<>();

        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("Priya")
                .password("2468")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("Benhur")
                .password("2469")
                .roles("USER")
                .build();

        UserDetails user3 = User
                .withDefaultPasswordEncoder()
                .username("BenPriya")
                .password("1357")
                .roles("USER")
                .build();

//        return new InMemoryUserDetailsManager(user1, user2, user3); //Varargs

        users.add(user1);
        users.add(user2);
        users.add(user3);
        return new InMemoryUserDetailsManager(users);

    }*/

}
