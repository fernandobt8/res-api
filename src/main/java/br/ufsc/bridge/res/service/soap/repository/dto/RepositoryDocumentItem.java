package br.ufsc.bridge.res.service.soap.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryDocumentItem {
	private String repositoryUniqueId;
	private String documentUniqueId;
	private String document;
}
