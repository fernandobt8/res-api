package br.ufsc.bridge.res.dab.medicoesobservacoes.gestante;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.medicoesobservacoes.gestante.ciclomenstrual.ResABCicloMenstrual;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABGestante {

	@XmlElement(name = "Ciclo_menstrual")
	private ResABCicloMenstrual cicloMenstrual;
}
