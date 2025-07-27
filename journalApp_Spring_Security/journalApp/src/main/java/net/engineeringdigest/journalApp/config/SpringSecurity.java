package net.engineeringdigest.journalApp.config;



import net.engineeringdigest.journalApp.service.UserDetailsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//
@Configurable
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
//
    @Autowired
    private UserDetailsServiceimpl userDetailsService;
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        //super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/journal/**","/user/**").authenticated()
//                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
               .anyRequest().permitAll()
                .and()
                .httpBasic();
//        http.csrf().disable();
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
    }

//user,password,authentication related work
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
//
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
