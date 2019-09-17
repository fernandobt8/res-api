package br.ufsc.bridge.res.service.rest.repository.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveDTO {

	private Date data;

	private String unidadeId;

	private String profissionalId;

	private String pacienteId;

	private String documento;
}
