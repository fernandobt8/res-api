package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
public class ResSumarioAltaProcedimento implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String codigo;

	public ResSumarioAltaProcedimento(XPathFactoryAssist xPathProcedimento) throws XPathExpressionException {
		this.nome = xPathProcedimento.getString("./description/Procedimento_SUS/value/value");
		this.codigo = xPathProcedimento.getString("./description/Procedimento_SUS/value/defining_code/code_string");
	}
}
