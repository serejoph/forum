package pedro.serejo.forum.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pedro.serejo.forum.repository.CursoRepository;

public class CursoValidator implements ConstraintValidator<CursoConstraint, String>{

	@Autowired
	CursoRepository cursoRep;
	
	@Override
	public boolean isValid(String curso, ConstraintValidatorContext context) {
		if (cursoRep.findByNome(curso) != null)	return true;
		else return false;
	}

}
