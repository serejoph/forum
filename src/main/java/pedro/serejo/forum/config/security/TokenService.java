package pedro.serejo.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pedro.serejo.forum.model.Usuario;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expirationTime;

	@Value("${forum.jwt.secret}")
	private String jwtSecret;

	public String generateToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date now = new Date();
		Date expDate = Date.from(now.toInstant().plusMillis(Long.parseLong(expirationTime)));

		String jwt = Jwts.builder().setIssuer("Forum API").setSubject(usuario.getId().toString()).setIssuedAt(now)
				.setExpiration(expDate)
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
		return jwt;
	}

	public boolean validate(String token) {
		try{
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public Long getId(String token) {
		String stringId = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		return Long.parseLong(stringId);
	}

}
