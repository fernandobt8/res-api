package br.ufsc.bridge.res.dab.dto;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
public class ResABIdentificacaoProfissional {

	private String cns;
	private String cbo;
	private boolean responsavel;

	public ResABIdentificacaoProfissional(XPathFactoryAssist xPathprofissional) throws XPathExpressionException {
		this.cns = xPathprofissional.getString("./CNS/value/value");
		this.cbo = xPathprofissional.getString("./CBO/value/value");
		this.responsavel = xPathprofissional.getBoolean("./É_o_responsável_pelo_atendimento_quest_/value/value");
	}
}
