package br.ufsc.bridge.res.dab.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.dab.domain.ResABAleitamentoMaternoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.write.builder.ResumoConsultaABBuilder;
import br.ufsc.bridge.res.dab.write.builder.alergia.AlergiaReacoesAdversasBuilder;
import br.ufsc.bridge.res.dab.write.builder.alergia.RiscoReacaoAdversaBuilder;
import br.ufsc.bridge.res.dab.write.builder.caracterizacaoconsulta.CaracterizacaoConsultaABBuilder;
import br.ufsc.bridge.res.dab.write.builder.desfecho.DadosDesfechoBuilder;
import br.ufsc.bridge.res.dab.write.builder.problema.ProblemaDiagnosticoAvaliadoBuilder;
import br.ufsc.bridge.res.dab.write.builder.procedimentospequenascirurgias.ProcedimentosPequenasCirurgiasBuilder;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.ResABXMLParserException;
import br.ufsc.bridge.res.util.ResDocument;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class ResABResumoConsulta extends ResDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date dataAtendimento;

	private ResABTipoAtendimentoEnum tipoAtendimento;
	private String cnes;
	private String ine;
	// nao presente mais, comentado para manter historico
	// private ResABTurnoEnum turno;
	private List<ResABIdentificacaoProfissional> profissionais = new ArrayList<>();

	private String peso;
	private String altura;
	private String perimetroCefalico;
	private ResABAleitamentoMaternoEnum aleitamentoMaterno;

	private Date dum;
	private String idadeGestacional;
	private String gestasPrevias;
	private String partos;

	private List<ResABProblemaDiagnostico> problemasDiagnosticos = new ArrayList<>();

	private List<ResABAlergiaReacoes> alergias = new LinkedList<>();

	private List<ResABProcedimento> procedimentos = new LinkedList<>();

	private List<ResABMedicamento> medicamentos = new LinkedList<>();

	private List<String> medicamentosNaoEstruturados = new LinkedList<>();

	private List<String> condutas = new LinkedList<>();

	private List<String> encaminhamentos = new LinkedList<>();

	public ResABResumoConsulta(String xml) throws ResABXMLParserException {
		XPathFactoryAssist xPathRoot = this.getXPathRoot(xml);
		try {
			XPathFactoryAssist xPathAdmissao = xPathRoot.getXPathAssist("//Admissão_do_paciente/data");
			this.tipoAtendimento = ResABTipoAtendimentoEnum.getByCodigo(xPathAdmissao.getString("./Tipo_de_atendimento//code_string"));
			this.cnes = xPathAdmissao.getString("./Localização_atribuída_ao_paciente//Estabelecimento_de_saúde//value/value");
			this.ine = xPathAdmissao.getString("./Localização_atribuída_ao_paciente//Identificação_da_equipe_de_saúde/value/value");
			this.dataAtendimento = RDateUtil.isoEHRToDate(xPathAdmissao.getString("./Data_fslash_hora_da_admissão/value/value"));

			// nao presente mais, comentado para manter historico
			// this.turno = ResABTurnoEnum.getByCodigo(xPathAdmissao.getString("./Turno_de_atendimento//code_string"));

			for (XPathFactoryAssist xPathprofissional : xPathAdmissao.iterable(".//Profissionais_do_atendimento")) {
				this.profissionais.add(new ResABIdentificacaoProfissional(xPathprofissional));
			}

			XPathFactoryAssist xPathMedicoes = xPathRoot.getXPathAssist("//Observações/Medições");
			this.peso = xPathMedicoes.getString("./Peso_corporal//Peso/value/magnitude");
			this.altura = xPathMedicoes.getString("./Altura__fslash__comprimento//Altura/value/magnitude");
			this.perimetroCefalico = xPathMedicoes.getString("./Perímetro_cefálico//Perímetro_cefálico/value/magnitude");

			XPathFactoryAssist xPathInfoAdicionais = xPathMedicoes.getXPathAssist(".//Informações_adicionais");
			this.dum = RDateUtil.isoEHRToDate(xPathInfoAdicionais.getString("./Ciclo_menstrual//DUM__openBrkt_Data_da_última_menstruação_closeBrkt_/value/value"));
			this.idadeGestacional = xPathInfoAdicionais.getString("./Gestação//Idade_gestacional/value/value");

			XPathFactoryAssist xPathSumarioObstetrico = xPathInfoAdicionais.getXPathAssist("./Sumário_obstétrico/data");
			this.gestasPrevias = xPathSumarioObstetrico.getString("./Quantidade_de_gestas_prévias/value/magnitude");
			this.partos = xPathSumarioObstetrico.getString("./Quantidade_de_partos/value/magnitude");

			this.aleitamentoMaterno = ResABAleitamentoMaternoEnum
					.getByDescricao(xPathInfoAdicionais.getString("./Alimentação_da_criança_menor_de_2_anos/data/Qualquer_evento_as_Point_Event/data//value/value"));

			XPathFactoryAssist xPathProbleam = xPathRoot.getXPathAssist("//Problemas_fslash_Diagnósticos_avaliados");
			for (XPathFactoryAssist xPathDiagnostico : xPathProbleam.iterable(".//Problema_Diagnóstico")) {
				this.problemasDiagnosticos.add(new ResABProblemaDiagnostico(xPathDiagnostico));
			}

			XPathFactoryAssist xPathAlergias = xPathRoot.getXPathAssist("//Alergias_e_fslash_ou_reações_adversas_no_atendimento");
			for (XPathFactoryAssist xPathAlergia : xPathAlergias.iterable(".//Risco_de_reação_adversa")) {
				this.alergias.add(new ResABAlergiaReacoes(xPathAlergia));
			}

			XPathFactoryAssist xPathProcedimentos = xPathRoot
					.getXPathAssist("//Procedimento_openBrkt_s_closeBrkt__realizado_openBrkt_s_closeBrkt__ou_solicitado_openBrkt_s_closeBrkt_");
			for (XPathFactoryAssist xPathProcedimento : xPathProcedimentos.iterable(".//Procedimento")) {
				this.procedimentos.add(new ResABProcedimento(xPathProcedimento));
			}

			String xPathMedicamentos = xPathRoot.getString("//Prescrição_no_atendimento//Descrição_da_prescrição/value/value");
			if (xPathMedicamentos != null) {
				for (String medicamento : xPathMedicamentos.split(";")) {
					if (StringUtils.isNotBlank(medicamento)) {
						this.medicamentosNaoEstruturados.add(medicamento);
					}
				}
			}

			// nao presente mais, comentado para manter historico
			// XPathFactoryAssist xPathMedicamentos = xPathRoot.getXPathAssist("//Prescrição_no_atendimento");
			// for (XPathFactoryAssist xPathMedicamento :
			// xPathMedicamentos.iterable(".//Linha_de_Medicação/data/Lista_de_medicamentos_no_atendimento__openBrkt_estruturada_closeBrkt_")) {
			// this.medicamentos.add(new ResABMedicamento(xPathMedicamento));
			// }

			XPathFactoryAssist xPathDados = xPathRoot.getXPathAssist("//Dados_do_desfecho/Desfecho__fslash__alta_do_contato_assistencial/data");
			for (XPathFactoryAssist xPathConduta : xPathDados.iterable(".//Motivo_do_desfecho")) {
				this.condutas.add(xPathConduta.getString("./value/value"));
			}

			// nao presente mais, comentado para manter historico
			// for (XPathFactoryAssist xPathEncaminhamento : xPathRoot.iterable(".//Solicitações_de_encaminhamentos/Encaminhamento")) {
			// this.encaminhamentos.add(xPathEncaminhamento.getString("./value/value"));
			// }

		} catch (XPathExpressionException e) {
			throw new ResABXMLParserException("Erro no parser do XML para o DTO", e);
		}
	}

	public ResABResumoConsulta(String json, boolean a) throws ResABXMLParserException {
		DocumentContext parse = JsonPath.parse(json);
		Object teste = parse.read("$.content[?(@.name.value == 'Caracterização do atendimento')].items.data.items[?(@.name.value == 'Tipo de atendimento')].value..code_string");
		teste = parse.read("$..items[?(@.name.value == 'Tipo de atendimento')].value..code_string");
		System.out.println(teste);
		teste = parse.read("$..items[?(@.name.value == 'Tipo de atendimento')]");

		JSONArray aa = (JSONArray) teste;

		String jsonString = aa.toJSONString();
		System.out.println(jsonString);

		parse.set("$..items[?(@.name.value == 'Tipo de atendimento')].value..code_string", "8888");

		parse.add("$.content[?(@.name.value == 'Caracterização do atendimento')].items.data.items", "asdf");

		//		parse.delete("$..items[?(@.name.value == 'Tipo de atendimento')]");

		System.out.println(parse.jsonString());
	}

	public String getXml() {
		ResumoConsultaABBuilder abBuilder = new ResumoConsultaABBuilder().data(this.dataAtendimento);

		//@formatter:off
		CaracterizacaoConsultaABBuilder<ResumoConsultaABBuilder> caracterizacaoConsulta = abBuilder.caracterizacaoConsulta();
		caracterizacaoConsulta
			.tipoAtendimento(this.tipoAtendimento)
			.localizacaoAtribuidaPaciente(this.cnes, this.ine);

		for (ResABIdentificacaoProfissional profissional : this.profissionais) {
			caracterizacaoConsulta.identificacaoProfissional()
				.cns(profissional.getCns())
				.nome(profissional.getNome())
				.descricaoCbo("")
				.cbo(profissional.getCbo())
				.responsavel(profissional.isResponsavel());
		}

		caracterizacaoConsulta
				.dataHoraAdmissao(this.dataAtendimento)
		.close()
		.medicoesObservacoes()
			.avaliacaoAntropometrica()
				.pesoCorporal(this.dataAtendimento, this.peso)
				.altura(this.dataAtendimento, this.altura)
				.perimetroCefalico(this.dataAtendimento, this.perimetroCefalico)
			.close();
		// XXX: aguardar postal com exemplos (dum, ig, gestas prévias, partos e aleitamento materno)
//			.gestante()
//				.cicloMenstrual(this.dataAtendimento, this.dum)
//				.gestacao(this.dataAtendimento, this.idadeGestacional)
//				.sumarioObstetrico(this.gestasPrevias, this.partos)
//			.close()
//			.crianca()
//				.aleitamentoMaterno(this.dataAtendimento, this.aleitamentoMaterno);

		ProblemaDiagnosticoAvaliadoBuilder<ResumoConsultaABBuilder> diagnosticoAvaliadoBuilder = abBuilder.problemaDiagnostico();
		for (ResABProblemaDiagnostico diagnostico : this.problemasDiagnosticos) {
			diagnosticoAvaliadoBuilder.problema()
				.descricao(diagnostico.getDescricao())
				.tipo(diagnostico.getTipoProblemaDiagnostico())
				.codigo(diagnostico.getCodigo());
		}

		AlergiaReacoesAdversasBuilder<ResumoConsultaABBuilder> alergiasBuilder = abBuilder.alergiaReacaoAdversa();
		for (ResABAlergiaReacoes alergia : this.alergias) {
			RiscoReacaoAdversaBuilder<AlergiaReacoesAdversasBuilder<ResumoConsultaABBuilder>> alergiaBuilder = alergiasBuilder.alergia();
			alergiaBuilder
				.agente(alergia.getAgente())
				.categoria(alergia.getCategoria())
				.gravidade(alergia.getCriticidade());

			for (ResABEventoReacao evento : alergia.getEventoReacao()) {
				alergiaBuilder.eventoReacao()
					.dataInstalacao(evento.getDataInstalacao())
					//.evolucaoAlergia(evento.getEvolucaoAlergia())
					.manifestacao(evento.getManifestacao());
			}
		}

		ProcedimentosPequenasCirurgiasBuilder<ResumoConsultaABBuilder> procedimentosBuilder = abBuilder.procedimentosPequenasCirurgias();
		for (ResABProcedimento procedimento : this.procedimentos) {
			procedimentosBuilder.procedimento()
				.nome(procedimento.getNome())
				.data(this.dataAtendimento)
				.codigo(procedimento.getCodigo());
				//.resultadoObservacoes(procedimento.getResultadoObservacoes());
		}

		// nao presente mais, comentado para manter historico
//		ListaMedicamentosBuilder<ResumoConsultaABBuilder> medicamentosBuilder = abBuilder.listaMedicamentos();
//		for (ResABMedicamento medicamento : this.medicamentos) {
//			medicamentosBuilder.itemMedicacao()
//				.medicamento(medicamento.getNomeMedicamento(), medicamento.getCodigoMedicamentoCatmat())
//				.formaFarmaceutica(medicamento.getDescricaoFormaFarmaceutica(), medicamento.getCodigoFormaFarmaceutica())
//				.viaAdministracao(medicamento.getDescricaoViaAdministracao(), medicamento.getCodigoViaAdministracao())
//				.dose(medicamento.getDescricaoDose())
//				.doseEstruturada(medicamento.getDuracaoTratamento())
//				.estadoMedicamento(medicamento.getEstadoMedicamento());
//		}

		abBuilder.listaMedicamentosNaoEstruturados().itemMedicacaoNaoEstruturada(this.medicamentosNaoEstruturados);

		DadosDesfechoBuilder<ResumoConsultaABBuilder> desfechoBuilder = abBuilder.dadosDesfecho();
		for (String conduta : this.condutas) {
			desfechoBuilder.conduta(conduta);
		}

		// nao presente mais, comentado para manter historico
//		SolicitacoesEncaminhamentoBuilder<DadosDesfechoBuilder<ResumoConsultaABBuilder>> solicitacaoEncaminhamento = desfechoBuilder.solicitacoesEncaminhamento();
//		for (String encaminhamento : this.encaminhamentos) {
//				solicitacaoEncaminhamento.encaminhamento(encaminhamento);
//		}

		return abBuilder.getXmlContent();
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	//	this.turno = ResABTurnoEnum.getTurnoByHora(dataAtendimento);
	}
}
