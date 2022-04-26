package pedro.serejo.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pedro.serejo.forum.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String email);
	
}
