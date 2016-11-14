package br.ufsc.bridge.res.dab.read.medicoesobservacoes.gestante.ciclomenstrual;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.read.common.ResABDateValueEHR;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABCicloMenstrualValue {

	@XmlElement(name = "value")
	private ResABDateValueEHR value;
}
