package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.json.DateJsonPathValueConverter;
import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class CaracterizacaoAtendimento implements Serializable {

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Serviço')]"
			+ ".description.items[?(@.name.value == 'Organização')]"
			+ ".items[?(@.name.value == 'Estabelecimento de saúde')]"
			+ ".value.value")
	private String estabelecimentoSaude;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Serviço')]"
			+ ".description.items[?(@.name.value == 'Data e hora da dispensação')]"
			+ ".value.value", converter = DateJsonPathValueConverter.class)
	private Date horaDispensacao;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Origem da informação')]"
			+ ".data.items"
			+ ".value.value", converter = OrigemRegistroDispensacao.JsonConverter.class)
	private OrigemRegistroDispensacao origemRegistro;
}
