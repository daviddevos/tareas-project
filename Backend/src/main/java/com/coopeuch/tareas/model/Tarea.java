package com.coopeuch.tareas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tarea")
@NoArgsConstructor
@AllArgsConstructor
public class Tarea implements Serializable{
	
	private static final long serialVersionUID = 3762545907455319422L;

	@Id
	@Column(name = "identificador")
	private Long identificador;
	
	@Column(name = "descripcion", nullable = false, length = 255)
	private String descripcion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "vigente", nullable = false)
	private Boolean vigente;
	
}
