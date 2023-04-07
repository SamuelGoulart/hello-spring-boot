package br.com.hello.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "O campo name e obrigatório")
    @Size(min = 3, max = 80)
    private String name;
    
    @Email(message = "O campo e-mail é inválido")
	private String email;
    
    @NotEmpty
    @Pattern(regexp = "^\\d{11}$")
	private String phone;
    
}
