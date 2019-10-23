package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
@Getter
public enum OrigemRegistroDispensacao {

	MUNICIPIO_SISTEMA_NACIONAL("Município com sistema nacional gerenciador da assistência farmacêutica"),
	MUNICIPIO_SISTEM_PROPRIO("Município com sistema próprio"),
	ESTADO_SISTEMA_NACIONAL("Estado com sistema nacional gerenciador da assistência farmacêutica"),
	ESTADO_SISTEMA_PROPRIO("Estado com sistema próprio"),
	FARMACIA_POPULAR("Farmácia Popular");

	private String value;

	public static class JsonConverter implements JsonPathValueConverter<OrigemRegistroDispensacao, String> {

		@Override
		public OrigemRegistroDispensacao convert(String value) {
			if (value == null) {
				return null;
			}
			for (OrigemRegistroDispensacao registro : OrigemRegistroDispensacao.values()) {
				if (registro.value.toLowerCase().equals(value.toLowerCase())) {
					return registro;
				}
			}
			return null;
		}
	}

}
