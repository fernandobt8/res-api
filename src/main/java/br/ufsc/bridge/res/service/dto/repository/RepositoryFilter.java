package br.ufsc.bridge.res.service.dto.repository;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryFilter {

	private String cnsProfissional;
	private String cboProfissional;
	private String cnesProfissional;

	private List<DocumentItemFilter> documents = new ArrayList<>();

	public RepositoryFilter(String cnsProfissional, String cboProfissional, String cnesProfissional) {
		super();
		this.cnsProfissional = cnsProfissional;
		this.cboProfissional = cboProfissional;
		this.cnesProfissional = cnesProfissional;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DocumentItemFilter {
		private String repositoryURL;
		private String repositoryUniqueId;
		private String documentUniqueId;
	}
}
