package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@ComponentScan("service")
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final IReviewDAO reviewDAO;

    private final ICommentDAO commentDAO;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService,
                             AuthenticationSuccessHandler authenticationSuccessHandler,
                             AuthenticationFailureHandler authenticationFailureHandler,
                             IReviewDAO reviewDAO,
                             ICommentDAO commentDAO
    ) {
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.reviewDAO = reviewDAO;
        this.commentDAO = commentDAO;
    }

    /**
     *
     * Initialize an instance of AuthenticationManager
     * @param http is an instance of http security
     * @return an instance of AAuthenticationManager based on HttpSecurity passed as input
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        return authenticationManagerBuilder.build();
    }

    /**
     * Configures a security filter chain for the application.
     *
     * @param http the HttpSecurity instance to use for configuring the security filter chain
     * @return a fully configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                )
//                .addFilterAfter(new MultiReadFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new AdminOrOwnerFilter(reviewDAO, commentDAO), MultiReadFilter.class)
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .and()
                .rememberMe()
                .alwaysRemember(true)
                .tokenValiditySeconds(7 * 24 * 60 * 60)
                .key("AbcdefghiJklmNoPqRstUvXyz");
        return http.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * Initializes the configuration of the webapp
     * @return an instance of CorsConfigurationSource with default values
     */
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /**
     *
     * Initializes the Authentication that is needed for the use case
     * @return an instance of AuthenticationProvider with the corresponding UserDetails and password encrypted
     *
     */
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }
}


