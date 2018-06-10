package edu.mum.coffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/createProduct", "/editProduct", "/deleteProduct", "/createPerson", "/restClient")
				.hasAnyAuthority("ADMIN").antMatchers("/userProfile", "/placeOrder").hasAnyAuthority("USER")
				.anyRequest().permitAll().and().formLogin().loginPage("/login").usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/login").failureUrl("/displayLogin").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).invalidateHttpSession(true)
				.logoutSuccessUrl("/products").permitAll();
		http.csrf().disable();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {

		try {
			auth.jdbcAuthentication().dataSource(dataSource)
					.usersByUsernameQuery("select username,password, enabled from users where username=?")
					.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}