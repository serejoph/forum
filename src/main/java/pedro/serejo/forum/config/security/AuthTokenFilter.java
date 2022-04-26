package pedro.serejo.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import pedro.serejo.forum.model.Usuario;
import pedro.serejo.forum.repository.UserRepository;

public class AuthTokenFilter extends OncePerRequestFilter{

	TokenService tokenService;
	UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
				
		boolean isValid = tokenService.validate(token);
		if (isValid) {
			authenticate(token);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private void authenticate(String token) {
		Long id = tokenService.getId(token);
		Usuario user = userRepository.findById(id).get();
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), id, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
		
	}

	public AuthTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header == null || !header.startsWith("Bearer ")) return null;
		return header.substring(7, header.length());
	}
	
}
