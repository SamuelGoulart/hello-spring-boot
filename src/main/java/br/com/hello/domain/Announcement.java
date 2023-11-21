package br.com.hello.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_announcement")
public class Announcement {

	@Id
	@Column(name = "announcement_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "announcement")
	@SequenceGenerator(name = "announcement", sequenceName = "SQ_T_ANNOUNCEMENT", allocationSize = 1)
	private int announcementId;

	@ManyToOne 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Size(min = 3, max = 80)
	@NotEmpty(message = "O campo subject e obrigatório")
	@Column(name = "subject", nullable = false, length = 80)
	private String subject;

	@Size(min = 3, max = 1000)
	@NotEmpty(message = "O campo description e obrigatório")
	@Column(name = "description", nullable = false, length = 1000)
	private String description;
	
	@Column(name = "listing_title", length = 80)
	private String listingTitle;

	@Column(name = "payload", length = 255)
	private String payload;

	@Column(name = "display_number")
	private int displayNumber;

	@Column(name = "category", length = 20)
	private String category;

	@Column(name = "date_field", nullable = false, length = 5)
	private String date;
	
	@Column(name = "time_field", nullable = false, length = 5)
	private String time;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "update_at")
	private Date updateAt;

	@Column(name = "delete_at")
	private Date deleteAt;
	
	@Transient
    private int userId;
}
