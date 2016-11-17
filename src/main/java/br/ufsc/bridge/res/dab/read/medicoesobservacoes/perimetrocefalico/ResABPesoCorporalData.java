package br.ufsc.bridge.res.dab.read.medicoesobservacoes.perimetrocefalico;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABPesoCorporalData {

	@XmlElement(name = "Qualquer_evento_as_Point_Event")
	private List<ResABPesoCorporalQualquerEvento> qualquerEvento;

	// TODO Qualquer_evento_as_Interval_Event
}
