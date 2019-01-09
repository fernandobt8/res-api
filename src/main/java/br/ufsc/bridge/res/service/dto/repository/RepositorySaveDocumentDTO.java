package br.ufsc.bridge.res.service.dto.repository;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.dab.TipoDocumento;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositorySaveDocumentDTO {

	private Date dataInicioAtendimento;
	private Date dataFimAtendimento;

	private String sourcePatientId;

	private String patientId;

	private String cnsProfissional;
	private String nomeProfissional;
	private String cboProfissional;
	private String descricaoCboProfissional;
	private String cnesUnidadeSaude;
	private String nomeUnidadeSaude;

	private String documentId;

	private String document;

	private TipoDocumento tipoDocumento;

	private String url;

	private ClassificationDTO classCode;

	private ClassificationDTO formatCode;

	private ClassificationDTO healthcareFacilityTypeCode;

	//cbo
	private ClassificationDTO practiceSettingCode;

	private ClassificationDTO typeCode;

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ClassificationDTO {
		private String code;
		private String codingScheme;
	}
}
