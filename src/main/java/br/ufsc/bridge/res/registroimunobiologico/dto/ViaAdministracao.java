package br.ufsc.bridge.res.registroimunobiologico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
@Getter
public enum ViaAdministracao {

	ENDOVENOSA(1L, "Endovenosa"),
	INTRADERMICA(2L, "Intradérmica"),
	INTRAMUSCULAR(3L, "Intramuscular"),
	INTRAMUSCULAR_PROFUNDA(4L, "Intramuscular profunda"),
	ORAL(5L, "Oral"),
	SUBCUTANEA(6L, "Subcutânea");

	private Long id;

	private String value;

	public static ViaAdministracao get(Long id) {
		for (ViaAdministracao viaAdministracao : values()) {
			if (viaAdministracao.id.equals(id)) {
				return viaAdministracao;
			}
		}
		return null;
	}

	public static class JsonConverter implements JsonPathValueConverter<ViaAdministracao, String> {

		@Override
		public ViaAdministracao convert(String value) {
			if (value == null) {
				return null;
			}
			for (ViaAdministracao viaAdministracao : ViaAdministracao.values()) {
				if (viaAdministracao.value.toLowerCase().equals(value.toLowerCase())) {
					return viaAdministracao;
				}
			}
			return null;
		}
	}

}
