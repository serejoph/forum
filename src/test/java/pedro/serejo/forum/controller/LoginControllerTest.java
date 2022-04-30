package pedro.serejo.forum.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = { "test", "prod" })
public class LoginControllerTest {

	@Autowired
	MockMvc mock;
	
	@Test
	public void dadosDeAutenticaçaoCorretos() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/auth").contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"aluno2@email.com\", \"password\":\"123456\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		
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

		String response = mock.perform(MockMvcRequestBuilders.post("/auth").contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"aluno2@email.com\", \"password\":\"123456\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		
		Pattern p = Pattern.compile("token\":\"(.*?)\"");
		Matcher m = p.matcher(response);
		
		String token = null;
		if (m.find()) token = m.group(1); 
		
		
		mock.perform(MockMvcRequestBuilders.get("/topicos/2").header("Authorization",
				"Bearer " + token))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void acessoRecusadoComAutenticacaoJWT() throws Exception {

		mock.perform(MockMvcRequestBuilders.get("/topicos/2").header("Authorization",
				"Bearer ayJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJGb3J1bSBBUEkiLCJzdWIiOiIxIiwiaWF0IjoxNjUxMjgxMzg2LCJleHAiOjE2NTEzNjc3ODZ9.8xLhKpyYMy77QAXkfMVkuJeqVVO5dmRSP4xLx1AdvMA"))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
		
	}

}
