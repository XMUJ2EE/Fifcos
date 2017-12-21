package xmu.crms.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author mads
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    // 认证管理器
    @Bean
    FifcosAuthenticationProvider fifcosAuthenticationProvider(){
        return new FifcosAuthenticationProvider();
    }

    // 配置过滤器
    FifcosAuthenticationProcessingFilter fifcosAuthenticationProcessingFilter(AuthenticationManager authenticationManager){
        FifcosAuthenticationProcessingFilter fifcosAuthenticationProcessingFilter = new FifcosAuthenticationProcessingFilter();
        // 添加认证器
        fifcosAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        // 重写失败时跳转页面
        fifcosAuthenticationProcessingFilter.setAuthenticationFailureHandler(new AjaxAuthFailHandler());
        fifcosAuthenticationProcessingFilter.setAuthenticationSuccessHandler(new AjaxAuthSuccessHandler());
        return fifcosAuthenticationProcessingFilter;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/img/**","/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/register", "/login","wechat_login").permitAll()
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/teacher/**").hasRole("TEACHER")
                .anyRequest().authenticated()
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/login")
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().csrf().disable();

        http.addFilterBefore(fifcosAuthenticationProcessingFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(fifcosAuthenticationProvider());
    }
}