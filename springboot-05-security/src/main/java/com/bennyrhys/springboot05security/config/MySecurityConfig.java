package com.bennyrhys.springboot05security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //注解里带里configure注解
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    //定义授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        定制请求的授权规则
         http.authorizeRequests().antMatchers("/").permitAll()
                 .antMatchers("/level1/**").hasAnyRole("vip1")
                 .antMatchers("/level2/**").hasAnyRole("vip2")
                 .antMatchers("/level3/**").hasAnyRole("vip3");

         ////开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
        //1、/login来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细规定
        //4、默认post形式的 /login代表处理登陆
        //5、一但定制loginPage；那么 loginPage的post请求就是登陆

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        //1、访问 /logout 表示用户注销，清空session
        //2、注销成功会返回 /login?logout 页面；

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie

    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        此处也可以jdbc，此时暂时是内存
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("root1").password(passwordEncoder().encode("root1")).roles("vip1","vip2")
                .and()
                .withUser("root2").password(passwordEncoder().encode("root2")).roles("vip2","vip3")
                .and()
                .withUser("root3").password(passwordEncoder().encode("root3")).roles("vip1","vip3");
    }

    /**
     * 密码加密规则5.x 新版本调用的方法
     */
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
