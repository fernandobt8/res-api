package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;

import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.ResABXMLParserException;
import br.ufsc.bridge.res.util.ResDocument;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
public class ResSumarioAlta extends ResDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date dataAtendimento;
	private String estabelecimento;
	private String equipe;
	private List<ResSumarioAltaProblemaDiagnostico> problemasDiagnostico = new ArrayList<>();
	private List<ResSumarioAltaProcedimento> procedimentos = new ArrayList<>();
	private List<ResSumarioAltaResumoEvolucaoClinica> resumos = new ArrayList<>();
	private List<ResSumarioAltaAlergia> alergias = new ArrayList<>();
	// medicamento estruturado nao ira aparecer por enquanto, deixarei comentado so para manter historico
	// private List<ResSumarioDeAltaMedicamento> medicamentos = new ArrayList<>();
	private List<ResSumarioAltaMedicamentoNaoEstruturado> medicamentosNaoEstruturados = new ArrayList<>();
	private String planoCuidado;
	private String motivoAlta;
	private ResSumarioAltaIdentificacaoProfissional profissional;
	private Date dataAlta;

	public ResSumarioAlta(String xml) throws ResABXMLParserException {
		XPathFactoryAssist xPathRoot = this.getXPathRoot(xml);
		try {
			XPathFactoryAssist xPathAdmissao = xPathRoot.getXPathAssist("//Admissão_do_paciente/data");
			this.dataAtendimento = RDateUtil.isoEHRToDate(xPathAdmissao.getString("./Data_e_hora_da_internação/value/value"));
			String startXPathInstituicao = "./Localização_atribuída_ao_paciente/Instituição";
			this.estabelecimento = xPathAdmissao.getString(startXPathInstituicao + "/Estabelecimento_de_saúde/value/value");
			this.equipe = xPathAdmissao.getString(startXPathInstituicao + "/Identificação_da_equipe_de_saúde/value/value");

			XPathFactoryAssist xPathDiagnosticos = xPathRoot
					.getXPathAssist("//Motivo_da_admissão_comma__diagnósticos_relevantes_e_patologias_associadas_desenvolvidas_na_internação");
			for (XPathFactoryAssist xPathDiagnostico : xPathDiagnosticos.iterable(".//Problema_Diagnóstico")) {
				this.problemasDiagnostico.add(new ResSumarioAltaProblemaDiagnostico(xPathDiagnostico));
			}

			XPathFactoryAssist xPathProcedimentos = xPathRoot
					.getXPathAssist("//Procedimento_openBrkt_s_closeBrkt__realizado_openBrkt_s_closeBrkt__ou_solicitado_openBrkt_s_closeBrkt_");
			for (XPathFactoryAssist xPathProcedimento : xPathProcedimentos.iterable(".//Procedimento")) {
				this.procedimentos.add(new ResSumarioAltaProcedimento(xPathProcedimento));
			}

			XPathFactoryAssist xPathResumos = xPathRoot.getXPathAssist("//Resumo_da_evolução_clínica_do_indivíduo_durante_a_internação");
			for (XPathFactoryAssist xPathResumo : xPathResumos.iterable(".//Recipiente")) {
				this.resumos.add(new ResSumarioAltaResumoEvolucaoClinica(xPathResumo));
			}

			XPathFactoryAssist xPathAlergias = xPathRoot.getXPathAssist("//Alergias_e_fslash_ou_reações_adversas_na_internação");
			for (XPathFactoryAssist xPathAlergia : xPathAlergias.iterable(".//Alergia_e_fslash_ou_reação_adversa")) {
				this.alergias.add(new ResSumarioAltaAlergia(xPathAlergia));
			}

			// medicamento estruturado nao ira aparecer por enquanto, deixarei comentado so para manter historico
			// for (XPathFactoryAssist xPathMedicamentoEstruturado : xPathAltaMedicamentos.iterable(".//Linha_de_Medicação")) {
			// this.medicamentos.add(new ResSumarioDeAltaMedicamento(xPathMedicamentoEstruturado));
			// }

			XPathFactoryAssist xPathAltaMedicamentos = xPathRoot.getXPathAssist("//Prescrição_da_alta");
			for (XPathFactoryAssist xPathMedicamentoNaoEstruturado : xPathAltaMedicamentos.iterable(".//Recipiente")) {
				this.medicamentosNaoEstruturados.add(new ResSumarioAltaMedicamentoNaoEstruturado(xPathMedicamentoNaoEstruturado));
			}

			XPathFactoryAssist xPathPlanoCuidado = xPathRoot.getXPathAssist("//Plano_de_cuidados_comma__instruções_e_recomendações__openBrkt_na_alta_closeBrkt_");
			this.planoCuidado = xPathPlanoCuidado.getString("./Plano_de_Cuidado/narrative/value");

			XPathFactoryAssist xPathInformacoesAlta = xPathRoot.getXPathAssist("//Informações_da_alta/Desfecho__fslash__alta_do_contato_assistencial/data");
			this.motivoAlta = xPathInformacoesAlta.getString("./Motivo_do_desfecho/value/value");
			this.profissional = new ResSumarioAltaIdentificacaoProfissional(xPathInformacoesAlta);
			this.dataAlta = RDateUtil.isoEHRToDate(xPathInformacoesAlta.getString("./Data_e_hora_da_saída_da_internação/value/value"));
		} catch (XPathExpressionException e) {
			throw new ResABXMLParserException("Erro no parser do XML para o DTO", e);
		}
	}
}