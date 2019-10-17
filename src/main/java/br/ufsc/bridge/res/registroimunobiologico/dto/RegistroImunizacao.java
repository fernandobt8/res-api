package br.ufsc.bridge.res.registroimunobiologico.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class RegistroImunizacao implements Serializable {

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Sumário de Imunização')]"
			+ ".data.items[?(@.name.value == 'Situação/condição')]"
			+ ".value.value",
			converter = SituacaoCondicao.JsonConverter.class)
	private List<SituacaoCondicao> situacaoCondicao = new ArrayList<>();

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Gestão de medicação')]"
			+ ".description.items[?(@.name.value == 'Medicamento')]"
			+ ".items[?(@.name.value == 'Imunobiológico')]"
			+ ".value.value")
	private String imunobiologico;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Sumário de Imunização')]"
			+ ".data.items[?(@.name.value == 'Estratégia')]"
			+ ".value.value",
			converter = Estrategia.JsonConverter.class)
	private Estrategia estrategia;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Gestão de medicação')]"
			+ ".description.items[?(@.name.value == 'Medicamento')]"
			+ ".items[?(@.name.value == 'Dose')]"
			+ ".items.value.value")
	private String dose;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Gestão de medicação')]"
			+ ".description.items[?(@.name.value == 'Detalhes da administração')]"
			+ ".items[?(@.name.value == 'Via de administração')]"
			+ ".value.value",
			converter = ViaAdministracao.JsonConverter.class)
	private ViaAdministracao viaAdministracao;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Gestão de medicação')]"
			+ ".description.items[?(@.name.value == 'Detalhes da administração')]"
			+ ".items[?(@.name.value == 'Local de aplicação')]"
			+ ".value.value")
	private String localAplicacao;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Gestão de medicação')]"
			+ ".description.items[?(@.name.value == 'Medicamento')]"
			+ ".items[?(@.name.value == 'Lote')]"
			+ ".value.value")
	private String lote;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Gestão de medicação')]"
			+ ".description.items[?(@.name.value == 'Medicamento')]"
			+ ".items[?(@.name.value == 'Fabricante')]"
			+ ".value.value")
	private String fabricante;

}
