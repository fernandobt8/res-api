package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class DispensacaoMedicamentos {

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Caracterização do atendimento')]")
	private CaracterizacaoAtendimento caracterizacaoAtendimento;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Dispensação')]"
			+ ".items[?(@.name.value == 'Lista de medicamentos')]")
	private List<Medicamento> medicamentos = new ArrayList<>();

	private List<Insumo> insumosSaude = new ArrayList<>();

	private Double peso;

	private Double altura;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Observações')]"
			+ ".items[?(@.name.value == 'Problema /Diagnóstico')]"
			+ ".data.items"
			+ ".value.value")
	private String diagnostico;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Profissional responsável pela prescrição')]"
			+ ".items.data.items")
	private ResponsavelPrescricao profissionalResponsavelPrescricao;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Farmacêutico responsável dispensação')]"
			+ ".items.data.items")
	private ResponsavelDispensacao profissionalResponsavelDispensacao;

}
