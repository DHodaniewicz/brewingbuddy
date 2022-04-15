//package pl.brewingbuddy;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import pl.brewingbuddy.servicess.SpringDataUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //@Autowired
//    //private SpringDataUserDetailsService springDataUserDetailsService;
//    //@Autowired
//    //private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    //  @Override
//  //  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//  //      auth.inMemoryAuthentication()
//  //              .withUser("user1").password("{noop}user123").roles("USER")
//  //              .and()
//  //              .withUser("admin1").password("{noop}admin123").roles("ADMIN");
//  //  }
//
//     // @Override
//     // public void configure(AuthenticationManagerBuilder auth) throws Exception {
//     //
//     // }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/recipe/update/**").permitAll()
//                .antMatchers("/recipe/add/**").permitAll()
//                .antMatchers("/recipe/all/**").permitAll()
//                .antMatchers("/recipe/details/**").permitAll()
//                .and().formLogin().loginPage("/login")
//                .permitAll();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SpringDataUserDetailsService customUserDetailsService() {
//        return new SpringDataUserDetailsService();
//    }
//
//}
