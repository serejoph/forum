package pedro.serejo.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import pedro.serejo.forum.model.Topico;
import pedro.serejo.forum.repository.TopicoRepository;

public class UpdateTopicoForm {
	
	@NotBlank
	@Size(min = 5)
	String title;
	@NotBlank
	@Size(min = 5)
	String mensagem;
	
	public UpdateTopicoForm() {
	}

	public UpdateTopicoForm(@Size(min = 5) String title, @Size(min = 5) String mensagem) {
		super();
		this.title = title;
		this.mensagem = mensagem;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Optional<Topico> update(Long id, TopicoRepository topicoRep) {
		Optional<Topico> optional = topicoRep.findById(id);
		if (optional.isPresent()) {
			Topico topico = optional.get();
			topico.setMensagem(mensagem);
			topico.setTitulo(title);
		}
		return optional;
	}
	
}
