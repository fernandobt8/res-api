package br.ufsc.bridge.res.sumariodealta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioAltaIdentificacaoProfissional {

	private String cns;
	private String cbo;

	public ResSumarioAltaIdentificacaoProfissional(XPathFactoryAssist xPathInformacoesAlta) throws XPathExpressionException {
		String startXPath = "./Profissional_responsável_pela_alta";
		this.cns = xPathInformacoesAlta.getString(startXPath + "/CNS_do_profissional/value/value");
		this.cbo = xPathInformacoesAlta.getString(startXPath + "/Ocupação_do_profissional_responsável_pela_alta/value/value");
	}

}
