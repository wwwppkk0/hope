package com.sky.admin.config;

import com.sky.admin.core.TigerAuthenticationFailureHandler;
import com.sky.admin.core.TigerAuthenticationSuccessHandler;
import com.sky.admin.server.MyUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TigerAuthenticationFailureHandler tigerAuthenticationFailureHandler;

    @Autowired
    private TigerAuthenticationSuccessHandler tigerAuthenticationSuccessHandler;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetails(){
        return new MyUserDetailServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetails());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage( "/authentication/require")//自定义登录请求
                .loginProcessingUrl("/authentication/form")//自定义表单登录地址
                .successHandler(tigerAuthenticationSuccessHandler)
                .failureHandler(tigerAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/authentication/form")//此路径放行 否则会陷入死循环
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();//跨域关闭

    }

    @Override
    public void configure(WebSecurity web) throws Exception{

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

}
