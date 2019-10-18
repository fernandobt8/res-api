package br.ufsc.bridge.res.registroimunobiologico.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.dab.dto.CaracterizacaoAtendimento;
import br.ufsc.bridge.res.dab.writer.json.RegistroImunizacaoJsonBuilder;
import br.ufsc.bridge.res.dab.writer.json.RegistroImunobiologicoJsonBuilder;
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

	public String getJson() {
		CaracterizacaoAtendimento caracterizacaoAtendimento = this.getCaracterizacaoAtendimento();

		//@formatter:off
		RegistroImunobiologicoJsonBuilder builder = new RegistroImunobiologicoJsonBuilder()
					.caracterizacaoAtendimento()
					.horaAtendimento(caracterizacaoAtendimento.getHoraAtendimento())
					.localAtendimento(caracterizacaoAtendimento.getLocalAtendimento())
					.cnes((caracterizacaoAtendimento.getCnes()))
					.ine(caracterizacaoAtendimento.getIne())
					.cnsProfissionalResponsavel(caracterizacaoAtendimento.getCnsProfissional())
					.nomeProfissionalResponsavel(caracterizacaoAtendimento.getNomeProfissional())
					.cbo(caracterizacaoAtendimento.getCbo())
				.close();

		for (RegistroImunizacao registro : this.getRegistroImunizacao()) {

			RegistroImunizacaoJsonBuilder<RegistroImunobiologicoJsonBuilder> registroBuilder = builder.registroImunizacao();
//			for (SituacaoCondicao situacao : registro.getSituacaoCondicao()) {
//				registroBuilder.situacaoCondicao()
//						.situacao(situacao);
//			}

			registroBuilder
//					.situacao(registro.getSituacaoCondicao())
					.imunobiologico(registro.getImunobiologico())
					.estrategia(registro.getEstrategia())
					.dose(registro.getDose())
					.viaAdministracao(registro.getViaAdministracao())
					.localAplicacao(registro.getLocalAplicacao())
					.lote(registro.getLote())
					.fabricante(registro.getFabricante());
		}

		return builder.getJsonString();
	}
}
