package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.json.DateJsonPathValueConverter;
import br.ufsc.bridge.res.util.json.DoubleJsonPathValueConverter;
import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class Medicamento implements Serializable {

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Componente Assistência Farmacêutica')]"
			+ ".value.value", converter = AssistenciaFarmaceutica.JsonConverter.class)
	private AssistenciaFarmaceutica assistenciaFarmaceutica;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Item de medicação')]"
			+ ".items[?(@.name.value == 'Medicamento')]"
			+ ".value.value")
	private String medicamento;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Item de medicação')]"
			+ ".items[?(@.name.value == 'Dose estruturada')]"
			+ ".items[?(@.name.value == 'Dose')]"
			+ ".items.value.magnitude", converter = DoubleJsonPathValueConverter.class)
	private double quantidadeUnidadeFarmaceutica;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Item de medicação')]"
			+ ".items[?(@.name.value == 'Frequência de uso do medicamento')]"
			+ ".value.value")
	private String frequenciaUso;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Item de medicação')]"
			+ ".items[?(@.name.value == 'Dose estruturada')]"
			+ ".items[?(@.name.value == 'Duração de uso do medicamento')]"
			+ ".value.value")
	private String duracaoUso;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Item de medicação')]"
			+ ".items[?(@.name.value == 'Detalhes do processo medicação')]"
			+ ".items.value.value", converter = EstadoMedicamento.JsonConverter.class)
	private EstadoMedicamento estado;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Produto')]"
			+ ".items[?(@.name.value == 'Data de validade do medicamento')]"
			+ ".value.value", converter = DateJsonPathValueConverter.class)
	private Date dataValidade;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Produto')]"
			+ ".items[?(@.name.value == 'Lote do medicamento')]"
			+ ".value.value")
	private String lote;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Produto')]"
			+ ".items[?(@.name.value == 'Fabricante do medicamento')]"
			+ ".value.value")
	private String fabricamente;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Programa de saúde')]"
			+ ".value.value")
	private String programaSaude;

	@JsonPathProperty(value = "$.items.description.items[?(@.name.value == 'Valor unitário')]"
			+ ".value.value")
	private String valorUnitario;
}
