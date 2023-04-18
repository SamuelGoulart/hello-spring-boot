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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.NumberFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_task")
public class Task {

	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task")
	@SequenceGenerator(name = "task", sequenceName = "SQ_T_TASK", allocationSize = 1)
	private int taskId;
	
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "O campo name e obrigatório")
    @Size(min = 3, max = 80)
    private String name;
    
    @Size(min = 3, max = 1000)
    @NotEmpty(message = "O campo description e obrigatório")
	@Column(name="description", nullable=false, length=1000)
    private	String description; 
  
    @Column(name = "spots")
    @NumberFormat
    private String spots;
    
    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", nullable=false)
    private Date createdAt;
	
	@Column(name="update_at")
    private Date updateAt;
	
	@Column(name="delete_at")
    private Date deleteAt;

}
