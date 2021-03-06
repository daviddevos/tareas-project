package com.coopeuch.tareas.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.coopeuch.tareas.utils.Constantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TareaDTO extends BaseDTO{
	
	private static final long serialVersionUID = -574239481867398633L;

	@NotNull(message = Constantes.ERROR_CAMPO_REQUERIDO)
	private Long identificador;
	
	@NotEmpty(message = Constantes.ERROR_CAMPO_REQUERIDO)
	@Size(max = 255, message = Constantes.ERROR_EXCEDE_TAMANO_MAXIMO)
	@NotBlank(message = Constantes.ERROR_CAMPO_VACIO)
	private String descripcion;
	
	@NotNull(message = Constantes.ERROR_CAMPO_REQUERIDO)
	@DateTimeFormat(pattern = Constantes.FORMATO_FECHA_YYYY_MM_DD_HH_MM_SS)
	private Date fechaCreacion;
	
	@NotNull(message = Constantes.ERROR_CAMPO_REQUERIDO)
	@Column(name = "vigente", nullable = false)
	private Boolean vigente;
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == getClass()) {
			final TareaDTO other = (TareaDTO) obj;
			return new EqualsBuilder().append(this.identificador, other.identificador)
					.append(this.descripcion, other.descripcion).build().booleanValue();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(identificador).append(descripcion).build();
	}
	
}
