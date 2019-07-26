package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
public class ResSumarioAltaIdentificacaoProfissional implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cns;
	private String cbo;

	public ResSumarioAltaIdentificacaoProfissional(XPathFactoryAssist xPathInformacoesAlta) throws XPathExpressionException {
		String startXPath = "./Profissional_responsável_pela_alta";
		this.cns = xPathInformacoesAlta.getString(startXPath + "/CNS_do_profissional/value/value");
		this.cbo = xPathInformacoesAlta.getString(startXPath + "/Ocupação_do_profissional_responsável_pela_alta/value/value");
	}

}
