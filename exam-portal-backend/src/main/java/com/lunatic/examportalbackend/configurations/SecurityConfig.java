package com.lunatic.examportalbackend.configurations;

import com.lunatic.examportalbackend.services.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable()
                .authorizeRequests()

                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/login").permitAll()

//                .antMatchers(HttpMethod.POST, "/api/file/avatar").hasAnyAuthority("ADMIN", "USER", "MANAGER")
                .antMatchers(HttpMethod.POST, "/api/file/avatar").permitAll()
                .antMatchers(HttpMethod.POST, "/api/file/question").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/api/file/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/file/avatar/{fileCode}").hasAnyAuthority("ADMIN", "USER", "MANAGER")
                .antMatchers(HttpMethod.PUT, "/api/file/question/{fileCode}").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.DELETE, "/api/file/**").hasAnyAuthority("ADMIN", "USER", "MANAGER")

                .antMatchers(HttpMethod.POST, "/api/subClass/**").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/api/subClass/**").hasAnyAuthority("MANAGER", "USER")
                .antMatchers(HttpMethod.PUT, "/api/subClass/{subClassId}").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.PUT, "/api/subClass/join/**").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/api/subClass/detach/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/api/subClass/**").hasAuthority("MANAGER")

                .antMatchers(HttpMethod.POST, "/api/user/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/user/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/api/user/**").hasAnyAuthority("ADMIN", "MANAGER", "USER")
                .antMatchers(HttpMethod.PUT, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/subject/**").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/api/subject/**").hasAnyAuthority("USER", "MANAGER")
                .antMatchers(HttpMethod.PUT, "/api/subject/**").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.DELETE, "/api/subject/**").hasAuthority("MANAGER")

                .antMatchers(HttpMethod.POST, "/api/quiz/**").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/api/quiz/**").hasAnyAuthority("USER", "MANAGER")
                .antMatchers(HttpMethod.PUT, "/api/quiz/**").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.DELETE, "/api/quiz/**").hasAuthority("MANAGER")

                .antMatchers(HttpMethod.POST, "/api/question/**").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/api/question/**").hasAnyAuthority("USER", "MANAGER")
                .antMatchers(HttpMethod.PUT, "/api/question/**").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.DELETE, "/api/question/**").hasAuthority("MANAGER")

                .antMatchers(HttpMethod.POST, "/api/quizResult/**").hasAnyAuthority("USER", "MANAGER")
                .antMatchers(HttpMethod.GET, "/api/quizResult/**").hasAnyAuthority("USER", "MANAGER")

                .anyRequest().denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }
}
