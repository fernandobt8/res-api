package br.ufsc.bridge.res.dab.medicoesobservacoes.gestante.ciclomenstrual;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABCicloMenstrualData {

	@XmlElement(name = "Qualquer_evento_as_Point_Event")
	private ResABCicloMenstrualQualquerEvento qualquerEvento;

	// TODO Qualquer_evento_as_Interval_Event
}
