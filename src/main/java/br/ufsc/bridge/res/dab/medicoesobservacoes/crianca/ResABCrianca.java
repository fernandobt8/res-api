package br.ufsc.bridge.res.dab.medicoesobservacoes.crianca;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABCrianca {

	@XmlElement(name = "Alimentação_da_criança_menor_de_2_anos")
	private List<ResABAlimentacaoCriancaMenorDoisAnos> alimentacaoCriancaMenorDoisAnos;

}
