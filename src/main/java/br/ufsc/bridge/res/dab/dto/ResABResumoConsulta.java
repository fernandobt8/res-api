package br.ufsc.bridge.res.dab.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.ufsc.bridge.res.dab.domain.ResABCondutaEnum;
import br.ufsc.bridge.res.dab.domain.ResABGravidadeEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTurnoEnum;
import br.ufsc.bridge.res.dab.exception.ResABXMLParserException;
import br.ufsc.bridge.res.dab.write.builder.ResumoConsultaABBuilder;
import br.ufsc.bridge.res.dab.write.builder.alergia.AlergiaReacoesAdversasBuilder;
import br.ufsc.bridge.res.dab.write.builder.alergia.RiscoReacaoAdversaBuilder;
import br.ufsc.bridge.res.dab.write.builder.caracterizacaoconsulta.CaracterizacaoConsultaABBuilder;
import br.ufsc.bridge.res.dab.write.builder.desfecho.DadosDesfechoBuilder;
import br.ufsc.bridge.res.dab.write.builder.desfecho.SolicitacoesEncaminhamentoBuilder;
import br.ufsc.bridge.res.dab.write.builder.listamedicamentos.ListaMedicamentosBuilder;
import br.ufsc.bridge.res.dab.write.builder.problema.ProblemaDiagnosticoAvaliadoBuilder;
import br.ufsc.bridge.res.dab.write.builder.procedimentospequenascirurgias.ProcedimentosPequenasCirurgiasBuilder;
import br.ufsc.bridge.res.util.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
public class ResABResumoConsulta {

	private Date dataAtendimento;

	private ResABTipoAtendimentoEnum tipoAtendimento;
	private String cnes;
	private String ine;
	private Date dataAdmissao;
	private ResABTurnoEnum turno;
	private List<ResABIdentificacaoProfissional> profissionais = new ArrayList<>();

	private String peso;
	private String altura;

	private Date dum;
	private String idadeGestacional;
	private String gestasPrevias;
	private String partos;

	private List<ResABProblemaDiagnostico> problemasDiagnosticos = new ArrayList<>();

	private List<ResABAlergiaReacoes> alergias = new ArrayList<>();

	private List<ResABProcedimento> procedimentos = new ArrayList<>();

	private List<ResABMedicamento> medicamentos = new ArrayList<>();

	private List<ResABCondutaEnum> condutas = new ArrayList<>();

	private List<String> encaminhamentos = new ArrayList<>();

	public ResABResumoConsulta(String xml) throws ResABXMLParserException {
		Document document;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(IOUtils.toInputStream(xml));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new ResABXMLParserException("Erro no parser do XML para document", e);
		}

