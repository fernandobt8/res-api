package br.ufsc.bridge.res.sumariodealta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

// XXX: precisa serializar? relembrar razão disso
public class ResSumarioDeAltaProcedimento {

	private String nome;
	private String codigo;

	public ResSumarioDeAltaProcedimento(XPathFactoryAssist xPathProcedimento) throws XPathExpressionException {
		this.nome = xPathProcedimento.getString("./description/Procedimento_SUS/value/value");
		this.codigo = xPathProcedimento.getString("./description/Procedimento_SUS/value/defining_code/code_string");
		// XXX: pegar alguma observação?
	}
}
