package co.com.udem.clubfutbol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import co.com.udem.clubfutbol.security.jwt.JwtSecurityConfigurer;
import co.com.udem.clubfutbol.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/auth/signin").permitAll()
				// .antMatchers(HttpMethod.GET, "/clubes/**").permitAll()
				// .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
				// .antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
				// .antMatchers(HttpMethod.GET, "/users/**").permitAll()
				.antMatchers(HttpMethod.POST, "/users/addUser**").permitAll()
				// .antMatchers(HttpMethod.POST, "/clubes/adicionarClub**").permitAll()
				.anyRequest().authenticated().and()
				.apply(new JwtSecurityConfigurer(jwtTokenProvider));
		// @formatter:on

		// http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();

	}

}
