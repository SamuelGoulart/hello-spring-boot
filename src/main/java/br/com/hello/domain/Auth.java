package br.com.hello.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auth {
    @NotEmpty(message = "O campo email é obrigatório")
    @Email(message = "O campo e-mail é inválido")
	private String email;
    
    @NotEmpty(message = "O campo password e obrigatório")
	private String password;
}
