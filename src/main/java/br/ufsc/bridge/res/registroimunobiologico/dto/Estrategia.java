package br.ufsc.bridge.res.registroimunobiologico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
@Getter
public enum Estrategia {

	ROTINA(1L, "Rotina"),
	ESPECIAL(2L, "Especial"),
	BLOQUEIO(3L, "Bloqueio"),
	INTENSIFICACAO(4L, "Intensificação"),
	CAMPANHA(5L, "Campanha"),
	SOROTERAPIA(7L, "Soroterapia"),
	MULTIVACINACAO(10L, "Multivacinação"),
	;

	private Long id;

	private String value;

	public static Estrategia get(Long id) {
		for (Estrategia estrategia : values()) {
			if (estrategia.id.equals(id)) {
				return estrategia;
			}
		}
		return null;
	}

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
