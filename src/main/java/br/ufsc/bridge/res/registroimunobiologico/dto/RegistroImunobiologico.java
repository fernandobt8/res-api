package br.ufsc.bridge.res.registroimunobiologico.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.dab.dto.CaracterizacaoAtendimento;
import br.ufsc.bridge.res.util.ResDocument;
import br.ufsc.bridge.res.util.json.DateJsonPathValueConverter;
import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class RegistroImunobiologico extends ResDocument implements Serializable {

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Caracterização do atendimento')]")
	private CaracterizacaoAtendimento caracterizacaoAtendimento;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Registro da imunização')]")
	private List<RegistroImunizacao> registroImunizacao = new ArrayList<>();

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Registro da imunização')]"
			+ ".items[?(@.name.value == 'Sumário de Imunização')]"
			+ ".data.items[?(@.name.value == 'Vacina específica')]"
			+ ".items.value.value",
			converter = DateJsonPathValueConverter.class)
	private Date dataAdministracao;

	@Override
	public Date getDataAtendimento() {
		return this.caracterizacaoAtendimento.getHoraAtendimento();
	}
}
