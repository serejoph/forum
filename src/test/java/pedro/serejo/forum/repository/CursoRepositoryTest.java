package pedro.serejo.forum.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import pedro.serejo.forum.model.Curso;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class CursoRepositoryTest {

	@Autowired
	CursoRepository repository;
	@Autowired
	TestEntityManager em;
	
	@Test
	void carregarCursoPeloNome() {
		
		String nomeCurso = "Spring Boot";
		Curso curso = repository.findByNome(nomeCurso);
		Assertions.assertNotNull(curso);
		Assertions.assertEquals(curso.getNome(), "Spring Boot");
	}
	
	@Test
	void carregarCursoInexistente() {
		
	
		
		String nomeCurso = "Curso Inexistente";
		Curso curso = repository.findByNome(nomeCurso);
		Assertions.assertNull(curso);
		
	}


}
