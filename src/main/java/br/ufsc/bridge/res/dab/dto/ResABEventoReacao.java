package br.ufsc.bridge.res.dab.dto;

import java.util.Date;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
public class ResABEventoReacao {
	private String manifestacao;
	private Date dataInstalacao;
	private String evolucaoAlergia;

	public ResABEventoReacao(XPathFactoryAssist xPathEvento) throws XPathExpressionException {
		this.dataInstalacao = xPathEvento.getDate("./Data_da_instalação_da_alergia__fslash__reação_adversa/value/value");
		this.evolucaoAlergia = xPathEvento.getString("./Evolução_da_alergia__fslash__reação_adversa/value/value");
		this.manifestacao = xPathEvento.getString("./Manifestação/value/value");
	}
}
