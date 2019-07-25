package br.ufsc.bridge.res.sumariodealta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.res.dab.domain.ResABGravidadeEnum;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioDeAltaAlergia {

	private String agente;
	private String categoria;
	private ResABGravidadeEnum gravidade;
	// private List<ResABEventoReacao> eventoReacao = new ArrayList<>();

	public ResSumarioDeAltaAlergia(XPathFactoryAssist xPathAlergia) throws XPathExpressionException {
		this.agente = xPathAlergia.getString("./data/Agente_fslash_substância_específica/value/value");
		this.categoria = xPathAlergia.getString("./data/Categoria_do_agente_causador_da_alergia_ou_reação_adversa/value/value");
		this.gravidade = ResABGravidadeEnum.getByCodigo(xPathAlergia.getString("./data/Criticidade/value/defining_code/code_string"));

		// XXX: conversar Burigo sobre colocar eventoReacao, AB possui
		// for (XPathFactoryAssist xPathEvento : xPathAlergia.iterable(".//Evento_da_reação")) {
		// this.eventoReacao.add(new ResABEventoReacao(xPathEvento));
		// }
	}

}
