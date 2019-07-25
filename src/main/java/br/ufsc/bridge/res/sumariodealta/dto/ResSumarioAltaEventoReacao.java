package br.ufsc.bridge.res.sumariodealta.dto;

import java.util.Date;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioAltaEventoReacao {

	private String manifestacao;
	private Date dataInstalacao;
	private String evolucaoAlergia;

	public ResSumarioAltaEventoReacao(XPathFactoryAssist xPathEventoReacao) throws XPathExpressionException {
		this.manifestacao = xPathEventoReacao.getString("./Manifestação/value/value");
		this.dataInstalacao = RDateUtil.isoEHRToDate(xPathEventoReacao.getString("./Data_da_instalação_da_reação_adversa/value/value"));
		this.evolucaoAlergia = xPathEventoReacao.getString("./Evolução_da_alergia_fslash_reação_adversa/value/value");
	}

}
