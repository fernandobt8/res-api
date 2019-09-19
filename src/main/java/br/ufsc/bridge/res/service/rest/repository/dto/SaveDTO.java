package br.ufsc.bridge.res.service.rest.repository.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import br.ufsc.bridge.res.domain.TipoDocumento;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveDTO {

	private String id;

	private Date data;

	private String unidadeId;

	private String profissionalId;

	private String pacienteId;

	private TipoDocumento tipoDocumento;

	private String documento;
}
