package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
public enum Sexo {

	MASCULINO("Masculino"),
	FEMININO("Feminino"),
	INDETERMINADO("Indeterminado");

	private String value;

	public static class JsonConverter implements JsonPathValueConverter<Sexo, String> {

		@Override
		public Sexo convert(String value) {
			if(value == null) {
				return null;
			}
			for(Sexo sexo: Sexo.values()) {
				if(sexo.value.toLowerCase().equals(value.toLowerCase())) {
					return sexo;
				}
			}
			return null;
		}
	}

}
