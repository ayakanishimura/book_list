package jp.co.sss.book.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginForm {
	
	@NotNull
	@Max(value = 5)
//	@Pattern(regexp = "^[0-9]+$") 
	private int bookUserId;
	
	@NotBlank
	@Size(max = 16)
	@Pattern(regexp = "^[a-zA-Z0-9]+$") 
    private String password;

	public int getBookUserId() {
		return bookUserId;
	}

	public void setBookUserId(int bookUserId) {
		this.bookUserId = bookUserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
