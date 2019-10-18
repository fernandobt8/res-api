package br.ufsc.bridge.res.registroimunobiologico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
@Getter
public enum Estrategia {

	ROTINA("Rotina"),
	ESPECIAL("Especial"),
	BLOQUEIO("Bloqueio"),
	INTENSIFICACAO("Intensificação"),
	CAMPANHA("Campanha"),
	SOROTERAPIA("Soroterapia"),
	MULTIVACINACAO("Multivacinação"),
	;

	private String value;

	public static class JsonConverter implements JsonPathValueConverter<Estrategia, String> {

		@Override
		public Estrategia convert(String value) {
			if (value == null) {
				return null;
			}
			for (Estrategia estrategia : Estrategia.values()) {
				if (estrategia.value.toLowerCase().equals(value.toLowerCase())) {
					return estrategia;
				}
			}
			return null;
		}
	}

}
