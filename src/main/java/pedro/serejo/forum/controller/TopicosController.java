package pedro.serejo.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pedro.serejo.forum.controller.dto.DetailedTopicDto;
import pedro.serejo.forum.controller.dto.TopicoDto;
import pedro.serejo.forum.controller.form.TopicoForm;
import pedro.serejo.forum.controller.form.UpdateTopicoForm;
import pedro.serejo.forum.model.Topico;
import pedro.serejo.forum.repository.CursoRepository;
import pedro.serejo.forum.repository.TopicoRepository;

@RestController
@RequestMapping(value="/topicos")
public class TopicosController {

	@Autowired
	TopicoRepository topicoRep;

	@Autowired
	CursoRepository cursoRep;

	@GetMapping
	@Cacheable(value="listTopics")
	public Page<TopicoDto> list(@RequestParam(required = false) String curso,
			@PageableDefault(size = 5, direction = Direction.ASC, sort = "titulo") Pageable pageable) {

		// Pageable pageable = PageRequest.of(page, size, Direction.ASC, sort);

		Page<Topico> topicos = (curso == null) ? topicos = topicoRep.findAll(pageable)
				: topicoRep.findByCursoNome(curso, pageable);
		return TopicoDto.converter(topicos);

	}

	
	@Cacheable(value="detailTopic")
	@GetMapping("{id}")
	public ResponseEntity<DetailedTopicDto> detailTopic(@PathVariable("id") Long id) {

		Optional<Topico> topico = topicoRep.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetailedTopicDto(topico.get()));
		}

		return ResponseEntity.notFound().build();

	}

	@CacheEvict(value = {"listTopics", "detailTopic"}, allEntries = true)
	@Transactional
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm,
			UriComponentsBuilder uriBuilder) {

		Topico topico = topicoForm.toTopico(cursoRep);
		topicoRep.save(topico);
		
		URI uri = uriBuilder.path("/{id}").buildAndExpand(topico.getId()).toUri();
//			uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));

	}
	
	@CacheEvict(value = {"listTopics", "detailTopic"}, allEntries = true)
	@Transactional
	@PutMapping("{id}")
	public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicoForm topicoForm) {
		Optional<Topico> optional = topicoForm.update(id, topicoRep);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new TopicoDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@CacheEvict(value = {"listTopics", "detailTopic"}, allEntries = true)
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Optional<Topico> optional = topicoRep.findById(id);

		if (optional.isPresent()) {
			topicoRep.delete(optional.get());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}
	



}
