package com.example.cybersamurai.CyberSamuraiGameStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Platform implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(length = 30, nullable = false)
	private String name;


}
