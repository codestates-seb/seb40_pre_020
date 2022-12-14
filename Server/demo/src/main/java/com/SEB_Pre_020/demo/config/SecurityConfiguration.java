package com.SEB_Pre_020.demo.config;

import com.SEB_Pre_020.demo.config.auth.filter.JwtAuthenticationFilter;
import com.SEB_Pre_020.demo.config.auth.filter.JwtVerificationFilter;
import com.SEB_Pre_020.demo.config.auth.handler.MemberAccessDeniedHandler;
import com.SEB_Pre_020.demo.config.auth.handler.MemberAuthenticationEntryPoint;
import com.SEB_Pre_020.demo.config.auth.handler.MemberAuthenticationFailureHandler;
import com.SEB_Pre_020.demo.config.auth.handler.MemberAuthenticationSuccessHandler;
import com.SEB_Pre_020.demo.config.auth.jwt.JwtTokenizer;
import com.SEB_Pre_020.demo.config.auth.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() //?????? ??????????????? ???????????? request??? ????????? ???????????? ??????
                .and()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().addHeaderWriter
                        (new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"))
                .frameOptions().disable()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll() // CORS ?????? ?????? ??? ????????? ??????
                        // MEMBER ?????? ?????? ???????????? ?????? ????????? ????????????
                        .antMatchers(HttpMethod.GET, "*/tags/**").permitAll()
                        .antMatchers(HttpMethod.POST, "*/tags/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.GET,"*/profiles/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.POST,"*/posts/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.PATCH,"*/posts/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.DELETE,"*/posts/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.POST,"*/answers/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.PATCH,"*/answers/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.DELETE,"*/answers/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.POST,"*/votes/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.POST,"*/comments/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.PATCH,"*/comments/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.DELETE,"*/comments/**").hasRole("MEMBER")
                        .anyRequest().permitAll()
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager
                    = builder.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter
                    = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/v1/auth/login"); // Login url
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    } // JwtAuthenticationFilter??? ???????????? ?????????

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("http://3.39.219.172:8080");
        configuration.addAllowedOrigin("http://seb40pre020.s3-website.ap-northeast-2.amazonaws.com");
        configuration.addAllowedOrigin("http://seb40pre020.s3-website.ap-northeast-2.amazonaws.com:80");
        configuration.setAllowCredentials(true);
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    } // ????????? ????????? ?????? CORS ??????
}
