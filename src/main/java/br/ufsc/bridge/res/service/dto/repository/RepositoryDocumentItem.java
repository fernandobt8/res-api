package br.ufsc.bridge.res.service.dto.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.dab.TipoDocumento;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryDocumentItem {
	private String repositoryUniqueId;
	private String documentUniqueId;
	private String document;
	private TipoDocumento documentType;
}