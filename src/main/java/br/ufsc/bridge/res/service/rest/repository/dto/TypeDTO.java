package br.ufsc.bridge.res.service.rest.repository.dto;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TypeDTO {

	public static final TypeDTO DEFAULT = new TypeDTO();

	private final List<CodingDTO> coding = Arrays.asList(new CodingDTO());

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CodingDTO {

		private final String system = "http://rnds.saude.gov.br/fhir/r4/CodeSystem/rnds-openehrdocumenttype-1.0";

		private final String code = "cn2_sumario_alta_internacao_v1.0";
	}
}
