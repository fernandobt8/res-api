package br.ufsc.bridge.res.dab.medicoesobservacoes.gestante.ciclomenstrual;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABCicloMenstrualQualquerEventoData {

	@XmlElement(name = "DUM__openBrkt_Data_da_última_menstruação_closeBrkt_")
	private ResABCicloMenstrualValue perimetroCefalico;
}
