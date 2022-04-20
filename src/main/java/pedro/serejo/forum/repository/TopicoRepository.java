package pedro.serejo.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pedro.serejo.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {



	
	List<Topico> findByCursoNome(String nome);

}
