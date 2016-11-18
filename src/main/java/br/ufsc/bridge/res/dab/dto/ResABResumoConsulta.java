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
				this.profissionais.add(new ResABIdentificacaoProfissional(xPathprofissional));
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
				this.problemasDiagnosticos.add(new ResABProblemaDiagnostico(xPathDiagnostico));
			}

			XPathFactoryAssist xPathAlergias = xPathRoot.getXPathAssist("//Alergias_e_reações_adversas");
			for (XPathFactoryAssist xPathAlergia : xPathAlergias.iterable(".//Risco_de_Reação_Adversa")) {
				this.alergias.add(new ResABAlergiaReacoes(xPathAlergia));
			}

			XPathFactoryAssist xPathProcedimentos = xPathRoot.getXPathAssist("//Procedimentos_ou_pequenas_cirurgias");
			for (XPathFactoryAssist xPathProcedimento : xPathProcedimentos.iterable(".//Procedimento")) {
				this.procedimentos.add(new ResABProcedimento(xPathProcedimento));
			}

			XPathFactoryAssist xPathMedicamentos = xPathRoot.getXPathAssist("//Lista_de_medicamentos");
			for (XPathFactoryAssist xPathMedicamento : xPathMedicamentos.iterable(".//Linha_de_Medicação/data/Item_de_medicação")) {
				this.medicamentos.add(new ResABMedicamento(xPathMedicamento));
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
			.dataHoraAdmissao(this.dataAtendimento)
			.turnoAtendimento(this.turno)
		.close()
		.medicoesObservacoes()
			.avaliacaoAntropometrica()
				.pesoCorporal(this.dataAtendimento, this.peso)
				.altura(this.dataAtendimento, this.altura)
			.close()
			.gestante()
				.cicloMenstrual(this.dataAtendimento, this.dum)
				.gestacao(this.dataAtendimento, this.idadeGestacional)
				.sumarioObstetrico(this.gestasPrevias, this.partos);

		ProblemaDiagnosticoAvaliadoBuilder<ResumoConsultaABBuilder> diagnosticoAvaliadoBuilder = abBuilder.problemaDiagnostico();
		for (ResABProblemaDiagnostico diagnostico : this.problemasDiagnosticos) {
			diagnosticoAvaliadoBuilder.problema()
				.descricao(diagnostico.getDescricao())
				.tipo(diagnostico.getTipo().getTipo())
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
				.data(this.dataAtendimento)
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

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
		this.turno = ResABTurnoEnum.getTurnoByHora(dataAtendimento);
	}
}
