package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class CaracterizacaoAtendimento {

	private String estabelecimentoSaude;

	private Date horaDispensacao;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Origem da informação')]"
			+ ".data.items"
			+ ".value.value", converter = OrigemRegistroDispensacao.JsonConverter.class)
	private OrigemRegistroDispensacao origemRegistro;
}
