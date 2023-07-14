//package com.kwy.management.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
///**
// * @author haoy
// * @description
// * @date 2023/7/13 16:25
// */
////@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//    // 在这里配置安全规则和认证方式
//    @Bean
//    public UserDetailsService userDetailsService() throws Exception {
//        // ensure the passwords are encoded properly
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("password").roles("USER").build());
//        manager.createUser(users.username("admin").password("123456").roles("USER","ADMIN").build());
//        return manager;
//    }
//
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests(authorize -> authorize
////                        .anyRequest().authenticated()
////                )
////                .formLogin(withDefaults())
////                .httpBasic(withDefaults());
////    }
//    @Configuration
//    @Order(1)
//    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .antMatcher("/**")
//                    .authorizeRequests(authorize -> authorize
//                            .anyRequest().permitAll()
//                    )
//                    .httpBasic(withDefaults());
//            http.formLogin().loginProcessingUrl("/api/employees/login");
//        }
//    }
//
//    @Configuration
//    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests(authorize -> authorize
//                            .anyRequest().authenticated()
//                    )
//                    .formLogin(withDefaults());
//        }
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("http://localhost:8080");
//        config.addAllowedMethod("*");
//        config.addAllowedHeader("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
//
//}
