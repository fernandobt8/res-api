package br.ufsc.bridge.res.service.dto.repository;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositorySaveDTO {

	private String cnsProfissional;
	private String cboProfissional;
	private String cnesUnidadeSaude;
	private String nomeUnidadeSaude;

	private String cnsPaciente;
	private String idInstalacao;
	private String submissionSetId;

	private List<RepositorySaveDocumentDTO> documents;

}
