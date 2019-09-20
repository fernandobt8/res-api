package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.ResABXMLParserException;
import br.ufsc.bridge.res.util.ResDocument;
import br.ufsc.bridge.res.util.json.DateJsonPathValueConverter;
import br.ufsc.bridge.res.util.json.JsonPathProperty;
import br.ufsc.bridge.res.util.json.ResJsonUtils;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
public class ResSumarioAlta extends ResDocument implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Caracterização do atendimento')]"
			+ ".items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Data e hora da internação')]"
			+ ".value.value",
			converter = DateJsonPathValueConverter.class)
	private Date dataAtendimento;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Caracterização do atendimento')]"
			+ ".items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"
			+ ".items[?(@.name.value == 'Instituição')]"
			+ ".items[?(@.name.value == 'Estabelecimento de saúde')]"
			+ ".value.value")
	private String estabelecimento;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Caracterização do atendimento')]"
			+ ".items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"
			+ ".items[?(@.name.value == 'Instituição')]"
			+ ".items[?(@.name.value == 'Identificação da equipe de saúde')]"
			+ ".value.value")
	private String equipe;

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Motivo da admissão, diagnósticos relevantes e patologias associadas desenvolvidas na internação')]"
			+ ".items[?(@.name.value == 'Problema Diagnóstico')]"
			+ ".data.items")
	private List<ResSumarioAltaProblemaDiagnostico> problemasDiagnostico = new ArrayList<>();

	@JsonPathProperty(value = "$.content[?(@.name.value == 'Procedimento(s) realizado(s) ou solicitado(s)')]")
	private List<ResSumarioAltaProcedimento> procedimentos = new ArrayList<>();

	@JsonPathProperty(value = ".content[?(@.name.value == 'Resumo da evolução clínica do indivíduo durante a internação')]"
			+ ".items")
	private List<ResSumarioAltaResumoEvolucaoClinica> resumos = new ArrayList<>();

	@JsonPathProperty(value = ".content[?(@.name.value == 'Alergias e/ou reações adversas na internação')]"
			+ ".items")
	private List<ResSumarioAltaAlergia> alergias = new ArrayList<>();
	// medicamento estruturado nao ira aparecer por enquanto, deixarei comentado so para manter historico
	// private List<ResSumarioDeAltaMedicamento> medicamentos = new ArrayList<>();

	@JsonPathProperty(value = ".content[?(@.name.value == 'Prescrição da alta')]")
	private List<ResSumarioAltaMedicamentoNaoEstruturado> medicamentosNaoEstruturados = new ArrayList<>();

	@JsonPathProperty(value = ".content[?(@.name.value == 'Plano de cuidados, instruções e recomendações (na alta)')]"
			+ ".items.activities.description"
			+ ".items.value.value")
	private String planoCuidado;

	@JsonPathProperty(value = ".content[?(@.name.value == 'Informações da alta')]"
			+ ".items.data.items[?(@.name.value == 'Motivo do desfecho')]"
			+ ".value.value")
	private String motivoAlta;

	@JsonPathProperty(value = ".content[?(@.name.value == 'Informações da alta')]"
			+ ".items.data.items[?(@.name.value == 'Profissional responsável pela alta')]"
			+ ".items")
	private ResSumarioAltaIdentificacaoProfissional profissional;

	@JsonPathProperty(value = ".content[?(@.name.value == 'Informações da alta')]"
			+ ".items.data.items[?(@.name.value == 'Data e hora da saída da internação')]"
			+ ".value.value",
			converter = DateJsonPathValueConverter.class)
	private Date dataAlta;

	public static ResSumarioAlta readJsonBase64(String jsonBase64) {
		ResSumarioAlta sumarioAlta = ResJsonUtils.readJson(jsonBase64, ResSumarioAlta.class);
		List<ResSumarioAltaMedicamentoNaoEstruturado> medicamentosNaoEstruturados = sumarioAlta.getMedicamentosNaoEstruturados();

		for (ResSumarioAltaMedicamentoNaoEstruturado medicamento : medicamentosNaoEstruturados) {
			List<String> descricoes = new ArrayList<>();
			for (String medicamentoDescricao : medicamento.getDescricoes()) {
				if (StringUtils.isNotBlank(medicamentoDescricao)) {
					descricoes.addAll(Arrays.asList(medicamentoDescricao.split(";")));
				}
			}
			medicamento.setDescricoes(descricoes);
		}
		return sumarioAlta;
	}

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
