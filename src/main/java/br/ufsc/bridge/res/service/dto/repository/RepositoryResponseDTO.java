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
public class RepositoryResponseDTO {

	private boolean success;
	private List<DocumentItem> documents = new ArrayList<>();

	public RepositoryResponseDTO(boolean success) {
		this.success = success;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DocumentItem {
		private String repositoryUniqueId;
		private String docucumentUniqueId;
		private String document;
	}
}
