package com.github.zxhtom.login.security.config;

import com.github.zxhtom.login.security.filter.JwtLoginFilter;
import com.github.zxhtom.login.security.provider.MaltcloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.config
 * @date 2021/9/15 9:55
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义登录身份认证组件
        auth.authenticationProvider(new MaltcloudProvider(userDetailsService));
        //auth.inMemoryAuthentication().withUser("zxhtom").password(new BCryptPasswordEncoder().encode("123456")).roles("admin");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/jwtLogin/**").permitAll()
                .antMatchers("/user/insert").permitAll()
                .antMatchers("/**/*.css").permitAll()
                .antMatchers("/**/*.woff2").permitAll()
                .antMatchers("/**/*.jpg").permitAll()
                .antMatchers("/login.html","/login2.html", "/error.html").permitAll()
                .antMatchers("/test/hello2").hasRole("admin2")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login.html")
                .usernameParameter("userName")
                .failureForwardUrl("/error.html")
                .defaultSuccessUrl("/system.html", false)
                //.successForwardUrl("/success.html")
                .and()
                .addFilterAfter(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                //.addFilterAt(loginFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler())
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }

    @Bean
    public AccessDeniedHandler myAccessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

}
