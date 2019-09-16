package br.ufsc.bridge.res.service.rest.repository.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Builder
public class RestRepositorySaveDTO {

	private final String resourceType = "DocumentReference";

	private final String status = "current";

	private final MetaDTO meta = MetaDTO.DEFAULT;

	private final TypeDTO type = TypeDTO.DEFAULT;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date date;

	private ReferenceDTO subject;

	@Singular
	@JsonProperty("author")
	private List<ReferenceDTO> authors;

	@Singular
	@JsonProperty("content")
	private List<AttachmentDTO> contents;

}


