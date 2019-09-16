package br.ufsc.bridge.res.service.rest.repository.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Getter
@Setter
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
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

	public RestRepositorySaveDTO(SaveDTO dto) {
		this.date = dto.getData();
		this.subject = ReferenceDTO.builder().reference(dto.getPacienteId()).build();
		this.authors = Arrays.asList(
				ReferenceDTO.builder().reference(dto.getUnidadeId()).build(),
				ReferenceDTO.builder().reference(dto.getProfissionalId()).build()
		);
		this.contents = Arrays.asList(AttachmentDTO.builder()
				.attachment(AttachmentDTO.DataDTO.builder()
						.data(dto.getDocumento())
						.build())
				.build());
	}

	public String stringfy() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error("Não foi possível converter em string", e);
			return StringUtils.EMPTY;
		}
	}

	public SaveDTO toDto() {
		return SaveDTO.builder()
				.data(this.date)
				.documento(CollectionUtils.isEmpty(this.contents) ? null : this.contents.get(0).getAttachment().getData())
				.pacienteId(this.subject.getReference())
				.unidadeId(this.getAuthor("Organization/"))
				.profissionalId(this.getAuthor("PractitionerRole/"))
				.build();
	}

	private String getAuthor(String name) {
		for (ReferenceDTO author: this.authors) {
			if(StringUtils.startsWith(author.getReference(), name)) {
				return author.getReference();
			}
		}
		return null;
	}

}

