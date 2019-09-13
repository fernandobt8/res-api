package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResABAleitamentoMaternoEnum {
	EXCLUSIVO("Exclusivo"),
	PREDOMINANTE("Predominante"),
	COMPLEMENTADO("Complementado"),
	INEXISTENTE("Inexistente");

	private String descricao;

}
