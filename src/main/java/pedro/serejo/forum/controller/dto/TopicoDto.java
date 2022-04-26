package pedro.serejo.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import pedro.serejo.forum.model.Topico;

public class TopicoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	

	
	public TopicoDto(Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	public static Page<TopicoDto> converter(Page<Topico> page) {
		return page.map(TopicoDto::new);
		
	}
	
	
	
}
