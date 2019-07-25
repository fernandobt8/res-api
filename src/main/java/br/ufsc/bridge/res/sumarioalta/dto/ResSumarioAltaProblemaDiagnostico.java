package br.ufsc.bridge.res.sumarioalta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.res.dab.domain.ResABTipoProblemaDiagnostico;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioAltaProblemaDiagnostico {

	private String descricao;
	private String codigo;
	private ResABTipoProblemaDiagnostico tipo;

	public ResSumarioAltaProblemaDiagnostico(XPathFactoryAssist xPathProblemaDiagnostico) throws XPathExpressionException {
		this.descricao = xPathProblemaDiagnostico.getString("./data/Diagnóstico/value/value");
		this.codigo = xPathProblemaDiagnostico.getString("./data/Diagnóstico/value/defining_code/code_string");
		this.tipo = ResABTipoProblemaDiagnostico.getByTipo(xPathProblemaDiagnostico.getString("./data/Diagnóstico/value/defining_code/terminology_id/value"));
	}
}
