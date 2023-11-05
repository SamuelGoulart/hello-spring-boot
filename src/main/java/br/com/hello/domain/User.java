package br.com.hello.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {
	
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user")
	@SequenceGenerator(name="user",sequenceName="SQ_T_USER",allocationSize=1)
	private int userId;


    @Column(name = "name", nullable = false)
    @NotEmpty(message = "O campo name e obrigatório")
    @Size(min = 3, max = 80)
    private String name;
    
    @Email(message = "O campo e-mail é inválido")
	private String email;
    
    @NotEmpty
    @Pattern(regexp = "^\\d{11}$")
	private String phone;
    
    @NotEmpty
  	@Column(name="password")
	private String password;
    
    @CreationTimestamp
  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name="created_at", nullable=false)
      private Date createdAt;
  	
  	@Column(name="update_at")
      private Date updateAt;
  	
  	@Column(name="delete_at")
      private Date deleteAt;
    
}
