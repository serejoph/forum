package pedro.serejo.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pedro.serejo.forum.config.security.TokenService;
import pedro.serejo.forum.controller.dto.TokenDto;
import pedro.serejo.forum.controller.form.LoginForm;

@RestController
@RequestMapping("auth")
public class LoginController {

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody LoginForm form) {
		
		UsernamePasswordAuthenticationToken usernamePasswordToken = form.toAuthToken();
//		try {
			Authentication authentication = authManager.authenticate(usernamePasswordToken);
			String jwtToken = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(jwtToken, "Bearer"));
			
//		} catch (AuthenticationException e) {
//			return ResponseEntity.badRequest().build();
//		}
	}
	
}
