package pedro.serejo.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pedro.serejo.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String curso);

}
