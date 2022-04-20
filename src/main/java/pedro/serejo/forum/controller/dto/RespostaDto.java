package pedro.serejo.forum.controller.dto;

import java.time.LocalDateTime;

import pedro.serejo.forum.model.Resposta;

public class RespostaDto {

	private Long id;
	private String message;
	private LocalDateTime creationDate;
	private String user;
	
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.message = resposta.getMensagem();
		this.creationDate = resposta.getDataCriacao();
		this.user = resposta.getAutor().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getUser() {
		return user;
	}
	
	
	
}
