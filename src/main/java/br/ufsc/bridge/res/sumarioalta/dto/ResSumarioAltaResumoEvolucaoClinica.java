package br.ufsc.bridge.res.sumarioalta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioAltaResumoEvolucaoClinica {

	private String resumo;

	public ResSumarioAltaResumoEvolucaoClinica(XPathFactoryAssist xPathResumoEvolucaoClinica) throws XPathExpressionException {
		this.resumo = xPathResumoEvolucaoClinica.getString("./data/Texto_livre/value/value");
	}

}
