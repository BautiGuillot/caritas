package com.caritas.caritas.config;

import com.caritas.caritas.handlers.CustomLoginFailureHandler;
import com.caritas.caritas.handlers.CustomLoginSuccessHandler;
import com.caritas.caritas.service.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //El constructor debe recibir, por inyección de dependencia, una instancia de UserDetailsService


    private AdminServiceImp userDetailsService;
    @Autowired
    public SecurityConfig(AdminServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Autowired
    private CustomLoginFailureHandler loginFailureHandler;

    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/webjars/**", "/resources/**", "/css/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/adminHome/manageUsers/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/adminHome/**").authenticated()
                        .requestMatchers("/**").permitAll()
                )

                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .failureHandler(loginFailureHandler)
                        .successHandler(loginSuccessHandler)
                        .defaultSuccessUrl("/adminHome")
                        .failureUrl("/loginError")
                        .permitAll()
                )

                .logout((logout) -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/login")
                        .permitAll());
        return http.build();
    }

        @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
