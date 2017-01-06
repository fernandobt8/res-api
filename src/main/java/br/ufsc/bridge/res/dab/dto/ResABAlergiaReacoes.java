package br.ufsc.bridge.res.dab.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import br.ufsc.bridge.res.dab.domain.ResABGravidadeEnum;
import br.ufsc.bridge.res.util.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResABAlergiaReacoes implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agente;
	private ResABGravidadeEnum gravidade;
	private String categoria;
	private List<ResABEventoReacao> eventoReacao = new ArrayList<>();

	public ResABAlergiaReacoes(XPathFactoryAssist xPathAlergia) throws XPathExpressionException {
		this.agente = xPathAlergia.getString("./data/Agente__fslash__substância_específica/value/value");
		this.categoria = xPathAlergia.getString("./data/Categoria_do_agente_causador_da_alergia__fslash__reação_adversa/value/value");
		this.gravidade = ResABGravidadeEnum.getByCodigo(xPathAlergia.getString("./data/Gravidade/value/defining_code/code_string"));

		for (XPathFactoryAssist xPathEvento : xPathAlergia.iterable(".//Evento_da_reação")) {
			this.eventoReacao.add(new ResABEventoReacao(xPathEvento));
		}
	}
}