		XPathFactoryAssist xPathRoot = new XPathFactoryAssist(document);
		try {
			XPathFactoryAssist xPathAdmissao = xPathRoot.getXPathAssist("//Admissão_do_paciente/data");
			this.tipoAtendimento = ResABTipoAtendimentoEnum.getByCodigo(xPathAdmissao.getString("./Tipo_de_atendimento//code_string"));
			this.cnes = xPathAdmissao.getString("./Localização_atribuída_ao_paciente//value/value");
			this.ine = xPathAdmissao.getString("./Identificação_da_equipe_de_saúde/value/value");
			this.dataAtendimento = xPathAdmissao.getDate("./Data_fslash_hora_da_admissão/value/value");
			this.turno = ResABTurnoEnum.getByCodigo(xPathAdmissao.getString("./Turno_de_atendimento//code_string"));

			for (XPathFactoryAssist xPathprofissional : xPathAdmissao.iterable(".//Identificação_do_profissional")) {
				ResABIdentificacaoProfissional profissional = new ResABIdentificacaoProfissional();
				profissional.setCns(xPathprofissional.getString("./CNS/value/value"));
				profissional.setCbo(xPathprofissional.getString("./CBO/value/value"));
				profissional.setResponsavel(xPathprofissional.getBoolean("./É_o_responsável_pelo_atendimento_quest_/value/value"));
				this.profissionais.add(profissional);
			}

			XPathFactoryAssist xPathMedicoes = xPathRoot.getXPathAssist("//Medições_e_observações");
			this.peso = xPathMedicoes.getString("./Avaliação_antropométrica/Peso_corporal//Peso/value/magnitude");
			this.altura = xPathMedicoes.getString("./Avaliação_antropométrica/Altura__fslash__comprimento//Altura__fslash__comprimento/value/magnitude");

			XPathFactoryAssist xPathGestante = xPathMedicoes.getXPathAssist(".//Gestante");
			this.dum = xPathGestante.getDate("./Ciclo_menstrual//DUM__openBrkt_Data_da_última_menstruação_closeBrkt_/value/value");
			this.idadeGestacional = xPathGestante.getString("./Gestação//Idade_gestacional/value/value");

			XPathFactoryAssist xPathSumarioObstetrico = xPathGestante.getXPathAssist(".//Sumário_obstétrico/data");
			this.gestasPrevias = xPathSumarioObstetrico.getString("./Gestas_prévias/value/magnitude");
			this.partos = xPathSumarioObstetrico.getString("./Partos/value/magnitude");

			XPathFactoryAssist xPathProbleam = xPathRoot.getXPathAssist("//Problemas__fslash__diagnósticos_avaliados");
			for (XPathFactoryAssist xPathDiagnostico : xPathProbleam.iterable(".//Problema__fslash_Diagnóstico")) {
				ResABProblemaDiagnostico diagnostico = new ResABProblemaDiagnostico();
				diagnostico.setCodigo(xPathDiagnostico.getString("./data/Problema__fslash__Diagnóstico/value/defining_code/code_string"));
				diagnostico.setDescricao(xPathDiagnostico.getString("./data/Problema__fslash__Diagnóstico/value/value"));
				diagnostico.setTipo(xPathDiagnostico.getString("./data/Problema__fslash__Diagnóstico/value/defining_code/terminology_id/value"));
				this.problemasDiagnosticos.add(diagnostico);
			}

			XPathFactoryAssist xPathAlergias = xPathRoot.getXPathAssist("//Alergias_e_reações_adversas");
			for (XPathFactoryAssist xPathAlergia : xPathAlergias.iterable(".//Risco_de_Reação_Adversa")) {
				ResABAlergiaReacoes alergia = new ResABAlergiaReacoes();
				alergia.setAgente(xPathAlergia.getString("./data/Agente__fslash__substância_específica/value/value"));
				alergia.setCategoria(xPathAlergia.getString("./data/Categoria_do_agente_causador_da_alergia__fslash__reação_adversa/value/value"));
				alergia.setGravidade(ResABGravidadeEnum.getByCodigo(xPathAlergia.getString("./data/Gravidade/value/defining_code/code_string")));

				for (XPathFactoryAssist xPathEvento : xPathAlergia.iterable(".//Evento_da_reação")) {
					ResABEventoReacao evento = new ResABEventoReacao();
					evento.setDataInstalacao(xPathEvento.getDate("./Data_da_instalação_da_alergia__fslash__reação_adversa/value/value"));
					evento.setEvolucaoAlergia(xPathEvento.getString("./Evolução_da_alergia__fslash__reação_adversa/value/value"));
					evento.setManifestacao(xPathEvento.getString("./Manifestação/value/value"));
					alergia.getEventoReacao().add(evento);
				}
				this.alergias.add(alergia);
			}

			XPathFactoryAssist xPathProcedimentos = xPathRoot.getXPathAssist("//Procedimentos_ou_pequenas_cirurgias");
			for (XPathFactoryAssist xPathProcedimento : xPathProcedimentos.iterable(".//Procedimento")) {
				ResABProcedimento procedimento = new ResABProcedimento();
				procedimento.setCodigo(xPathProcedimento.getString("./description/Procedimento_realizado/value/defining_code/code_string"));
				procedimento.setNome(xPathProcedimento.getString("./description/Procedimento_realizado/value/value"));

				for (XPathFactoryAssist xPathResultado : xPathProcedimento.iterable(".//description/Resultado_e_fslash_ou_observações_do_procedimento_ou_pequena_cirurgia")) {
					procedimento.getResultadoObservacoes().add(xPathResultado.getString("./value/value"));
				}
				this.procedimentos.add(procedimento);
			}

			XPathFactoryAssist xPathMedicamentos = xPathRoot.getXPathAssist("//Lista_de_medicamentos");
			for (XPathFactoryAssist xPathMedicamento : xPathMedicamentos.iterable(".//Linha_de_Medicação/data/Item_de_medicação")) {
				ResABMedicamento medicamento = new ResABMedicamento();
				medicamento.setNomeMedicamento(xPathMedicamento.getString("./Medicamento/value/value"));
				medicamento.setCodigoMedicamentoCatmat(xPathMedicamento.getString("./Medicamento/value/defining_code/code_string"));
				medicamento.setDescricaoFormaFarmaceutica(xPathMedicamento.getString("./Forma_farmacêutica/value/value"));
				medicamento.setCodigoFormaFarmaceutica(xPathMedicamento.getString("./Forma_farmacêutica/value/defining_code/code_string"));
				medicamento.setDescricaoViaAdministracao(xPathMedicamento.getString("./Via_de_administração/value/value"));
				medicamento.setCodigoViaAdministracao(xPathMedicamento.getString("./Via_de_administração/value/defining_code/code_string"));
				medicamento.setDescricaoDose(xPathMedicamento.getString("./Dose/value/value"));
				medicamento.setCodigoDoseEstruturada(xPathMedicamento.getString("./Dose_estruturada/Duração_do_tratamento/value/value"));
				this.medicamentos.add(medicamento);
			}

			XPathFactoryAssist xPathDados = xPathRoot.getXPathAssist("//Dados_do_desfecho/Desfecho__fslash__alta_do_contato_assistencial/data");
			for (XPathFactoryAssist xPathConduta : xPathDados.iterable(".//Conduta")) {
				this.condutas.add(ResABCondutaEnum.getByCodigo(xPathConduta.getString("./value/value")));
			}
			for (XPathFactoryAssist xPathEncaminhamento : xPathRoot.iterable(".//Solicitações_de_encaminhamentos/Encaminhamento")) {
				this.encaminhamentos.add(xPathEncaminhamento.getString("./value/value"));
			}
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
			.cnes(this.cnes);

		for (ResABIdentificacaoProfissional profissional : this.profissionais) {
			caracterizacaoConsulta.identificacaoProfissional()
				.cns(profissional.getCns())
				.cbo(profissional.getCbo())
				.responsavel(profissional.isResponsavel());
		}

		caracterizacaoConsulta
			.ine(this.ine)
			.dataHoraAdmissao(this.dataAdmissao)
			.turnoAtendimento(this.turno)
		.close()
		.medicoesObservacoes()
			.avaliacaoAntropometrica()
				.pesoCorporal(this.dataAdmissao, this.peso)
				.altura(this.dataAdmissao, this.altura)
			.close()
			.gestante()
				.cicloMenstrual(this.dataAdmissao, this.dum)
				.gestacao(this.dataAdmissao, this.idadeGestacional)
				.sumarioObstetrico(this.gestasPrevias, this.partos);

		ProblemaDiagnosticoAvaliadoBuilder<ResumoConsultaABBuilder> diagnosticoAvaliadoBuilder = abBuilder.problemaDiagnostico();
		for (ResABProblemaDiagnostico diagnostico : this.problemasDiagnosticos) {
			diagnosticoAvaliadoBuilder.problema()
				.descricao(diagnostico.getDescricao())
				.tipo(diagnostico.getTipo())
				.codigo(diagnostico.getCodigo());
		}

		AlergiaReacoesAdversasBuilder<ResumoConsultaABBuilder> alergiasBuilder = abBuilder.alergiaReacaoAdversa();
		for (ResABAlergiaReacoes alergia : this.alergias) {
			RiscoReacaoAdversaBuilder<AlergiaReacoesAdversasBuilder<ResumoConsultaABBuilder>> alergiaBuilder = alergiasBuilder.alergia();
			alergiaBuilder
				.agente(alergia.getAgente())
				.categoria(alergia.getCategoria())
				.gravidade(alergia.getGravidade());

			for (ResABEventoReacao evento : alergia.getEventoReacao()) {
				alergiaBuilder.eventoReacao()
					.dataInstalacao(evento.getDataInstalacao())
					.evolucaoAlergia(evento.getEvolucaoAlergia())
					.manifestacao(evento.getManifestacao());
			}
		}

		ProcedimentosPequenasCirurgiasBuilder<ResumoConsultaABBuilder> procedimentosBuilder = abBuilder.procedimentosPequenasCirurgias();
		for (ResABProcedimento procedimento : this.procedimentos) {
			procedimentosBuilder.procedimento()
				.nome(procedimento.getNome())
				.data(this.dataAdmissao)
				.codigo(procedimento.getCodigo())
				.resultadoObservacoes(procedimento.getResultadoObservacoes());
		}

		ListaMedicamentosBuilder<ResumoConsultaABBuilder> medicamentosBuilder = abBuilder.listaMedicamentos();
		for (ResABMedicamento medicamento : this.medicamentos) {
			medicamentosBuilder.itemMedicacao()
				.medicamento(medicamento.getNomeMedicamento(), medicamento.getCodigoMedicamentoCatmat())
				.formaFarmaceutica(medicamento.getDescricaoFormaFarmaceutica(), medicamento.getCodigoFormaFarmaceutica())
				.viaAdministracao(medicamento.getDescricaoViaAdministracao(), medicamento.getCodigoViaAdministracao())
				.dose(medicamento.getDescricaoDose())
				.doseEstruturada(medicamento.getCodigoDoseEstruturada());
		}

		DadosDesfechoBuilder<ResumoConsultaABBuilder> desfechoBuilder = abBuilder.dadosDesfecho();
		for (ResABCondutaEnum conduta : this.condutas) {
			desfechoBuilder.conduta(conduta);
		}

		SolicitacoesEncaminhamentoBuilder<DadosDesfechoBuilder<ResumoConsultaABBuilder>> solicitacaoEncaminhamento = desfechoBuilder.solicitacoesEncaminhamento();
		for (String encaminhamento : this.encaminhamentos) {
				solicitacaoEncaminhamento.encaminhamento(encaminhamento);
		}

		return abBuilder.getXmlContent();
	}
}
