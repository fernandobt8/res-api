package br.ufsc.bridge.res.service.dto.repository;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO.ClassificationDTO;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositorySaveDTO {

	private String cnsProfissional;
	private String cboProfissional;
	private String nomeProfissional;
	private String cnesUnidadeSaude;
	private String nomeUnidadeSaude;

	private String patientId;
	private String idInstalacao;
	private String submissionSetId;

	private ClassificationDTO contentTypeCode;

	@Singular
	private List<RepositorySaveDocumentDTO> documents;
}
