package com.joshua.findcomputer.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserAuthRequestPayload;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	public JwtUsernamePasswordAuthFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
		this.authenticationManager = authenticationManager;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			UserAuthRequestPayload authRequest = new ObjectMapper()
				.readValue(request.getInputStream(), UserAuthRequestPayload.class);

			Authentication authentication = new UsernamePasswordAuthenticationToken(
				authRequest.getUsername(), // principal
				authRequest.getPassword() // credential
			);

			// check if user exists and password is correct
			Authentication auth = authenticationManager.authenticate(authentication);
			return auth;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.attemptAuthentication(request, response);
	}

	/**
	 * invoked when successfully authenticated, create token
	 * @param request
	 * @param response
	 * @param chain
	 * @param authResult
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

		// generate token
		String token = Jwts.builder()
			.setSubject(authResult.getName()) // get username
			.claim("authorities",authResult.getAuthorities())
			.setIssuedAt(new Date())
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays()))) // exp each 1 week
			.signWith(secretKey)
			.compact();

		// send token to client
		response.addHeader(jwtConfig.getAuthorizationHeader(),jwtConfig.getTokenPrefix()+token);
	}
}
