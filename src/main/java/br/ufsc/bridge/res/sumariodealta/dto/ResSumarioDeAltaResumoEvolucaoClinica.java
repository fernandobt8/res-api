package br.ufsc.bridge.res.sumariodealta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioDeAltaResumoEvolucaoClinica {

	private String resumo;

	public ResSumarioDeAltaResumoEvolucaoClinica(XPathFactoryAssist xPathResumoEvolucaoClinica) throws XPathExpressionException {
		this.resumo = xPathResumoEvolucaoClinica.getString("./data/Texto_livre/value/value");
	}

}
