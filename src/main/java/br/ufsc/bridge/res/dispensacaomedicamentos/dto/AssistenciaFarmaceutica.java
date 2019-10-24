package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
public enum AssistenciaFarmaceutica {

	BASICO("Básico"),
	ESTRATEGICO("Estratégico"),
	ESPECIALIZADO("Especializado");

	private String value;

	public static class JsonConverter implements JsonPathValueConverter<AssistenciaFarmaceutica, String> {

		@Override
		public AssistenciaFarmaceutica convert(String value) {
			if (value == null) {
				return null;
			}
			for (AssistenciaFarmaceutica estrategia : AssistenciaFarmaceutica.values()) {
				if (estrategia.value.toLowerCase().equals(value.toLowerCase())) {
					return estrategia;
				}
			}
			return null;
		}
	}

}
