package com.codingSocial.codingSocial.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
        // private final UserAuthenticationProvider userAuthenticationProvider;

        private static final RequestMatcher ALL_URLS = new OrRequestMatcher(new AntPathRequestMatcher("/**"));

        @Autowired
        private ConfigurableBeanFactory beanFactory;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf(csrf -> csrf.disable())
                                .sessionManagement(
                                                sessionManagement -> sessionManagement
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(
                                                authorizeRequests -> authorizeRequests.requestMatchers(ALL_URLS)
                                                                .permitAll().anyRequest().authenticated())
                .rememberMe(customizer -> customizer.alwaysRemember(true).key("demo"));

                                                                // var rememberMeServices = http.getSharedObject(RememberMeServices.class);
                // var rememberMeServices = RememberMeServices.class;
                // beanFactory.registerSingleton("rememberMeServices", rememberMeServices);

                return http.build();
        }

        @Bean
        RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
                String myKey = "1234567890";
                RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
                TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices(myKey, userDetailsService,
                                encodingAlgorithm);
                rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
                return rememberMe;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
