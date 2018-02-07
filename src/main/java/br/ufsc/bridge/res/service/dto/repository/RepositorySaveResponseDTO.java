package br.ufsc.bridge.res.service.dto.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.service.exception.ResException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositorySaveResponseDTO {

	private String url;
	private boolean success;
	private ResException error;

	public RepositorySaveResponseDTO(String url) {
		this.url = url;
		this.success = true;
	}
}
