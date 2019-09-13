package br.ufsc.bridge.res.dab.domain;

public class ResABAleitamentoMaternoEnumJsonPathConverter implements JsonPathValueConverter<ResABAleitamentoMaternoEnum>{

	@Override
	public ResABAleitamentoMaternoEnum convert(String jsonValue) {
		for (ResABAleitamentoMaternoEnum value : ResABAleitamentoMaternoEnum.values()) {
			if (value.getDescricao().equalsIgnoreCase(jsonValue)) {
				return value;
			}
		}
		return null;
	}

}
