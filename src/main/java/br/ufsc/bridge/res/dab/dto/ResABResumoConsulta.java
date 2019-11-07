package br.ufsc.bridge.res.dab.dto;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import br.ufsc.bridge.res.dab.domain.ResABAleitamentoMaternoEnum.ResABAleitamentoMaternoEnumJsonPathConverter;
import br.ufsc.bridge.res.dab.domain.ResABCondutaEnum;
import br.ufsc.bridge.res.dab.domain.ResABCondutaEnum.ResABCondutaEnumJsonPathConverter;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum.ResABTipoAtendimentoEnumJsonPathValueConverter;
import br.ufsc.bridge.res.dab.writer.json.AlergiaReacoesAdversasJsonBuilder;
import br.ufsc.bridge.res.dab.writer.json.AlergiaReacoesAdversasJsonBuilder.AlergiaJsonBuilder;
import br.ufsc.bridge.res.dab.writer.json.CaracterizacaoConsultaABJsonBuilder;
import br.ufsc.bridge.res.dab.writer.json.DadosDesfechoJsonBuilder;
import br.ufsc.bridge.res.dab.writer.json.ProblemaDiagnosticoJsonBuilder;
import br.ufsc.bridge.res.dab.writer.json.ProcedimentoRealizadoJsonBuilder;
import br.ufsc.bridge.res.dab.writer.json.ResumoConsultaABJsonBuilder;
import br.ufsc.bridge.res.dab.writer.xml.ResumoConsultaABBuilder;
import br.ufsc.bridge.res.dab.writer.xml.alergia.AlergiaReacoesAdversasBuilder;
import br.ufsc.bridge.res.dab.writer.xml.alergia.RiscoReacaoAdversaBuilder;
import br.ufsc.bridge.res.dab.writer.xml.caracterizacaoconsulta.CaracterizacaoConsultaABBuilder;
import br.ufsc.bridge.res.dab.writer.xml.desfecho.DadosDesfechoBuilder;
import br.ufsc.bridge.res.dab.writer.xml.problema.ProblemaDiagnosticoAvaliadoBuilder;
import br.ufsc.bridge.res.dab.writer.xml.procedimentospequenascirurgias.ProcedimentosPequenasCirurgiasBuilder;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.ResABXMLParserException;
import br.ufsc.bridge.res.util.ResDocument;
import br.ufsc.bridge.res.util.json.DateJsonPathValueConverter;
import br.ufsc.bridge.res.util.json.JsonPathProperty;
import br.ufsc.bridge.res.util.json.JsonPathProperty.Group;
import br.ufsc.bridge.res.util.json.ResJsonUtils;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class ResABResumoConsulta extends ResDocument implements Serializable, JsonDocument {
	private static final long serialVersionUID = 1L;

	@JsonPathProperty(group = Group.ADMISSAO_DO_PACIENTE,
			value = ".data.items[?(@.name.value == 'Data/hora da admissão')].value.value",
			converter = DateJsonPathValueConverter.class)
	private Date dataAtendimento;

	@JsonPathProperty(group = Group.ADMISSAO_DO_PACIENTE,
			value = ".data.items[?(@.name.value == 'Tipo de atendimento')].value.defining_code.code_string",
			converter = ResABTipoAtendimentoEnumJsonPathValueConverter.class)
	private ResABTipoAtendimentoEnum tipoAtendimento;

	@JsonPathProperty(group = Group.INSTITUICAO,
			value = ".items[?(@.name.value == 'Estabelecimento de saúde')].value.value")
	private String cnes;

	@JsonPathProperty(group = Group.INSTITUICAO,
			value = ".items[?(@.name.value == 'Identificação da equipe de saúde')].value.value")
	private String ine;

	// nao presente mais, comentado para manter historico
	// private ResABTurnoEnum turno;

	@JsonPathProperty(group = Group.ADMISSAO_DO_PACIENTE,
			value = ".data.items[?(@.name.value == 'Profissionais do atendimento')]")
	private List<ResABIdentificacaoProfissional> profissionais = new ArrayList<>();

	@JsonPathProperty(group = Group.OBSERVACOES_MEDICOES,
			value = ".items[?(@.name.value == 'Peso corporal')].data..magnitude")
	private String peso;

	@JsonPathProperty(group = Group.OBSERVACOES_MEDICOES,
			value = ".items[?(@.name.value == 'Altura / comprimento')].data..magnitude")
	private String altura;

	@JsonPathProperty(group = Group.OBSERVACOES_MEDICOES,
			value = ".items[?(@.name.value == 'Perímetro cefálico')].data..magnitude")
	private String perimetroCefalico;

	@JsonPathProperty(group = Group.OBSERVACOES_INFORMACOES_ADICIONAIS,
			value = ".items[?(@.name.value == 'Alimentação da criança menor de 2 anos')].data.events.data.items.value.value",
			converter = ResABAleitamentoMaternoEnumJsonPathConverter.class)
	private ResABAleitamentoMaternoEnum aleitamentoMaterno;

	@JsonPathProperty(group = Group.OBSERVACOES_INFORMACOES_ADICIONAIS,
			value = ".items[?(@.name.value == 'Ciclo menstrual')].data.events.data.items.value.value",
			converter = DateJsonPathValueConverter.class)
	private Date dum;

	@JsonPathProperty(group = Group.OBSERVACOES_INFORMACOES_ADICIONAIS,
			value = ".items[?(@.name.value == 'Gestação')].data.events.data.items.value.value")
	private String idadeGestacional;

	@JsonPathProperty(group = Group.OBSERVACOES_INFORMACOES_ADICIONAIS,
			value = "..items[?(@.name.value == 'Quantidade de gestas prévias')].value.magnitude")
	private String gestasPrevias;

	@JsonPathProperty(group = Group.OBSERVACOES_INFORMACOES_ADICIONAIS,
			value = "..items[?(@.name.value == 'Quantidade de partos')].value.magnitude")
	private String partos;

	@JsonPathProperty("$.content[?(@.name.value == 'Problemas/Diagnósticos avaliados')].items[?(@.name.value == 'Problema Diagnóstico')]")
	private List<ResABProblemaDiagnostico> problemasDiagnosticos = new ArrayList<>();

	@JsonPathProperty("$.content[?(@.name.value == 'Alergias e/ou reações adversas no atendimento')].items[?(@.name.value == 'Risco de reação adversa')]")
	private List<ResABAlergiaReacoes> alergias = new LinkedList<>();

	@JsonPathProperty("$.content[?(@.name.value == 'Procedimento(s) realizado(s) ou solicitado(s)')].items[?(@.name.value == 'Procedimento')]")
	private List<ResABProcedimento> procedimentos = new LinkedList<>();

	// não tem json
	private List<ResABMedicamento> medicamentos = new LinkedList<>();

	@JsonPathProperty("$.content[?(@.name.value == 'Prescrição no atendimento')]..items[?(@.name.value == 'Medicamentos prescritos no atendimento (não estruturado)')].items.value.value")
	private List<String> medicamentosNaoEstruturados = new LinkedList<>();

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Dados do desfecho')]..items[?(@.name.value == 'Motivo do desfecho')].value..code_string",
			converter = ResABCondutaEnumJsonPathConverter.class)
	private List<ResABCondutaEnum> condutas = new LinkedList<>();

	// não tem json
	private List<String> encaminhamentos = new LinkedList<>();

	public static ResABResumoConsulta readJsonBase64(String jsonBase64) {
		ResABResumoConsulta resumoConsulta = ResJsonUtils.readJson(jsonBase64, ResABResumoConsulta.class);
		List<String> descricoes = new ArrayList<>();
		for (String medicamentoDescricao : resumoConsulta.getMedicamentosNaoEstruturados()) {
			if (StringUtils.isNotBlank(medicamentoDescricao)) {
				descricoes.addAll(Arrays.asList(medicamentoDescricao.split(";")));
			}
		}
		resumoConsulta.setMedicamentosNaoEstruturados(descricoes);
		return resumoConsulta;
	}

	public ResABResumoConsulta(String xml) throws ResABXMLParserException {
		XPathFactoryAssist xPathRoot = this.getXPathRoot(xml);
		try {
			XPathFactoryAssist xPathAdmissao = xPathRoot.getXPathAssist("//Admissão_do_paciente/data");
			this.tipoAtendimento = ResABTipoAtendimentoEnum.getById(xPathAdmissao.getString("./Tipo_de_atendimento//code_string"));
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
				this.condutas.add(ResABCondutaEnum.getByCodigo(xPathConduta.getString("./value/defining_code/code_string")));
			}

			// nao presente mais, comentado para manter historico
			// for (XPathFactoryAssist xPathEncaminhamento : xPathRoot.iterable(".//Solicitações_de_encaminhamentos/Encaminhamento")) {
			// this.encaminhamentos.add(xPathEncaminhamento.getString("./value/value"));
			// }

		} catch (XPathExpressionException e) {
			throw new ResABXMLParserException("Erro no parser do XML para o DTO", e);
		}
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
		for (ResABCondutaEnum conduta : this.condutas) {
			desfechoBuilder.conduta(conduta);
		}

		// nao presente mais, comentado para manter historico
