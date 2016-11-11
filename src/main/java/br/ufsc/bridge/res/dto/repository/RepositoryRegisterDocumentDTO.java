package br.ufsc.bridge.res.dto.repository;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositoryRegisterDocumentDTO {

	private Date dataInicioAtendimento;
	private Date dataFimAtendimento;

	private String cnsPaciente;

	private String cnsProfissional;
	private String cboProfissional;
	private String cnesUnidadeSaude;

	private String documentId;

	private String document;
}
