package xyz.glabaystudios.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import xyz.glabaystudios.services.CustomUserDetailsService;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-25
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                    // Pages
                    .requestMatchers(
                        "/",
                        "/home",
                        "/index",
                        "/osby",
                        "/runiverse",
                        "/irpg",
                        "/penguins",
                        "/misc",
                        "/register",
                        "/error").permitAll()
                    // APIs
                    .requestMatchers("/api/v1/registration/**").permitAll()
                    // Resources
                    .requestMatchers("/css/**", "/images/**", "/webjars/**").permitAll()
                    // Administration
                    .requestMatchers("/acp/**").hasAnyRole("DEVELOPER", "OWNER")
                    // User-related
                    .requestMatchers("/profile").hasRole("USER")
                    // Anything else
                    .anyRequest().authenticated());
        // set up Spring Default Login page
        //TODO: Make a custom one for Glabay-Studios
        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
            .logout((logout) -> logout.addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES))));

        http.headers(configurer -> configurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(getUserDetailsService());
            authProvider.setPasswordEncoder(getBCryptPasswordEncoder());
        return authProvider;
    }
}
