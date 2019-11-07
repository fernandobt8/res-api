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
public class Insumo implements Serializable {

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Produto')]"
			+ ".items[?(@.name.value == 'Lote do medicamento')]"
			+ ".value.value")
	private String lote;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Produto')]"
			+ ".items[?(@.name.value == 'Fabricante do medicamento')]"
			+ ".value.value")
	private String fabricante;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Produto')]"
			+ ".items[?(@.name.value == 'Data de validade do medicamento')]"
			+ ".value.value", converter = DateJsonPathValueConverter.class)
	private Date validade;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Valor unitário')]"
			+ ".value.value")
	private String valorUnitario;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Programa de saúde')]"
			+ ".value.value")
	private String programaSaude;

}
