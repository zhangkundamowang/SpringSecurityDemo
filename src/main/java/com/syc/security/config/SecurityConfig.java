package com.syc.security.config;

import com.syc.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
/*EnableGlobalMethodSecurity:开启全局的方法级别的安全拦截*/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    //对要访问的页面资源进行授权限制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //链式调用/编程--->构建器模式.
        http.authorizeRequests() //对页面|资源请求进行认证授权操作
                .antMatchers("/css/**", "/index")
                .permitAll()
                .antMatchers("/user/**")
                //.hasAuthority("ROLE_USER");
                .hasRole("USER")
                .antMatchers("/blogs/**")
                //.hasRole("USER");
                .hasAnyRole("USER", "ADMIN")
                .and() //转折
                .formLogin() //设置登录相关信息
                .loginPage("/login")
                .failureForwardUrl("/login-error")
                .and() //未授权异常处理的信息
                .exceptionHandling()
                .accessDeniedPage("/401")
                .and()
                .logout() //对退出进行设置
                .logoutSuccessUrl("/");
    }

    //进行认证授权的方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //构建一个的用户,有2种方式:
        //1.在内存中创建一个用户:不会持久化.2.关联数据库,查询对应的用户信息.

        //BCryptPasswordEncoder:spring security中默认的密码加密方式,类似于md5.
        //BCryptPasswordEncoder:属于对称加密算法.des/3des/bcrypt等都是对称加密.
       /* 注意:新版本的spring-security中,必须明确指明要用的密码加密方案,否则:
        IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"*/

   /*     auth.inMemoryAuthentication()
               .passwordEncoder(new BCryptPasswordEncoder())
               .withUser("syc")
               .password(new BCryptPasswordEncoder().encode("123"))
               .roles("USER");*/

        //2.关联数据库,查询对应的用户信息.
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());

    }
}
