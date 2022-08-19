package com.ud26_SpringMySQL_Ex1;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig  implements WebMvcConfigurer  {

	protected void configure(HttpSecurity http) throws Exception {
    	http
    	.csrf().disable()
    	.authorizeRequests()
    	.antMatchers(HttpMethod.OPTIONS, "/**").authenticated()
        .antMatchers(HttpMethod.GET,"/**").authenticated()
        .antMatchers(HttpMethod.POST,"/**").authenticated()
        .antMatchers(HttpMethod.PUT, "/**").authenticated()
        .antMatchers(HttpMethod.DELETE,"/**").hasRole("admin")
        .anyRequest().authenticated()
        .and().formLogin().permitAll()
        .and().httpBasic();
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*").allowedOrigins("*").allowedMethods("*");
    }

}