package pedro.serejo.forum.config.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authority implements GrantedAuthority {
 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String authorityName;
	
	@Override
	public String getAuthority() {

		return this.authorityName;
	}
	
}
