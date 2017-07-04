package br.ufsc.bridge.res.service.dto.repository;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositorySaveDocumentDTO {

	private Date dataInicioAtendimento;
	private Date dataFimAtendimento;

	private String cnsPaciente;

	private String cnsProfissional;
	private String nomeProfissional;
	private String cboProfissional;
	private String descricaoCboProfissional;
	private String cnesUnidadeSaude;
	private String nomeUnidadeSaude;

	private String documentId;

	private String document;

	private String url;
}
