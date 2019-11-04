package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.ResDocument;
import br.ufsc.bridge.res.util.json.DoubleJsonPathValueConverter;
import br.ufsc.bridge.res.util.json.JsonPathProperty;
import br.ufsc.bridge.res.util.json.ResJsonUtils;

@Getter
@Setter
@NoArgsConstructor
public class DispensacaoMedicamentos extends ResDocument {

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Caracterização do atendimento')]")
	private CaracterizacaoAtendimento caracterizacaoAtendimento;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Dispensação')]"
			+ ".items[?(@.name.value == 'Lista de medicamentos')]")
	private List<Medicamento> medicamentos = new ArrayList<>();

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Dispensação')]"
			+ ".items[?(@.name.value == 'Lista de medicamentos')]")
	private List<Insumo> insumosSaude = new ArrayList<>();

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Observações')]"
			+ ".items[?(@.name.value == 'Peso corporal')]"
			+ ".data.events"
			+ ".data.items"
			+ ".value.magnitude", converter = DoubleJsonPathValueConverter.class)
	private double peso;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Observações')]"
			+ ".items[?(@.name.value == 'Altura / comprimento')]"
			+ ".data.events"
			+ ".data.items"
			+ ".value.magnitude", converter = DoubleJsonPathValueConverter.class)
	private double altura;

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

	@Override
	public Date getDataAtendimento() {
		return this.caracterizacaoAtendimento.getHoraDispensacao();
	}

	public static DispensacaoMedicamentos readJsonBase64(String jsonBase64) {
		DispensacaoMedicamentos registroImunobiologico = ResJsonUtils.readJson(jsonBase64, DispensacaoMedicamentos.class);
		return registroImunobiologico;
	}
}
