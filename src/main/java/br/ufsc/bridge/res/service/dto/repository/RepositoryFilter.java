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

	private List<DocumentItemFilter> documents = new ArrayList<>();

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DocumentItemFilter {
		private String repositoryUniqueId;
		private String documentUniqueId;
	}
}
