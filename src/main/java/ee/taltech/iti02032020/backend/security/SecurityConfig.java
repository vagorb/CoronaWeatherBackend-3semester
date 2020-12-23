package ee.taltech.iti02032020.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) //this makes spring use @Secured
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersConfig usersConfig;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//    @Autowired
//    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
//    @Resource
//    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(usersConfig.getUserName()).password(passwordEncoder().encode(usersConfig.getUserPassword()))
                .authorities(Roles.USER)
                .and()
                .withUser(usersConfig.getAdminName()).password(passwordEncoder().encode(usersConfig.getAdminPassword()))
                .authorities(Roles.USER, Roles.ADMIN);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().httpStrictTransportSecurity().disable()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // IT"S JUST STATELESS
                .and()
//                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/Forecast/**").permitAll()
                .anyRequest().fullyAuthenticated()
//                .and()
//                .formLogin();

//                .and()
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
    }
//                httpBasic()
//                .and()
//                .authorizeRequests()
//                // this is for url based security
////                    .antMatchers("/").permitAll()
////                    .antMatchers("/user").hasRole("USER")
////                    .antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
//                .and()
//                .csrf().disable() // cross site request forgery, it's a must if we use cookies
//                .headers().httpStrictTransportSecurity().disable(); // if this is not disabled your https frontend must have https ( not http ) on backend


//        		http
//                .authorizeRequests()
//                        .antMatchers("/").permitAll()
//                        .antMatchers("/user").hasAnyRole("ROLE_USER")
//                        .antMatchers("/admin").hasAnyRole("ROLE_ADMIN")
//                        .anyRequest()
//                        .authenticated()
//                        .and()
//                .anyRequest().authenticated()
//				.and()
//			.formLogin().and()
//			.httpBasic();
//    }
//        http.authorizeRequests()
//                .antMatchers("/securityNone").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();


//                .authenticationEntryPoint(authenticationEntryPoint);

//        http.addFilterAfter(new CustomFilter(),
//                BasicAuthenticationFilter.class);
//    }
//    		logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
//
//		http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//				.and()
//			.formLogin().and()
//			.httpBasic();
//}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}