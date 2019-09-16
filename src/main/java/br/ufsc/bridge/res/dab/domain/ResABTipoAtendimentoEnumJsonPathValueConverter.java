package br.ufsc.bridge.res.dab.domain;

public class ResABTipoAtendimentoEnumJsonPathValueConverter implements JsonPathValueConverter<ResABTipoAtendimentoEnum> {

	@Override
	public ResABTipoAtendimentoEnum convert(String codigo) {
		for (ResABTipoAtendimentoEnum value : ResABTipoAtendimentoEnum.values()) {
			if (value.getCodigo().equals(codigo)) {
				return value;
			}
		}
		return null;
	}

}
