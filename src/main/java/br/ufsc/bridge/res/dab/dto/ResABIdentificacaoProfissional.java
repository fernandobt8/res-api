package br.ufsc.bridge.res.dab.dto;

import java.io.Serializable;

import javax.xml.xpath.XPathExpressionException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResABIdentificacaoProfissional implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cns;
	private String cbo;
	private String nome;
	private boolean responsavel;

	public ResABIdentificacaoProfissional(XPathFactoryAssist xPathprofissional) throws XPathExpressionException {
		this.cns = xPathprofissional.getString("./CNS/value/value");
		this.cbo = xPathprofissional.getString("./CBO/value/value");
		this.nome = xPathprofissional.getString("./Nome/value/value");
		this.responsavel = xPathprofissional.getBoolean("./É_o_responsável_pelo_atendimento_quest_/value/value");
	}
}
