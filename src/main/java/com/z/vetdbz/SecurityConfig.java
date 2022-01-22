// package com.z.vetdbz;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.User.UserBuilder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

// @Autowired
// private BCryptPasswordEncoder passwordEncoder;

// public void configGlobal(AuthenticationManagerBuilder managerBuilder) throws
// Exception {
// PasswordEncoder encoder = passwordEncoder;
// UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);

// managerBuilder.inMemoryAuthentication()
// .withUser(userBuilder.username("admin").password("admin").roles("ADMIN"))
// .withUser(userBuilder.username("user").password("123").roles("USER"));
// }

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http.authorizeRequests()
// .antMatchers("/ruta");
// }

// }
