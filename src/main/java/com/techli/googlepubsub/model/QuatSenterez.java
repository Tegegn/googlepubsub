package com.techli.googlepubsub.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "quatsenterez")
@Data
public class QuatSenterez {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "start_date", nullable = false)
	private Timestamp startDate;
	
	@Column(name = "end_date", nullable = false)
	private Timestamp endDate;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "remark")
	private String remark;

}
