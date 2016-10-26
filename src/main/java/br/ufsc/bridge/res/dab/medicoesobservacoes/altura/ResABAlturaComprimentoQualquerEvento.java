package br.ufsc.bridge.res.dab.medicoesobservacoes.altura;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.common.ResABQualquerEvento;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABAlturaComprimentoQualquerEvento extends ResABQualquerEvento {

	@XmlElement(name = "data")
	private ResABAlturaComprimentoQualquerEventoData data;
}
