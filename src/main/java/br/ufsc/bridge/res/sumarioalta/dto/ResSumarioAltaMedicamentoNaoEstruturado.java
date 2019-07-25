package br.ufsc.bridge.res.sumarioalta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

// XXX: aguardar Postal
public class ResSumarioAltaMedicamentoNaoEstruturado {

	private String descricao;

	public ResSumarioAltaMedicamentoNaoEstruturado(XPathFactoryAssist xPathMedicamentoNaoEstruturado) throws XPathExpressionException {
		this.descricao = xPathMedicamentoNaoEstruturado
				.getString("./data/Medicamentos_prescritos_na_alta__openBrkt_não_estruturado_closeBrkt_/Descrição_da_prescrição/value/value");
	}

}
