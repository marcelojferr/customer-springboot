package com.javaproject.customer_springboot.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.javaproject.customer_springboot.dto.UserDetailsImplDTO;
import com.javaproject.customer_springboot.exception.UserException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

	@Value("${projeto.jwtSecret}")
	private String jwtSecret;
	
	@Value("${projeto.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateTokenFromUserDetailsImpl(UserDetailsImplDTO userDetail) {
		return Jwts.builder().subject(userDetail.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(getSigninKey()).compact();
	}
	
	public SecretKey getSigninKey() {
		 byte[] bytes = Base64.getDecoder()
			 .decode(jwtSecret.getBytes(StandardCharsets.UTF_8));
		          return new SecretKeySpec(bytes, "HmacSHA256"); 
	}

	public String getUsernameToken(String token) {
		return Jwts.parser().verifyWith(getSigninKey()).build()
				.parseSignedClaims(token).getPayload().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(authToken);
			return true;	
		}catch(MalformedJwtException e) {
			 new UserException("Token inválido " + e.getMessage());
		}catch(ExpiredJwtException e) {
			 new UserException("Token expirado " + e.getMessage());
		}catch(UnsupportedJwtException e) {
			 new UserException("Token não suportado " + e.getMessage());
		}catch(IllegalArgumentException e) {
			 new UserException("Token Argumento inválido " + e.getMessage());
		}
		
		return false;
	}
}
