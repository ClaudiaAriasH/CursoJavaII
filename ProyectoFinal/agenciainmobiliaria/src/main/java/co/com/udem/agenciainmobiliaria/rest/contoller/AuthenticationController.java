package co.com.udem.agenciainmobiliaria.rest.contoller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.agenciainmobiliaria.dto.AuthenticationRequest;
import co.com.udem.agenciainmobiliaria.repositories.UserRepository;
import co.com.udem.agenciainmobiliaria.security.jwt.JwtTokenProvider;
import co.com.udem.agenciainmobiliaria.util.Constantes;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;

	@PostMapping("/signin")
	public Map<String, String> signin(@RequestBody AuthenticationRequest data) {
		Map<String, String> response = new HashMap<>();
		try {
			String username = data.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + "no existe.")).getRoles());

			response.put(Constantes.CODIGO_HTTP, "200");
			response.put("token", token);
			response.put("username", username);
			return response;
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}
}
