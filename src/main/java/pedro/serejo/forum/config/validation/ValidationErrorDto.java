package pedro.serejo.forum.config.validation;

public class ValidationErrorDto {

	String field;
	String message;
	
	public ValidationErrorDto(String field, String message) {
		this.field = field;
		this.message = message;
	}
	public String getField() {
		return field;
	}
	public String getMessage() {
		return message;
	}
	
	
}
