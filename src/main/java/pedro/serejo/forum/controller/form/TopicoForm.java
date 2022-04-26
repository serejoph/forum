package pedro.serejo.forum.controller.form;

import javax.validation.constraints.Size;

import pedro.serejo.forum.constraints.CursoConstraint;
import pedro.serejo.forum.model.Topico;
import pedro.serejo.forum.repository.CursoRepository;

public class TopicoForm {
	
	@Size(min = 5)
	String title;
	@Size(min = 5)
	String mensagem;
	@CursoConstraint
	String curso;

	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String nome) {
		this.title = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public TopicoForm() {
	}

	public TopicoForm(String nome, String mensagem, String curso) {
		this.title = nome;
		this.mensagem = mensagem;
		this.curso = curso;
	}
	


	@Override
	public String toString() {
		return "TopicoForm [nome=" + title + ", mensagem=" + mensagem + ", curso=" + curso + "]";
	}

	public Topico toTopico(CursoRepository cursoRepository) {

		return new Topico(title, mensagem, cursoRepository.findByNome(curso));
	}



}
