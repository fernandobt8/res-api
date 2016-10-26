package br.ufsc.bridge.res.dab.medicoesobservacoes.gestante.ciclomenstrual;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.common.ResABDateValueEHR;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABCicloMenstrualValue {

	@XmlElement(name = "value")
	private ResABDateValueEHR value;
}