//		SolicitacoesEncaminhamentoBuilder<DadosDesfechoBuilder<ResumoConsultaABBuilder>> solicitacaoEncaminhamento = desfechoBuilder.solicitacoesEncaminhamento();
//		for (String encaminhamento : this.encaminhamentos) {
//				solicitacaoEncaminhamento.encaminhamento(encaminhamento);
//		}

		return abBuilder.getXmlContent();
	}

	@Override
	public String getJson() throws IOException {
		ResumoConsultaABJsonBuilder abBuilder = new ResumoConsultaABJsonBuilder().data(this.dataAtendimento);

		//@formatter:off
		CaracterizacaoConsultaABJsonBuilder<ResumoConsultaABJsonBuilder> caracterizacaoConsulta = abBuilder.caracterizacaoConsulta();
		caracterizacaoConsulta
			.tipoAtendimento(this.tipoAtendimento)
			.localizacaoAtribuidaPaciente(this.cnes, this.ine)
			.dataHoraAdmissao(this.dataAtendimento);

		for (ResABIdentificacaoProfissional profissional : this.profissionais) {
			caracterizacaoConsulta.profissional()
				.cns(profissional.getCns())
				.nome(profissional.getNome())
				.cboDescricao("")
				.cbo(profissional.getCbo())
				.responsavel(profissional.isResponsavel());
		}

		abBuilder.medicoesObservacoes()
			.pesoCorporal(this.dataAtendimento, this.peso)
			.altura(this.dataAtendimento, this.altura)
			.perimetroCefalico(this.dataAtendimento, this.perimetroCefalico)
			.cicloMenstrual(this.dataAtendimento, this.dum)
			.gestacao(this.dataAtendimento, this.idadeGestacional)
			.sumarioObstetrico(this.gestasPrevias, this.partos)
			.aleitamentoMaterno(this.dataAtendimento, this.aleitamentoMaterno);

		ProblemaDiagnosticoJsonBuilder<ResumoConsultaABJsonBuilder> diagnosticoAvaliadoBuilder = abBuilder.problemaDiagnostico();
		for (ResABProblemaDiagnostico diagnostico : this.problemasDiagnosticos) {
			diagnosticoAvaliadoBuilder.problema()
				.descricao(diagnostico.getDescricao())
				.tipo(diagnostico.getTipoProblemaDiagnostico())
				.codigo(diagnostico.getCodigo());
		}

		AlergiaReacoesAdversasJsonBuilder<ResumoConsultaABJsonBuilder> alergiasBuilder = abBuilder.alergiaReacao();
		for (ResABAlergiaReacoes alergia : this.alergias) {
			AlergiaJsonBuilder alergiaBuilder = alergiasBuilder.alergia();
			alergiaBuilder
				.agente(alergia.getAgente())
				.categoria(alergia.getCategoria())
				.criticidade(alergia.getCriticidade());

			for (ResABEventoReacao evento : alergia.getEventoReacao()) {
				alergiaBuilder.evento()
					.dataInstalacao(evento.getDataInstalacao())
					.evolucao(evento.getEvolucaoAlergia())
					.manifestacao(evento.getManifestacao());
			}
		}

		ProcedimentoRealizadoJsonBuilder<ResumoConsultaABJsonBuilder> procedimentosBuilder = abBuilder.procedimentoRealizado();
		for (ResABProcedimento procedimento : this.procedimentos) {
			procedimentosBuilder.procedimento()
				.descricao(procedimento.getNome())
				.data(this.dataAtendimento)
				.codigo(procedimento.getCodigo());
		}

		abBuilder.prescricaoAtendimento()
				.medicamentoNaoEstruturado().medicamentos(this.medicamentosNaoEstruturados);

		DadosDesfechoJsonBuilder<ResumoConsultaABJsonBuilder> desfechoBuilder = abBuilder.dadosDesfecho();
		for (ResABCondutaEnum conduta : this.condutas) {
			desfechoBuilder.desfecho(conduta);
		}

		return abBuilder.getJsonString();
	}
}
