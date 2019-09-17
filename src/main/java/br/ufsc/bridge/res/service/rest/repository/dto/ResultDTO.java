package br.ufsc.bridge.res.service.rest.repository.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultDTO {

	private Long total;

	private List<EntryDTO> entry;

	@Getter
	@Setter
	@Builder
	public static class EntryDTO {

		private String fullUrl;

		private ResourceDTO resource;

	}

	@Getter
	@Setter
	@Builder
	public static class ResourceDTO {

		private String id;

		private MetaDTO meta;

		private String status;

		private TypeDTO type;

		private ReferenceDTO subject;

		private Date date;

		private List<ReferenceDTO> author;

		private List<AttachmentDTO> content;

	}

	public List<ItemDTO> toItems() {
		List<ItemDTO> items = new ArrayList<>();
		for(EntryDTO entry : this.entry) {
			ItemDTO item = new ItemDTO();
			item.setUuid(entry.getResource().getId());
			item.setUrl(entry.getFullUrl());
			items.add(item);
		}
		return items;
	}
}
