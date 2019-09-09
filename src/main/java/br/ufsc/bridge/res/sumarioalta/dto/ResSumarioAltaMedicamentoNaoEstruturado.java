package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
public class ResSumarioAltaMedicamentoNaoEstruturado implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> descricoes = new ArrayList<>();

	public ResSumarioAltaMedicamentoNaoEstruturado(XPathFactoryAssist xPathMedicamentoNaoEstruturado) throws XPathExpressionException {
		String descricao = xPathMedicamentoNaoEstruturado
				.getString("./data/Medicamentos_prescritos_na_alta__openBrkt_não_estruturado_closeBrkt_/Descrição_da_prescrição/value/value");
		if (descricao != null) {
			for (String medicamento : descricao.split(";")) {
				if (StringUtils.isNotBlank(medicamento)) {
					this.descricoes.add(medicamento);
				}
			}
		}
	}

}
