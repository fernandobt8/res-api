package br.ufsc.bridge.res.dab.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.xpath.XPathExpressionException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResABEventoReacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private String manifestacao;
	private Date dataInstalacao;
	private String evolucaoAlergia;

	public ResABEventoReacao(XPathFactoryAssist xPathEvento) throws XPathExpressionException {
		this.dataInstalacao = RDateUtil.isoEHRToDate(xPathEvento.getString("./Data_da_instalação_da_reação_adversa/value/value"));
		this.evolucaoAlergia = xPathEvento.getString("./Evolução_da_alergia_fslash_reação_adversa/value/value");
		this.manifestacao = xPathEvento.getString("./Manifestação/value/value");
	}
}
