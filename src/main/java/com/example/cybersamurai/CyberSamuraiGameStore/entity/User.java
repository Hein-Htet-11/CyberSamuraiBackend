package com.example.cybersamurai.CyberSamuraiGameStore.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	@NotBlank(message = "Required")
	private String name;

	@Column(length = 100, nullable = false)
	@NotBlank(message = "Required")
	private String password;

	@Column(length = 100, nullable = false, unique = true)
	@NotBlank(message = "Required")
	private String gmail;

	@Column(columnDefinition = "ENUM('active', 'deactivated', 'banned') NOT NULL")
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Column(columnDefinition = "ENUM('admin', 'user') NOT NULL")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Column(nullable = false)
	private LocalDate startJoinDate;

	private LocalDate lastJoinDate;

	private int accessCount;

	@Column(nullable = false)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}
