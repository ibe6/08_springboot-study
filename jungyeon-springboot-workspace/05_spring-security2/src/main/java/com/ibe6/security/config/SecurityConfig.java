package com.ibe6.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // 비밀번호 암호화 관련 빈 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // URL 접근 제어 (인가 설정)
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/", "/login", "signup", "/images/**").permitAll()
                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
                    .requestMatchers("/user/**").hasAuthority("USER")
                    .anyRequest().authenticated();
        });

        // 로그인 처리 설정 (인증 설정) => 기존의 매커니즘 설정 (기존 로그인폼 방식)
//        http.formLogin(Customizer.withDefaults());


        // 로그인 처리 설정 (인증 설정) => 사용자 정의 로그인폼 설정
        http.formLogin(from -> {
            from.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("userId")
                    .passwordParameter("userPwd")
//                    .defaultSuccessUrl("/", true)
                    .successHandler(((request, response, authentication) -> {
                        // 권한별 각기 다른 url로 리다이렉트
                        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
                            response.sendRedirect("/admin/main");
                        }else{
                            response.sendRedirect("/user/main");

                        }
                    }))
                    .failureUrl("/login?error");
        });

        // 로그아웃 처리 설정 (인증 설정)
        http.logout(logout -> {
            logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
        });
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}

