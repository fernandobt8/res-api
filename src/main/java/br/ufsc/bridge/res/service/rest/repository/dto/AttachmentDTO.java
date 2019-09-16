package br.ufsc.bridge.res.service.rest.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AttachmentDTO {

	private DataDTO attachment;

	@Getter
	@Setter
	@Builder
	public static class DataDTO {

		private final String contentType = "application/json";

		private String data;

	}

}
