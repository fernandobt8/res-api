package br.ufsc.bridge.res.service.rest.repository.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class MetaDTO {

	public static final MetaDTO DEFAULT = new MetaDTO();

	private final List<String> profile = Arrays.asList("http://rnds.saude.gov.br/fhir/r4/StructureDefinition/rnds-documentreference-1.0");
}
