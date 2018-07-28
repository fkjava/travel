package org.fkjava.travel.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fkjava.travel.security.service.SpringDataUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//
                .antMatchers("/webjars/**", "/resources/**", "/site/index", "/").permitAll()//
                .antMatchers("/security/register").permitAll()//
//                .antMatchers("/security/login").permitAll()//
                .antMatchers("/**").hasRole("USER")//
                .and()//
                // 使用表单登录
                .formLogin()//
                // 登录表单的用户名和密码字段名称
                .usernameParameter("loginName")//
                .passwordParameter("password")//
                // 显示登录页面的URL
                .loginPage("/security/login")//
                // 执行登录的URL
                .loginProcessingUrl("/security/do-login")//
                // 登录成功后的默认URL。这个URL会在直接访问登录页面的时候使用。
                .defaultSuccessUrl("/")//
                .failureUrl("/security/login")//
                .permitAll()//
                .and()//
                .logout()//
                .logoutUrl("/security/logout")//
                .logoutSuccessUrl("/")//
                .permitAll()//
        //
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user")
//                .password("$2a$10$X84AOCWhADpsapMqeOcl1e1n3HVf8aqrFPz4cbhakPmCCdSxCRuBW").roles("USER");
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 在请求完成以后，删除Session里面的SPRING_SECURITY_LAST_EXCEPTION对象
        registry.addInterceptor(new HandlerInterceptorAdapter() {

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                    Exception ex) throws Exception {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
                }
            }
        });
    }
}
