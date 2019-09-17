package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;

import br.ufsc.bridge.res.dab.domain.ResCriticidadeEnum;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
public class ResSumarioAltaAlergia implements Serializable {

	private static final long serialVersionUID = 1L;
	private String agente;
	private String categoria;
	private ResCriticidadeEnum criticidade;
	private List<ResSumarioAltaEventoReacao> eventoReacao = new ArrayList<>();

	public ResSumarioAltaAlergia(XPathFactoryAssist xPathAlergia) throws XPathExpressionException {
		this.agente = xPathAlergia.getString("./data/Agente_fslash_substância_específica/value/value");
		this.categoria = xPathAlergia.getString("./data/Categoria_do_agente_causador_da_alergia_ou_reação_adversa/value/value");
		this.criticidade = ResCriticidadeEnum.getByCodigo(xPathAlergia.getString("./data/Criticidade/value/defining_code/code_string"));

		for (XPathFactoryAssist xPathEventoReacao : xPathAlergia.iterable(".//Evento_da_reação")) {
			this.eventoReacao.add(new ResSumarioAltaEventoReacao(xPathEventoReacao));
		}
	}

}
