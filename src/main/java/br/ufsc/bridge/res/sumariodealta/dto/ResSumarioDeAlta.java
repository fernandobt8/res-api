package br.ufsc.bridge.res.sumariodealta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;

import br.ufsc.bridge.res.dab.exception.ResABXMLParserException;
import br.ufsc.bridge.res.sumariodealta.write.builder.SumarioDeAltaBuilder;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.ResDocument;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
public class ResSumarioDeAlta extends ResDocument implements Serializable {

	private Date dataAtendimento;

	// XXX: verificar possibilidade de usar mesmos dtos para ab e sumário
	private List<ResSumarioDeAltaProblemaDiagnostico> problemasDiagnosticos = new ArrayList<>();

	// XXX: verificar possibilidade de usar mesmos dtos para ab e sumário
	private List<ResSumarioDeAltaProcedimento> procedimentos = new ArrayList<>();

	private List<ResSumarioDeAltaResumoEvolucaoClinica> resumos = new ArrayList<>();

	// XXX: verificar possibilidade de usar mesmos dtos para ab e sumário
	private List<ResSumarioDeAltaAlergia> alergias = new ArrayList<>();

	// XXX: verificar apresentacao na tela e necessidade de dois dtos ou um só com campo aberto
	// XXX: verificar possibilidade de usar mesmos dtos para ab e sumário
	private List<ResSumarioDeAltaMedicamento> medicamentos = new ArrayList<>();
	private List<ResSumarioDeAltaMedicamentoNaoEstruturado> medicamentosNaoEstruturados = new ArrayList<>();

	// XXX: verificar necessidade
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ResSumarioDeAlta(String xml) throws ResABXMLParserException {
		XPathFactoryAssist xPathRoot = this.getXPathRoot(xml);
		try {
			// XXX: acho que não, confirmar burigo
			// territory?
			// cns?
			// start_time?
			XPathFactoryAssist xPathAdmissao = xPathRoot.getXPathAssist("//Admissão_do_paciente/data");
			this.dataAtendimento = RDateUtil.isoEHRToDate(xPathAdmissao.getString("./Data_e_hora_da_internação/value/value"));
			// XXX: acho que sim, confirmar postal
			// caráter da internação
			// procedencia
			// local do atendimento0
			// estabelecimento de saúde
			// equipe de saúde

			XPathFactoryAssist xPathDiagnosticos = xPathRoot
					.getXPathAssist("//Motivo_da_admissão_comma__diagnósticos_relevantes_e_patologias_associadas_desenvolvidas_na_internação");
			// XXX: também pode ter procedimentos aqui
			// XXX: problema_diagnostico_hash
			for (XPathFactoryAssist xPathDiagnostico : xPathDiagnosticos.iterable(".//Problema_Diagnóstico")) {
				this.problemasDiagnosticos.add(new ResSumarioDeAltaProblemaDiagnostico(xPathDiagnostico));
			}

			XPathFactoryAssist xPathProcedimentos = xPathRoot
					.getXPathAssist("//Procedimento_openBrkt_s_closeBrkt__realizado_openBrkt_s_closeBrkt__ou_solicitado_openBrkt_s_closeBrkt");
			// XXX: problemas_ou_diagnosticos
			// XXX: procedimento_hash
			for (XPathFactoryAssist xPathProcedimento : xPathProcedimentos.iterable(".//Procedimento")) {
				this.procedimentos.add(new ResSumarioDeAltaProcedimento(xPathProcedimento));
			}

			XPathFactoryAssist xPathResumos = xPathRoot.getXPathAssist("//Resumo_da_evolução_clínica_do_indivíduo_durante_a_internação");
			for (XPathFactoryAssist xPathResumo : xPathResumos.iterable(".//Recipiente")) {
				this.resumos.add(new ResSumarioDeAltaResumoEvolucaoClinica(xPathResumo));
			}

			XPathFactoryAssist xPathAlergias = xPathRoot.getXPathAssist("//Alergias_e_fslash_ou_reações_adversas_na_internação");
			for (XPathFactoryAssist xPathAlergia : xPathAlergias.iterable(".//Alergia_e_fslash_ou_reação_adversa")) {
				this.alergias.add(new ResSumarioDeAltaAlergia(xPathAlergia));
			}

			XPathFactoryAssist xPathAltaMedicamentos = xPathRoot.getXPathAssist("//Prescrição_da_alta");
			for (XPathFactoryAssist xPathMedicamentoEstruturado : xPathAltaMedicamentos.iterable(".//Linha_de_Medicação")) {
				this.medicamentos.add(new ResSumarioDeAltaMedicamento(xPathMedicamentoEstruturado));
			}
			for (XPathFactoryAssist xPathMedicamentoNaoEstruturado : xPathAltaMedicamentos.iterable(".//Recipiente")) {
				this.medicamentosNaoEstruturados.add(new ResSumarioDeAltaMedicamentoNaoEstruturado(xPathMedicamentoNaoEstruturado));
			}

			// Plano_de_cuidados_comma__instruções_e_recomendações__openBrkt_na_alta_closeBrkt_

			// verificar informações da alta

			// XXX: colocar algum profissional

		} catch (XPathExpressionException e) {
			throw new ResABXMLParserException("Erro no parser do XML para o DTO", e);
		}
	}

	@Override
	public String getXml() {
		SumarioDeAltaBuilder sumarioDeAltaBuilder = new SumarioDeAltaBuilder();
		// XXX: implementar
		return sumarioDeAltaBuilder.getXmlContent();
	}

}
