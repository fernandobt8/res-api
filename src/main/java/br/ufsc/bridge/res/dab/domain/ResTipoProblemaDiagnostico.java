package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@Getter
@AllArgsConstructor
public enum ResTipoProblemaDiagnostico {

	CID10("CID10"),
	CID10_1998("CID-10_1998.v1.0.0"),
	CID10_201812("CID-10_201812.v1.0.0"),
	CIAP("CIAP2");

	private String tipo;

	public static ResTipoProblemaDiagnostico getByTipo(String codigo) {
		for (ResTipoProblemaDiagnostico value : values()) {
			if (value.getTipo().equals(codigo)) {
				return value;
			}
		}
		return null;
	}

	public static class ResTipoProblemaDiagnosticoJsonPathConveter implements JsonPathValueConverter<ResTipoProblemaDiagnostico, String> {

		@Override
		public ResTipoProblemaDiagnostico convert(String value) {
			return ResTipoProblemaDiagnostico.getByTipo(value);
		}
	}
}
