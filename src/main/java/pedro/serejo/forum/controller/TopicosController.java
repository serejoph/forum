package pedro.serejo.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pedro.serejo.forum.controller.dto.TopicoDto;
import pedro.serejo.forum.controller.form.TopicoForm;
import pedro.serejo.forum.model.Topico;
import pedro.serejo.forum.repository.CursoRepository;
import pedro.serejo.forum.repository.TopicoRepository;

@RestController
@RequestMapping("topicos")
public class TopicosController {

	@Autowired
	TopicoRepository topicoRep;
	
	@Autowired
	CursoRepository cursoRep;

	@GetMapping
	public List<TopicoDto> list(String curso) {
		List<Topico> topicos = (curso == null) ? topicos = topicoRep.findAll() : topicoRep.findByCursoNome(curso); 
		
		return TopicoDto.converter(topicos);
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
		Topico topico = topicoForm.toTopico(cursoRep);
		if (topico.getCurso() != null) {
			topicoRep.save(topico);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(topico.getId()).toUri();
//			uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(topico.getId()).toUri();
			return ResponseEntity.created(uri).body(new TopicoDto(topico));
		}
		return ResponseEntity.badRequest().build();
	}
		
}
