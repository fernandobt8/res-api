package br.ufsc.bridge.res.dab.read.medicoesobservacoes.perimetrocefalico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABPesoCorporal {

	@XmlElement(name = "data")
	private ResABPesoCorporalData data;
}
