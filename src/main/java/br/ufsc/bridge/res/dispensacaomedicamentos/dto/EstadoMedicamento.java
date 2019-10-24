package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
public enum EstadoMedicamento {

	ATIVO("Ativo"),
	DESCONTINUADO("Descontinuado"),
	NUNCA_ATIVO("Nunca ativo"),
	TRATAMENTO_COMPLETO("Tratamento completo"),
	SUBSTITUIDO("Substituído");

	private String value;

	public static class JsonConverter implements JsonPathValueConverter<EstadoMedicamento, String> {

		@Override
		public EstadoMedicamento convert(String value) {
			if (value == null) {
				return null;
			}
			for (EstadoMedicamento estadoMedicamento : EstadoMedicamento.values()) {
				if (estadoMedicamento.value.toLowerCase().equals(value.toLowerCase())) {
					return estadoMedicamento;
				}
			}
			return null;
		}
	}
}
