package br.ufsc.bridge.res.service.rest.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {

	private DataDTO attachment;

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DataDTO {

		private String contentType = "application/json";

		private String data;

		private String url;

	}

}
