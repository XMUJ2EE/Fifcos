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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xmu.crms.security.*;

/**
 * @author mads
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // 认证管理器
    @Bean
    public FifcosAuthenticationProvider fifcosAuthenticationProvider(){
        return new FifcosAuthenticationProvider();
    }

//    // 配置过滤器
//    @Bean
//    public FifcosAuthenticationProcessingFilter fifcosAuthenticationFilter(AuthenticationManager authenticationManager){
//        FifcosAuthenticationProcessingFilter fifcosAuthenticationProcessingFilter = new FifcosAuthenticationProcessingFilter();
//        // 添加认证器
//        fifcosAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
//        // 重写失败时跳转页面
//        fifcosAuthenticationProcessingFilter.setAuthenticationFailureHandler(new AjaxAuthFailHandler());
//        fifcosAuthenticationProcessingFilter.setAuthenticationSuccessHandler(new AjaxAuthSuccessHandler());
//        return fifcosAuthenticationProcessingFilter;
//    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

//    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/img/**","/js/**","/student/**","/teacher/**","/register","/school/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureHandler(new AjaxAuthFailHandler())
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/login?error")
                .authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
//        http.addFilterBefore(fifcosAuthenticationFilter(authenticationManager()),lass);
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(fifcosAuthenticationProvider());
    }
}