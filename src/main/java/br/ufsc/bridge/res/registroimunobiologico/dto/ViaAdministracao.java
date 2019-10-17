package br.ufsc.bridge.res.registroimunobiologico.dto;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
public enum ViaAdministracao {

	ENDOVENOSA("Endovenosa"),
	INTRADERMICA("Intradérmica"),
	INTRAMUSCULAR("Intramuscular"),
	INTRAMUSCULAR_PROFUNDA("Intramuscular profunda"),
	ORAL("Oral"),
	SUBCUTANEA("Subcutânea");

	private String value;

	public static class JsonConverter implements JsonPathValueConverter<ViaAdministracao, String> {

		@Override
		public ViaAdministracao convert(String value) {
			if(value == null) {
				return null;
			}
			for(ViaAdministracao viaAdministracao: ViaAdministracao.values()) {
				if(viaAdministracao.value.toLowerCase().equals(value.toLowerCase())) {
					return viaAdministracao;
				}
			}
			return null;
		}
	}

}
