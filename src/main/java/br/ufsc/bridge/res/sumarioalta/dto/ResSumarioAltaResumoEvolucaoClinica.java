package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioAltaResumoEvolucaoClinica implements Serializable {

	private static final long serialVersionUID = 1L;
	private String resumo;

	public ResSumarioAltaResumoEvolucaoClinica(XPathFactoryAssist xPathResumoEvolucaoClinica) throws XPathExpressionException {
		this.resumo = xPathResumoEvolucaoClinica.getString("./data/Texto_livre/Descrição_da_evolução_clínica_do_indivíduo_durante_a_internação/value/value");
	}

}
