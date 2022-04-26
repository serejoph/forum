package pedro.serejo.forum.controller.dto;

public class TokenDto {

	private String token;
	private String authMethod;
	
	public TokenDto() {
	}
	
	public TokenDto(String token, String authMethod) {
		this.token = token;
		this.authMethod = authMethod;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAuthMethod() {
		return authMethod;
	}
	public void setAuthMethod(String authMethod) {
		this.authMethod = authMethod;
	}
	
	
	
}
