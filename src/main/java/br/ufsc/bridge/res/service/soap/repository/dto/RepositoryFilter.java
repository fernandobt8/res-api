package br.ufsc.bridge.res.service.soap.repository.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryFilter {

	private String cnsProfissional;
	private String cboProfissional;
	private String cnesProfissional;

	@Singular
	private List<DocumentItemFilter> documents = new ArrayList<>();

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DocumentItemFilter {
		private String repositoryURL;
		private String repositoryUniqueId;
		private String documentUniqueId;
	}
}
