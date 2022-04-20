package pedro.serejo.forum.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CursoValidator.class)
@Documented
public @interface CursoConstraint {
	String message() default "Curso ${validatedValue} não encontrado na base de dados";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
