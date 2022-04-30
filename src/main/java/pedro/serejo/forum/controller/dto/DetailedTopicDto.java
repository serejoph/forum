package pedro.serejo.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import pedro.serejo.forum.model.StatusTopico;
import pedro.serejo.forum.model.Topico;

public class DetailedTopicDto {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;

	
	
	private StatusTopico status;
	private List<RespostaDto> respostas;
	private String curso;
	
	public DetailedTopicDto(Topico topico) {
		this.id = topico.getId();
		this.title = topico.getTitulo();
		this.message = topico.getMensagem();
		this.creationDate = topico.getDataCriacao();
		
		this.status = topico.getStatus();
		this.respostas = topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
		this.curso = topico.getCurso().getNome();
		
		
	}

	public String getCurso() {
		return curso;
	}


	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}



	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
	}


	
	
	
}
