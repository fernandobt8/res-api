package br.ufsc.bridge.res.dab.read.medicoesobservacoes.crianca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.read.common.ResABQualquerEvento;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABAlimentacaoCriancaMenorDoisAnosQualquerEvento extends ResABQualquerEvento {

	@XmlElement(name = "data")
	private ResABAlimentacaoCriancaMenorDoisAnosQualquerEventoData data;
}
