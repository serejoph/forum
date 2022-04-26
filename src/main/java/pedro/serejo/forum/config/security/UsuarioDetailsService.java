package pedro.serejo.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import pedro.serejo.forum.model.Usuario;
import pedro.serejo.forum.repository.UserRepository;

@Component
public class UsuarioDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = userRepository.findByEmail(username);
		if (user.isPresent()) {
			return user.get();
		}
		
	throw new UsernameNotFoundException(username);
		
	}

}
