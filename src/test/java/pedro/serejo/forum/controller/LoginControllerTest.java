package pedro.serejo.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.common.net.HttpHeaders;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = { "test", "prod" })
public class LoginControllerTest {

	@Autowired
	MockMvc mock;

	@Test
	public void dadosDeAutenticaçaoCorretos() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/auth").contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"aluno@email.com\", \"password\":\"123456\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void autenticacaoComSenhaIncorreta() throws Exception {

		mock.perform(MockMvcRequestBuilders.post("/auth").contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"aluno@email.com\", \"password\":\"12345\"}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void autenticacaoComSenhaEEmailIncorretos() throws Exception {

		mock.perform(MockMvcRequestBuilders.post("/auth").contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"naoAluno@email.com\", \"password\":\"12345\"}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void acessoAutorizadoComAutenticacaoJWT() throws Exception {

		mock.perform(MockMvcRequestBuilders.get("/topicos/2").header("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJGb3J1bSBBUEkiLCJzdWIiOiIxIiwiaWF0IjoxNjUxMjgxMzg2LCJleHAiOjE2NTEzNjc3ODZ9.8xLhKpyYMy77QAXkfMVkuJeqVVO5dmRSP4xLx1AdvMA"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void acessoRecusadoComAutenticacaoJWT() throws Exception {

		mock.perform(MockMvcRequestBuilders.get("/topicos/2").header("Authorization",
				"Bearer ayJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJGb3J1bSBBUEkiLCJzdWIiOiIxIiwiaWF0IjoxNjUxMjgxMzg2LCJleHAiOjE2NTEzNjc3ODZ9.8xLhKpyYMy77QAXkfMVkuJeqVVO5dmRSP4xLx1AdvMA"))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

}
