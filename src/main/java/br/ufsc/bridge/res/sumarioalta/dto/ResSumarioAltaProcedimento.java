package br.ufsc.bridge.res.sumarioalta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

// XXX: precisa serializar? relembrar razão disso
public class ResSumarioAltaProcedimento {

	private String nome;
	private String codigo;

	public ResSumarioAltaProcedimento(XPathFactoryAssist xPathProcedimento) throws XPathExpressionException {
		this.nome = xPathProcedimento.getString("./description/Procedimento_SUS/value/value");
		this.codigo = xPathProcedimento.getString("./description/Procedimento_SUS/value/defining_code/code_string");
	}
}
