package br.ufsc.bridge.res.dab.medicoesobservacoes.peso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABPerimetroCefalicoQualquerEvento {

	@XmlElement(name = "data")
	private ResABPerimetroCefalicoQualquerEventoData data;
}
