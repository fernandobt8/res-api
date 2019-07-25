package br.ufsc.bridge.res.sumariodealta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioDeAltaProblemaDiagnostico {

	private String descricao;
	private String codigo;

	public ResSumarioDeAltaProblemaDiagnostico(XPathFactoryAssist xPathProblemaDiagnostico) throws XPathExpressionException {
		this.descricao = xPathProblemaDiagnostico.getString("./data/Diagnóstico/value/value");
		this.codigo = xPathProblemaDiagnostico.getString("./data/Diagnóstico/value/defining_code/code_string");

		// XXX: diferenciar entre cid e ciap?
	}
}
