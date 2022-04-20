package pedro.serejo.forum.config.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationErrorDto> handler(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getFieldErrors();

		return fieldErrors.stream().map(
				x -> new ValidationErrorDto(x.getField(), messageSource.getMessage(x, LocaleContextHolder.getLocale())))
				.collect(Collectors.toList());

	}

}
