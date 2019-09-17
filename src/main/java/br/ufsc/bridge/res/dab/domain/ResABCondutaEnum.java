package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResABCondutaEnum {
	RETORNO_PARA_CONSULTA_AGENDADA("Retorno para consulta agendada"),
	RETORNO_PARA_CUIDADO_CONTINUADO_PROGRAMADO("Retorno para cuidado continuado/programado"),
	AGENDAMENTO_PARA_GRUPOS("Agendamento para grupos"),
	AGENDAMENTO_PARA_NASF("Agendamento para NASF"),
	ALTA_DO_EPISODIO("Alta do episódio");

	private String descricao;

	public static ResABCondutaEnum getByCodigo(String codigo) {
		for (ResABCondutaEnum value : values()) {
			if (value.getDescricao().equals(codigo)) {
				return value;
			}
		}
		return null;
	}

	public static class ResABCondutaEnumJsonPathConverter implements JsonPathValueConverter<ResABCondutaEnum, String> {

		@Override
		public ResABCondutaEnum convert(String value) {
			return ResABCondutaEnum.getByCodigo(value);
		}
	}
}
