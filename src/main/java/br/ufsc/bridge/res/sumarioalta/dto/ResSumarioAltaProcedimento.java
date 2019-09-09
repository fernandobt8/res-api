package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
public class ResSumarioAltaProcedimento implements Serializable {

	private static final String SIGTAP = "SIGTAP";
	private static final long serialVersionUID = 1L;
	private String nome;
	private String codigo;
	private String classificacao;
	private String status;

	public ResSumarioAltaProcedimento(XPathFactoryAssist xPathProcedimento) throws XPathExpressionException {
		String startXPath = "./description/Procedimento_SUS/value";

		this.nome = xPathProcedimento.getString(startXPath + "/value");
		this.codigo = xPathProcedimento.getString(startXPath + "/defining_code/code_string");

		this.classificacao = xPathProcedimento.getString(startXPath + "/defining_code/terminology_id/value");
		if (this.classificacao != null && this.classificacao.contains(SIGTAP)) {
			this.classificacao = SIGTAP;
		}

		this.status = xPathProcedimento.getString("./description/Status_do_procedimento/value/value");
	}
}
