package com.SEB_Pre_020.demo.config;

import com.SEB_Pre_020.demo.config.auth.filter.JwtAuthenticationFilter;
import com.SEB_Pre_020.demo.config.auth.jwt.JwtTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() //동일 출처로부터 들어오는 request만 페이지 렌더링을 허용
                .and()
                .csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("!/h2/**"))
                .and()
                .headers().addHeaderWriter
                        (new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"))
                .frameOptions().disable()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/profiles/**").hasRole("MEMBER")
                        // MEMBER 권한 가진 사용자만 마이페이지 이하 리소스 접근가능
                        .antMatchers(HttpMethod.POST,"/posts/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.PATCH,"/posts/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.DELETE,"/posts/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.POST,"/answers/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.PATCH,"/answers/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.DELETE,"/answers/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.POST,"/votes/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.POST,"/comments/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.PATCH,"/comments/**").hasRole("MEMBER")
                        .antMatchers(HttpMethod.DELETE,"/comments/**").hasRole("MEMBER")
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

            builder.addFilter(jwtAuthenticationFilter);
        }
    } // JwtAuthenticationFilter를 등록하는 메서드

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    } // 프론트 통신을 위한 CORS 설정
}
